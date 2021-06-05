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
        TextField[] textFields = returnTextFields();
        ToggleButton specialButton = new ToggleButton("Buy");
        ChoiceBox<AccountType> selectBox = new ChoiceBox<>();
        selectBox.getItems().addAll(AccountType.values());
        VBox leftLeftVBox = new VBox(new Label("Name"),new Label("Username"),new Label("Password"));
        leftLeftVBox.setSpacing(23);
        VBox leftVBox = new VBox(textFields[0],textFields[1],textFields[3]);
        leftVBox.setSpacing(10);
        VBox rightVBox = new VBox(new Label("ID"),new Label("Account's type"));
        rightVBox.setSpacing(23);
        VBox rightRightVBox = new VBox(textFields[2],selectBox);
        rightRightVBox.setSpacing(10);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        hBox.setSpacing(5);
        borderPane.setCenter(new VBox(pane,hBox));
        selectBox.setOnAction(Event->{
            if (selectBox.getValue().equals(AccountType.USER)) {
                leftLeftVBox.getChildren().addAll(new Label("Phone"),new Label("Address"),new Label("Special Account"));
                leftVBox.getChildren().addAll(textFields[5],textFields[4],specialButton);
            } else {
                if (leftLeftVBox.getChildren().size() > 3) {
                    leftLeftVBox.getChildren().removeAll(leftLeftVBox.getChildren().get(leftLeftVBox.getChildren().size() -1),leftLeftVBox.getChildren().get(leftLeftVBox.getChildren().size() -2),leftLeftVBox.getChildren().get(leftLeftVBox.getChildren().size() -3));
                    leftVBox.getChildren().removeAll(leftVBox.getChildren().get(leftVBox.getChildren().size()-3),leftVBox.getChildren().get(leftVBox.getChildren().size()-2),specialButton);
                }
            }
        });
        createUserButton.setOnAction(Event-> createAccount(textFields,labelError,selectBox,specialButton));
        backButton.setOnAction(Event-> new LoginMenu(engine).getLoginPain());
        new Main().changeScene(borderPane);
    }

    private TextField[] returnTextFields() {
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
        return new TextField[]{nameField,usernameField,idField,passwordField,addressField,phoneField};
    }

    private void createAccount(TextField[] textFields,Label labelError,ChoiceBox<AccountType> selectBox,ToggleButton specialButton) {
        try {
            Account accountTemp = new Account(textFields[2].getText().trim(),textFields[0].getText().trim(),textFields[1].getText().trim(),textFields[3].getText(), selectBox.getValue());
            createAccountButtonPressed(specialButton.isSelected(),accountTemp,textFields[4].getText().trim(), textFields[5].getText().trim());
            labelError.setTextFill(Color.GREEN);
            labelError.setText("Successfully created, press back and login.");
        } catch (IllegalArgumentException e) {
            labelError.setTextFill(Color.RED);
            labelError.setText(e.getMessage());
        }
    }

    private void createAccountButtonPressed(boolean specialAccount, Account accountTemp, String address, String phone) {
        Account newAccount;
        if (accountTemp.getAccountType().equals(AccountType.USER)) {
            newAccount = new User(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword(),address,phone, specialAccount);
        } else if(accountTemp.getAccountType().equals(AccountType.MANAGER)) {
            newAccount = new Manager(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword());
        } else {
            newAccount = new Admin(accountTemp.getId(),accountTemp.getName(),accountTemp.getUsername(),accountTemp.getPassword());
        }
        engine.getAccountService().addAccount(newAccount);
    }
}
