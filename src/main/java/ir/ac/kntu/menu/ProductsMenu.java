package ir.ac.kntu.menu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.MarketType;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ProductsMenu {
    private final Engine engine;
    private final Account account;
    private final Market market;

    public ProductsMenu(Engine engine, Account account, Market market) {
        this.engine = engine;
        this.account = account;
        this.market = market;
    }

    public Pane showProductsOfMarketMenu() {
        ArrayList<Product> relatedMarkets = market.searchByName("");
        BorderPane borderPane = new BorderPane();
        BorderPane innerBorderPane = new BorderPane();
        Button searchButton = new Button("Search");
        TextField searchField = new TextField("");
        searchField.setPromptText("Product's name");
        innerBorderPane.setTop(new HBox(searchField,searchButton));
        TableView tableView = new TableView();
        tableView.setPlaceholder(new Label("Nothing"));
        TableColumn<Product,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product,String> costColumn = new TableColumn<>("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        TableColumn<Product,String> availabilityColumn = new TableColumn<>("Availability");
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        TableColumn<Product,String> rateColumn = new TableColumn<>("Rate");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        TableColumn<Product,String> commentNumColumn = new TableColumn<>("Comment Number");
        commentNumColumn.setCellValueFactory(new PropertyValueFactory<>("commentsNum"));

        tableView.getColumns().addAll(nameColumn,costColumn,availabilityColumn,rateColumn,commentNumColumn);
        tableView.getItems().addAll(relatedMarkets);
        TableView.TableViewSelectionModel<Product> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        Button orderButton = new Button("Order");
        Button seeComments = new Button("See Comments");
        Label alertLabel = new Label("");
        innerBorderPane.setCenter(tableView);
        innerBorderPane.setBottom(new HBox(orderButton,seeComments,alertLabel));
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setCenter(innerBorderPane);
        borderPane.setCenter(borderPane2);

        orderButton.setOnAction(Event-> {
            orderButtonPressed(borderPane2,selectionModel,alertLabel);
        });
        seeComments.setOnAction(Event-> {
            seeCommentsButtonPressed(tableView.getSelectionModel().getSelectedItems());
        });
        searchButton.setOnAction(Event-> {
            ArrayList<Product> searchedProducts = market.searchByName(searchField.getText());
            tableView.getItems().clear();
            tableView.getItems().addAll(searchedProducts);
        });
        return borderPane;
    }

    private void orderButtonPressed(BorderPane borderPane2, TableView.TableViewSelectionModel<Product> selectionModel , Label alertLabel) {
        if (selectionModel.getSelectedItems().size() != 0) {
            if (market.getMarketType().equals(MarketType.RESTAURANT)) {
                ArrayList<Product> selectedProducts = new ArrayList<>();
                selectedProducts.addAll(selectionModel.getSelectedItems());
                engine.getOrderService().addOrder(new Order((User) account, market, selectedProducts));
                alertLabel.setTextFill(Color.GREEN);
                alertLabel.setText("Successfully Ordered");
            } else {
                borderPane2.setCenter(showFinalizingOrder(selectionModel));
//                for (Map.Entry<String,Integer> c: market.getSchedule()) {
//                    System.out.println(c.getKey());
//                }
            }
        }
    }

    private Pane showFinalizingOrder(TableView.TableViewSelectionModel<Product> selectionModel) {
        ArrayList<Product> selectedProducts = new ArrayList<>();
        selectedProducts.addAll(selectionModel.getSelectedItems());
        BorderPane borderPane = new BorderPane();
        ListView listView = new ListView();
        listView.setFixedCellSize(40);
        listView.setPrefHeight(200);
        listView.getItems().addAll(selectedProducts);
        ListView listView2 = new ListView();
        listView2.setPrefHeight(200);
        Label[] labels = new Label[selectedProducts.size()];
        for (int i = 0; i < selectedProducts.size(); i++) {
            Slider slider = new Slider(0, selectedProducts.get(i).getAvailabilityInt(),0);
            slider.setShowTickLabels(true);
            Label label= new Label("" +0);
            labels[i] = label;
            HBox tempHBox = new HBox(slider,label);
            listView2.getItems().add(tempHBox);
            slider.valueProperty().addListener((observableValue, number, t1) -> label.setText("" + Math.round(t1.intValue())));
        }
        HBox hBox = new HBox(listView,listView2);
        borderPane.setTop(hBox);

        return borderPane;
    }

    private void seeCommentsButtonPressed(ObservableList<Product> temp) {
        if (temp.size() != 0) {
            Stage commentStage = new Stage();
            BorderPane borderPane = new BorderPane();
            ListView listView = new ListView();
            listView.setPlaceholder(new Label("Nothing"));
            listView.setPrefWidth(500);
            listView.getItems().addAll(temp.get(0).getComments());
            borderPane.setCenter(listView);
            commentStage.setScene(new Scene(borderPane));
            commentStage.show();
        }
    }
}
