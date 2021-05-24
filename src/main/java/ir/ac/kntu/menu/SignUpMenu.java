package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.classes.Manager;
import ir.ac.kntu.model.classes.User;
import ir.ac.kntu.model.enums.AccountType;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SignUpMenu {
    private final Engine engine;

    public SignUpMenu() {
        this.engine = new Engine();
    }

    public SignUpMenu(Engine engine) {
        this.engine = engine;
    }

    public ArrayList<Node> getNodesForSignUp() {
        ArrayList<Node> nodes = new ArrayList<>();
        TextField nameField = new TextField();
        nameField.setPromptText("full name");
        nameField.setLayoutX(173);
        nameField.setLayoutY(100);
        nodes.add(nameField);

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setLayoutX(173);
        usernameField.setLayoutY(143);
        nodes.add(usernameField);

        TextField idField = new TextField();
        idField.setPromptText("id");
        idField.setLayoutX(440);
        idField.setLayoutY(100);
        nodes.add(idField);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setLayoutX(173);
        passwordField.setLayoutY(188);
        nodes.add(passwordField);

        Label labelName = new Label("Name");
        labelName.setLayoutX(127);
        labelName.setLayoutY(105);
        nodes.add(labelName);

        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(98);
        labelUsername.setLayoutY(148);
        nodes.add(labelUsername);

        Label labelPassword = new Label("Password");
        labelPassword.setLayoutX(103);
        labelPassword.setLayoutY(193);
        nodes.add(labelPassword);

        Label labelID = new Label("ID");
        labelID.setLayoutX(416);
        labelID.setLayoutY(105);
        nodes.add(labelID);

        Label labelError = new Label("");
        labelError.setLayoutX(103);
        labelError.setLayoutY(310);
        labelError.setTextFill(Color.RED);
        nodes.add(labelError);

        Label labelType = new Label("Account's type");
        labelType.setLayoutX(416);
        labelType.setLayoutY(148);
        nodes.add(labelType);

        TextField addressField = new TextField();
        addressField.setPromptText("address");
        addressField.setLayoutX(173);
        addressField.setLayoutY(275);
        addressField.setPrefWidth(440);
        nodes.add(addressField);

        TextField phoneField = new TextField();
        phoneField.setPromptText("phone");
        phoneField.setLayoutX(173);
        phoneField.setLayoutY(240);
        nodes.add(phoneField);

        Label labelAddress = new Label("Address");
        labelAddress.setLayoutX(110);
        labelAddress.setLayoutY(280);
        nodes.add(labelAddress);

        Label labelPhone = new Label("Phone");
        labelPhone.setLayoutX(127);
        labelPhone.setLayoutY(243);
        nodes.add(labelPhone);

        return nodes;
    }

    public void getSignUpMenu() {
        TextField nameField = new TextField();
        nameField.setPromptText("full name");
        nameField.setLayoutX(173);
        nameField.setLayoutY(100);

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setLayoutX(173);
        usernameField.setLayoutY(143);

        TextField idField = new TextField();
        idField.setPromptText("id");
        idField.setLayoutX(440);
        idField.setLayoutY(100);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setLayoutX(173);
        passwordField.setLayoutY(188);

        Label labelName = new Label("Name");
        labelName.setLayoutX(127);
        labelName.setLayoutY(105);

        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(98);
        labelUsername.setLayoutY(148);

        Label labelPassword = new Label("Password");
        labelPassword.setLayoutX(103);
        labelPassword.setLayoutY(193);

        Label labelID = new Label("ID");
        labelID.setLayoutX(416);
        labelID.setLayoutY(105);

        Label labelError = new Label("");
        labelError.setLayoutX(103);
        labelError.setLayoutY(310);
        labelError.setTextFill(Color.RED);

        Label labelType = new Label("Account's type");
        labelType.setLayoutX(416);
        labelType.setLayoutY(148);

        TextField addressField = new TextField();
        addressField.setPromptText("address");
        addressField.setLayoutX(173);
        addressField.setLayoutY(275);
        addressField.setPrefWidth(440);

        TextField phoneField = new TextField();
        phoneField.setPromptText("phone");
        phoneField.setLayoutX(173);
        phoneField.setLayoutY(240);

        Label labelAddress = new Label("Address");
        labelAddress.setLayoutX(110);
        labelAddress.setLayoutY(280);

        Label labelPhone = new Label("Phone");
        labelPhone.setLayoutX(127);
        labelPhone.setLayoutY(243);

        ChoiceBox selectBox = new ChoiceBox();
        selectBox.setLayoutX(525);
        selectBox.setLayoutY(145);
        selectBox.setPrefWidth(100);
        selectBox.getItems().addAll(AccountType.values());

        Button createUserButton = new Button("Create User");
        createUserButton.setLayoutX(510);
        createUserButton.setLayoutY(350);

        Button backButton = new Button("Back");
        backButton.setLayoutX(440);
        backButton.setLayoutY(350);

        Pane pane = new Pane(nameField,usernameField,idField,passwordField,labelName,labelUsername,labelPassword,labelID,labelError,selectBox,labelType,createUserButton,backButton);

        selectBox.setOnAction(Event->{
            if (selectBox.getValue().equals(AccountType.USER)) {
                pane.getChildren().addAll(addressField,phoneField,labelAddress,labelPhone);
            } else {
                pane.getChildren().removeAll(addressField,phoneField,labelAddress,labelPhone);
            }
        });

        createUserButton.setOnAction(Event->{
            try {
                Account accountTemp = new Account(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),passwordField.getText(),(AccountType) selectBox.getValue());
                createUserButtonPressed(labelError,accountTemp,addressField.getText().trim(), phoneField.getText().trim());
            } catch (IOException e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });

        backButton.setOnAction(Event-> {
            LoginMenu loginMenu = new LoginMenu(engine);
            loginMenu.getLoginPain();
        });

        new Main().changeScene(pane);
    }

    private void createUserButtonPressed(Label labelError,Account accountTemp,String address,String phone) {
        try {
            if (accountTemp.getAccountType() == null) {
                throw new IOException("The account's type shouldn't be blank.");
            }
            Account newAccount;
            if (accountTemp.getAccountType().equals(AccountType.USER)) {
                newAccount = new User(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword(),accountTemp.getAccountType(),address,phone);
            } else if(accountTemp.getAccountType().equals(AccountType.MANAGER)) {
                newAccount = new Manager(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword(),accountTemp.getAccountType());
            } else {
                newAccount = new Account(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword(),accountTemp.getAccountType());
            }
            engine.getAccountService().addAccount(newAccount);
            labelError.setTextFill(Color.GREEN);
            labelError.setText("Successfully created, press back and login.");
        } catch (Exception e) {
            labelError.setTextFill(Color.RED);
            labelError.setText(e.getMessage());
        }
    }
}
