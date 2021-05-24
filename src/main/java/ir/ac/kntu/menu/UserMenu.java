package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.classes.User;
import ir.ac.kntu.model.enums.AccountType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;


public class UserMenu {

    private final User account;
    private final Engine engine;
    public UserMenu(Account account, Engine engine) throws Exception {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.USER)) {
            throw new Exception("Account is not user");
        }
        this.account = (User) account;
    }

    public void showMenu() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(700);
        borderPane.setPrefHeight(500);
        borderPane.setCenter(showProfileAndEdit());
        Label labelName = new Label("\t"+account.getName()+"\t");
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(100);
        VBox leftVBox = new VBox(labelName,exitButton);
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(5);
        borderPane.setLeft(leftVBox);


        exitButton.setOnAction(Event-> {
            new LoginMenu(engine).getLoginPain(); });
        new Main().changeScene(new Pane(borderPane));
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
                User tempUser = new User(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),"1234",AccountType.USER,addressField.getText().trim(),phoneField.getText().trim());
                updateUser(tempUser,passwordField.getText());
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully Change");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        return borderPane;
    }

    private void updateUser(User user,String password) throws IOException {
        if (engine.getAccountService().searchAccountByID(user.getId()) != account && engine.getAccountService().searchAccountByID(user.getId()) != null) {
            throw new IOException("The ID has already been used.");
        }
        if (engine.getAccountService().searchAccountByUsername(user.getUsername()) != account && engine.getAccountService().searchAccountByUsername(user.getUsername()) != null) {
            throw new IOException("The username has already been used.");
        }
        account.setName(user.getName());
        account.setId(user.getId());
        account.setUsername(user.getUsername());
        account.setPhone(user.getPhone());
        account.setAddress(user.getAddress());
        if (!password.equals("")) {
            account.setPassword(password);
        }
    }
}
