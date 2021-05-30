package ir.ac.kntu.menu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.ProductType;
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
import java.util.Map;

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
        ArrayList<Product> relatedMarkets = market.searchProductsByName("");
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
        borderPane.setCenter(innerBorderPane);

        orderButton.setOnAction(Event-> {
            orderButtonPressed(borderPane,selectionModel,alertLabel);
        });
        seeComments.setOnAction(Event-> {
            seeCommentsButtonPressed(tableView.getSelectionModel().getSelectedItems());
        });
        searchButton.setOnAction(Event-> {
            ArrayList<Product> searchedProducts = market.searchProductsByName(searchField.getText());
            tableView.getItems().clear();
            tableView.getItems().addAll(searchedProducts);
        });
        return borderPane;
    }

    private void orderButtonPressed(BorderPane borderPane, TableView.TableViewSelectionModel<Product> selectionModel, Label alertLabel) {
        if (selectionModel.getSelectedItems().size() != 0) {
            if (market.getMarketType().equals(MarketType.RESTAURANT)) {
                ArrayList<Product> selectedProducts = new ArrayList<>();
                selectedProducts.addAll(selectionModel.getSelectedItems());
                int[] number = new int[selectedProducts.size()];
                for (int i = 0; i < number.length; i++) {
                    number[i] = 1;
                }
                engine.getOrderService().addOrder(new Order((User) account, market, selectedProducts,number));
                alertLabel.setTextFill(Color.GREEN);
                alertLabel.setText("Successfully Ordered");
            } else {
                borderPane.setCenter(showFinalizingOrder(selectionModel));
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
            Slider slider = new Slider(selectedProducts.get(i).getAvailabilityInt() != 0 ? 1 : 0, selectedProducts.get(i).getProductType().equals(ProductType.FRUIT) ? selectedProducts.get(i).getAvailabilityInt()/5 : selectedProducts.get(i).getAvailabilityInt(),0);
            slider.setShowTickLabels(true);
            Label label= new Label("" +Math.round(slider.getMin()));
            labels[i] = label;
            HBox tempHBox = new HBox(slider,label);
            listView2.getItems().add(tempHBox);
            slider.valueProperty().addListener((observableValue, number, t1) -> label.setText("" + Math.round(t1.intValue())));
        }
        borderPane.setTop(new HBox(listView,listView2));
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(market.getSchedule());
        Label costLabel = new Label("Final Cost: ");
        Button orderButton = new Button("Order");
        Label alertLabel = new Label("");
        orderButton.setDisable(true);
        choiceBox.setOnAction(Event ->{
            Map.Entry<String,Integer> temp = (Map.Entry<String, Integer>) choiceBox.getValue();
            int[] counter = new int[selectedProducts.size()];
            for (int i = 0; i < counter.length; i++) {
                counter[i] = Integer.parseInt(labels[i].getText());
            }
            Order newOrder = new Order((User) account, market, selectedProducts, counter);
            Double costTemp = engine.getOrderService().calculateCostOfOrder(newOrder,temp.getValue());
            newOrder.setCost(costTemp);
            costLabel.setText("Final Cost: " + costTemp);
            choiceBoxUpdate(newOrder,temp,alertLabel,orderButton);
        });

        Label period = new Label("Periods Of time: ");
        HBox bottomHBox = new HBox(period,choiceBox,costLabel,orderButton,alertLabel);
        bottomHBox.setSpacing(10);
        borderPane.setCenter(bottomHBox);
        return borderPane;
    }

    private void choiceBoxUpdate(Order newOrder, Map.Entry<String,Integer> temp,Label alertLabel,Button orderButton) {
        orderButton.setDisable(false);
        orderButton.setOnAction(Event2 -> {
            temp.setValue(temp.getValue() + 1);
            engine.getOrderService().addOrder(newOrder);
            alertLabel.setTextFill(Color.GREEN);
            alertLabel.setText("Successfully Ordered");
        });
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
