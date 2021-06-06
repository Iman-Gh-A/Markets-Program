package ir.ac.kntu.logic;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.enums.MarketType;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MarketsMenu {
    private final Account account;
    private final Engine engine;

    public MarketsMenu(Engine engine, Account account) {
        this.account = account;
        this.engine = engine;
    }

    public Pane showMarketsForOrdering(MarketType marketType) {
        ArrayList<Market> relatedMarkets = engine.getMarketService().getListOfMarketByType(marketType,"");
        BorderPane borderPane = new BorderPane();
        BorderPane innerBorderPane = new BorderPane();
        Button searchButton = new Button("Search");
        Button filterBestProductAllMarketsButton = new Button("Search in All Market for Product");
        TextField searchField = new TextField("");
        searchField.setPromptText("name");
        innerBorderPane.setTop(new HBox(searchField,searchButton,filterBestProductAllMarketsButton));
        TableView<Market> tableView = new TableView<>();
        tableView.setPlaceholder(new Label("Nothing"));
        TableColumn<Market,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Market,String> rateColumn = new TableColumn<>("Rate");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        TableColumn<Market,String> commentNumColumn = new TableColumn<>("Comment Number");
        commentNumColumn.setCellValueFactory(new PropertyValueFactory<>("commentsNum"));
        if (marketType.equals(MarketType.RESTAURANT)) {
            TableColumn<Market,String> typeColumn = new TableColumn<>("Type");
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.getColumns().addAll(nameColumn,typeColumn,rateColumn,commentNumColumn);
        } else {
            tableView.getColumns().addAll(nameColumn,rateColumn,commentNumColumn);
        }
        tableView.getItems().addAll(relatedMarkets);
        Button nextButton = new Button("Next");
        Button seeComments = new Button("See Comments");
        innerBorderPane.setCenter(tableView);
        innerBorderPane.setBottom(new HBox(nextButton,seeComments));
        Button backButton = new Button("Back");
        borderPane.setCenter(innerBorderPane);
        borderPane.setBottom(backButton);
        nextButton.setOnAction(Event-> {
            ObservableList<Market> temp = tableView.getSelectionModel().getSelectedItems();
            if (temp.size() != 0) {
                Market selectedMarket = temp.get(0);
                ProductsMenu productsMenu = new ProductsMenu(engine, account, selectedMarket);
                borderPane.setCenter(productsMenu.showProductsOfMarketMenu());
            }
        });
        seeComments.setOnAction(Event-> seeCommentsButtonPressed(tableView.getSelectionModel().getSelectedItems()));
        backButton.setOnAction(Event-> borderPane.setCenter(innerBorderPane));
        searchButton.setOnAction(Event-> {
            ArrayList<Market> searchedMarkets = engine.getMarketService().getListOfMarketByType(marketType,searchField.getText());
            tableView.getItems().clear();
            tableView.getItems().addAll(searchedMarkets);
        });
        filterBestProductAllMarketsButton.setOnAction(e-> {
            ArrayList<Market> searchedMarkets = engine.getMarketService().searchProductByNameBestMarkets(marketType,searchField.getText());
            tableView.getItems().clear();
            tableView.getItems().addAll(searchedMarkets);
        });
        return borderPane;
    }

    private void seeCommentsButtonPressed(ObservableList<Market> temp) {
        if (temp.size() != 0) {
            Stage commentStage = new Stage();
            BorderPane borderPane = new BorderPane();
            ListView<Comment> listView = new ListView<>();
            listView.setPlaceholder(new Label("Nothing"));
            listView.setPrefWidth(500);
            listView.getItems().addAll(temp.get(0).getComments());
            borderPane.setCenter(listView);
            commentStage.setScene(new Scene(borderPane));
            commentStage.show();
        }
    }
}
