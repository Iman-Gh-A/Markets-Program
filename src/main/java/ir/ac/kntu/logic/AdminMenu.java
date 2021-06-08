package ir.ac.kntu.logic;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.enums.AccountType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AdminMenu implements AccountMenu{
    private final Account account;
    private final Engine engine;

    public AdminMenu(Account account, Engine engine) {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.ADMIN)) {
            throw new IllegalArgumentException("Account is not admin");
        }
        this.account = account;
    }
    @Override
    public Pane showBaseMenu() {
        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneInner = new BorderPane();
        borderPaneInner.setPrefWidth(700);
        borderPaneInner.setPrefHeight(500);
        Label labelName = new Label("Admin\t\t\n"+account.getName()+"\t");
        Button profileButton = new Button("Profile");
        profileButton.setPrefWidth(120);
        Button accountsButton = new Button("Accounts");
        accountsButton.setPrefWidth(120);
        Button ordersButton = new Button("Orders");
        ordersButton.setPrefWidth(120);
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(120);
        VBox leftVBox = new VBox(labelName,profileButton,accountsButton,ordersButton,exitButton);
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        borderPaneInner.setLeft(leftVBox);
        borderPaneInner.setCenter(showProfileAndEdit());
        profileButton.setOnAction(Event-> borderPaneInner.setCenter(showProfileAndEdit()));
        accountsButton.setOnAction(Event-> borderPaneInner.setCenter(accountsMenu(borderPane)));
        ordersButton.setOnAction(e-> borderPaneInner.setCenter(ordersMenuPane()));
        exitButton.setOnAction(Event-> new LoginMenu(engine).getLoginPain());
        borderPane.setCenter(borderPaneInner);
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
        Label labelName = new Label("Name");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelID = new Label("ID");
        Button saveButton = new Button("Save");
        VBox leftLeftVBox = new VBox(labelName,labelUsername,saveButton);
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,usernameField);
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
                Manager tempUser = new Manager(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),"1234");
                engine.getAccountService().updateManagerOrAdmin(account,tempUser,passwordField.getText());
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully Change");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        return borderPane;
    }

    private Pane accountsMenu(BorderPane borderPaneBase) {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab managersTab = new Tab("Managers");
        managersTab.setClosable(false);
        Tab usersTab = new Tab("Users");
        usersTab.setClosable(false);
        Tab allAccountsTab = new Tab("All Accounts");
        allAccountsTab.setClosable(false);
        tabPane.getTabs().addAll(managersTab,usersTab,allAccountsTab);
        borderPane.setCenter(tabPane);
        managersTab.setContent(accountsMenuPane(AccountType.MANAGER,borderPaneBase));
        usersTab.setContent(accountsMenuPane(AccountType.USER,borderPaneBase));
        allAccountsTab.setContent(accountsMenuPane(null,borderPaneBase));
        return borderPane;
    }

    private Pane accountsMenuPane(AccountType accountType, BorderPane borderPaneBase) {
        ArrayList<Account> relatedAccounts;
        if (accountType != null) {
            relatedAccounts = engine.getAccountService().getListOfAccountByType(accountType);
        } else {
            relatedAccounts = engine.getAccountService().getAccounts();
        }

        ListView<Account> listView = new ListView<>();
        listView.setPlaceholder(new Label("Nothing"));
        listView.getItems().addAll(relatedAccounts);
        Button selectButton = new Button("Select");
        VBox vBox = new VBox(listView,selectButton);
        Button backButton = new Button("Back");
        borderPaneBase.setAlignment(backButton, Pos.CENTER);
        backButton.setRotate(90);
        backButton.setOnAction(e-> {
            borderPaneBase.setLeft(null);
            borderPaneBase.setCenter(showBaseMenu());
        });
        selectButton.setOnAction(e-> {
            if ( listView.getSelectionModel().getSelectedItems().size() != 0 ) {
                Account currentAccount = listView.getSelectionModel().getSelectedItems().get(0);
                if (!currentAccount.getAccountType().equals(AccountType.ADMIN)) {
                    AccountMenu accountMenu;
                    try {
                        accountMenu = new AdminMenu(currentAccount, engine);
                    } catch (Exception e1) {
                        try {
                            accountMenu = new ManagerMenu(currentAccount, engine);
                        } catch (Exception c) {
                            accountMenu = new UserMenu(currentAccount, engine);
                        }
                    }
                    borderPaneBase.setLeft(backButton);
                    borderPaneBase.setCenter(accountMenu.showBaseMenu());
                }
            }
        });
        return vBox;
    }

    private Pane ordersMenuPane() {
        BorderPane borderPane = new BorderPane();
        ArrayList<Order> allOrders = engine.getOrderService().getOrders();
        ListView<Order> listView = new ListView<>();
        listView.setPlaceholder(new Label("Nothing"));
        listView.getItems().addAll(allOrders);
        borderPane.setCenter(listView);
        return  borderPane;
    }


}
