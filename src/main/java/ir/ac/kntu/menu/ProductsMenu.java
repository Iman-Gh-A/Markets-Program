package ir.ac.kntu.menu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Product;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
        Label alertLabel = new Label("");
        innerBorderPane.setCenter(tableView);
        innerBorderPane.setBottom(new HBox(orderButton,alertLabel));
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setCenter(innerBorderPane);
        borderPane.setCenter(borderPane2);

        orderButton.setOnAction(Event-> {
            if (selectionModel.getSelectedItems().size() != 0) {
                ArrayList<Product> selectedProducts= new ArrayList<>();
                selectedProducts.addAll(selectionModel.getSelectedItems());
                engine.getOrderService().addOrder(new Order((User) account,market,selectedProducts));
                alertLabel.setTextFill(Color.GREEN);
                alertLabel.setText("Successfully Ordered");
            }
        });
        searchButton.setOnAction(Event-> {
            ArrayList<Product> searchedProducts = market.searchByName(searchField.getText());
            tableView.getItems().clear();
            tableView.getItems().addAll(searchedProducts);
        });
        return borderPane;
    }
}
