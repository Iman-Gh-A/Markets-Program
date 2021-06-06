package ir.ac.kntu.logic;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.enums.AccountType;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.OrderStatus;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.IllegalArgumentException;


public class UserMenu implements AccountMenu{

    private final User account;
    private final Engine engine;
    public UserMenu(Account account, Engine engine) {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.USER)) {
            throw new IllegalArgumentException("Account is not user");
        }
        this.account = (User) account;
    }

    @Override
    public Pane showBaseMenu() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(700);
        borderPane.setPrefHeight(500);
        Label labelName = new Label("User\t\t\n"+account.getName()+"\t");
        Button profileButton = new Button("Profile");
        profileButton.setPrefWidth(120);
        Button orderingButton = new Button("Ordering");
        orderingButton.setPrefWidth(120);
        Button historyButton = new Button("Order history");
        historyButton.setPrefWidth(120);
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(120);
        VBox leftVBox = new VBox(labelName,profileButton,orderingButton,historyButton,exitButton);
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        borderPane.setLeft(leftVBox);
        borderPane.setCenter(showProfileAndEdit());

        profileButton.setOnAction(Event-> borderPane.setCenter(showProfileAndEdit()));
        orderingButton.setOnAction(Event-> borderPane.setCenter(showOrderingMenu()));
        historyButton.setOnAction(Event -> borderPane.setCenter(showOrderHistoryMenu()));
        exitButton.setOnAction(Event-> new LoginMenu(engine).getLoginPain());

        return borderPane;
    }

    private Pane showProfileAndEdit() {
        BorderPane borderPane = new BorderPane();
        Label topLabel = new Label("View and Edit your information");
        topLabel.setLayoutY(50);
        Label labelError = new Label("");
        Pane pane = new Pane(topLabel,labelError);
        pane.setPrefHeight(100);
        borderPane.setTop(pane);
        TextField nameField = new TextField(account.getName());
        TextField idField = new TextField(account.getId());
        TextField usernameField = new TextField(account.getUsername());
        PasswordField passwordField = new PasswordField();
        TextField addressField = new TextField(account.getAddress());
        TextField phoneField = new TextField(account.getPhone());
        Label labelName = new Label("Name");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelID = new Label("ID");
        Label labelAddress = new Label("Address");
        Label labelPhone = new Label("Phone");
        Button saveButton = new Button("Save");
        VBox leftLeftVBox = new VBox(labelName,labelUsername,labelPhone,labelAddress,saveButton);
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,usernameField,phoneField,addressField);
        leftVBox.setSpacing(10);
        VBox rightRightVBox = new VBox(labelID,labelPassword);
        rightRightVBox.setSpacing(25);
        VBox rightVBox = new VBox(idField,passwordField);
        rightVBox.setSpacing(10);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightRightVBox,rightVBox);
        hBox.setSpacing(5);
        borderPane.setCenter(hBox);
        saveButton.setOnAction(Event ->{
            try {
                User tempUser = new User(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),"1234",addressField.getText().trim(),phoneField.getText().trim(), account.isSpecialAccount());
                engine.getAccountService().updateUser(account,tempUser,passwordField.getText());
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully Change");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        return borderPane;
    }

    private Pane showOrderingMenu() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab restaurantTab = new Tab("Restaurants");
        restaurantTab.setClosable(false);
        Tab superTab = new Tab("Super Markets");
        superTab.setClosable(false);
        Tab shopTab = new Tab("Fruit Shop");
        shopTab.setClosable(false);
        tabPane.getTabs().addAll(restaurantTab,superTab,shopTab);
        borderPane.setCenter(tabPane);
        MarketsMenu marketsMenu = new MarketsMenu(engine,account);
        restaurantTab.setContent(marketsMenu.showMarketsForOrdering(MarketType.RESTAURANT));
        superTab.setContent(marketsMenu.showMarketsForOrdering(MarketType.SUPER));
        shopTab.setContent(marketsMenu.showMarketsForOrdering(MarketType.FRUITSHOP));
        return borderPane;
    }

    private Pane showOrderHistoryMenu() {
        BorderPane borderPane = new BorderPane();
        ListView<Order> listView = new ListView<>();
        listView.setPrefHeight(200);
        VBox topVBox = new VBox(new Label("Order History"));
        listView.getItems().addAll(account.getOrders());
        listView.setPlaceholder(new Label("Nothing"));
        topVBox.getChildren().add(listView);
        borderPane.setTop(topVBox);
        ChoiceBox<Integer> selectRate = new ChoiceBox<>();
        selectRate.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
        TextArea commentArea = new TextArea();
        commentArea.setPromptText("Write your comment related about selected order");
        Button commentingButton = new Button("Comment");
        Label alertLabel = new Label("");
        borderPane.setBottom(new VBox(new HBox(new Label("Rate"),selectRate,alertLabel),commentArea,commentingButton));
        commentingButton.setOnAction(Event-> {
            ObservableList<Order> temp = listView.getSelectionModel().getSelectedItems();
            try {
                if (temp.size() != 0) {
                    Order order = temp.get(0);
                    if (selectRate.getValue() == null ) {
                        throw new IllegalArgumentException("the rate box shouldn't be blank.");
                    }
                    if (!order.getStatus().equals(OrderStatus.DELIVERED)) {
                        throw new IllegalArgumentException("this order doesn't delivered.");
                    }
                    order.addComment(new Comment(commentArea.getText().trim(),selectRate.getValue(), account,  order.getMarket(), order.getProducts()));
                    alertLabel.setTextFill(Color.GREEN);
                    alertLabel.setText("Successfully Commenting");
                }
            } catch (Exception e) {
                alertLabel.setTextFill(Color.RED);
                alertLabel.setText(e.getMessage());
            }
        });
        return borderPane;
    }
}
