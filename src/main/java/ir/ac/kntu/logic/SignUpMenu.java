package ir.ac.kntu.logic;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Admin;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.enums.AccountType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.IllegalArgumentException;

public class SignUpMenu {
    private final Engine engine;

    public SignUpMenu() {
        this.engine = new Engine();
    }

    public SignUpMenu(Engine engine) {
        this.engine = engine;
    }

    public void getSignUpMenu() {
        BorderPane borderPane = new BorderPane();
        Button createUserButton = new Button("Create User");
        createUserButton.setPrefWidth(100);
        Button backButton = new Button("Back");
        backButton.setPrefWidth(100);
        VBox vBox = new VBox(new Label("\t\tSign up\t\t"),createUserButton,backButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setLeft(vBox);
        Label labelError = new Label("");
        Pane pane = new Pane(labelError);
        pane.setPrefHeight(100);
        TextField nameField = new TextField();
        nameField.setPromptText("full name");
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        TextField idField = new TextField();
        idField.setPromptText("id");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        TextField phoneField = new TextField();
        phoneField.setPromptText("phone");
        Label labelName = new Label("Name");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelID = new Label("ID");
        Label labelType = new Label("Account's type");
        Label labelAddress = new Label("Address");
        Label labelPhone = new Label("Phone");
        Label labelSpecialAccount = new Label("Special Account");
        ToggleButton specialButton = new ToggleButton("Buy");
        ChoiceBox selectBox = new ChoiceBox();
        selectBox.getItems().addAll(AccountType.values());
        VBox leftLeftVBox = new VBox(labelName,labelUsername,labelPassword);
        leftLeftVBox.setSpacing(23);
        VBox leftVBox = new VBox(nameField,usernameField,passwordField);
        leftVBox.setSpacing(10);
        VBox rightVBox = new VBox(labelID,labelType);
        rightVBox.setSpacing(23);
        VBox rightRightVBox = new VBox(idField,selectBox);
        rightRightVBox.setSpacing(10);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        hBox.setSpacing(5);
        borderPane.setCenter(new VBox(pane,hBox));
        selectBox.setOnAction(Event->{
            if (selectBox.getValue().equals(AccountType.USER)) {
                leftLeftVBox.getChildren().addAll(labelPhone,labelAddress,labelSpecialAccount);
                leftVBox.getChildren().addAll(phoneField,addressField,specialButton);
            } else {
                leftLeftVBox.getChildren().removeAll(labelPhone,labelAddress,labelSpecialAccount);
                leftVBox.getChildren().removeAll(labelPhone,labelAddress,specialButton);
            }
        });
        createUserButton.setOnAction(Event->{
            try {
                Account accountTemp = new Account(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),passwordField.getText(),(AccountType) selectBox.getValue());
                createUserButtonPressed(specialButton.isSelected(),accountTemp,addressField.getText().trim(), phoneField.getText().trim());
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully created, press back and login.");
            } catch (IllegalArgumentException e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        backButton.setOnAction(Event-> {
            new LoginMenu(engine).getLoginPain();
        });
        new Main().changeScene(borderPane);
    }

    private void createUserButtonPressed(boolean specialAccount,Account accountTemp,String address,String phone) {
        try {
            Account newAccount;
            if (accountTemp.getAccountType().equals(AccountType.USER)) {
                newAccount = new User(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword(),address,phone, specialAccount);
            } else if(accountTemp.getAccountType().equals(AccountType.MANAGER)) {
                newAccount = new Manager(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword());
            } else {
                newAccount = new Admin(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword());
            }
            engine.getAccountService().addAccount(newAccount);

        } catch (Exception e) {
        }
    }
}