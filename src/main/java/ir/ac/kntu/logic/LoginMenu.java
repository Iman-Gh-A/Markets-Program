package ir.ac.kntu.logic;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.persons.Account;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginMenu {
    private final Engine engine;

    public LoginMenu(Engine engine) {
        this.engine = engine;
    }

    public void getLoginPain() {
        BorderPane borderPane = new BorderPane();
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        VBox leftVBox = new VBox(labelUsername,labelPassword);
        leftVBox.setSpacing(20);
        leftVBox.setAlignment(Pos.CENTER);
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        VBox rightVBox = new VBox(usernameField,passwordField);
        rightVBox.setSpacing(10);
        rightVBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(leftVBox,rightVBox);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        Label labelErrors = new Label();
        Button signInButton = new Button("Sign in");
        Button signUpButton = new Button("Sign up");
        HBox hBoxButton = new HBox(signInButton,signUpButton);
        hBoxButton.setSpacing(30);
        hBoxButton.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hBox,labelErrors,hBoxButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        signInButton.setOnAction(Event->{
            try {
                signInButtonPressed(usernameField.getText().trim(), passwordField.getText());
            } catch (Exception e) {
                labelErrors.setTextFill(Color.RED);
                labelErrors.setText(e.getMessage());
            }
        });

        signUpButton.setOnAction(Event-> {
            SignUpMenu signUpMenu = new SignUpMenu(engine);
            signUpMenu.getSignUpMenu();
        });

        new Main().changeScene(borderPane);
    }

    private void signInButtonPressed(String username, String password) {
        Account currentAccount = engine.getAccountService().searchAccountByUsername(username);
        if (currentAccount != null && currentAccount.getPassword().equals(password)) {
            AccountMenu accountMenu;
            try {
                accountMenu = new AdminMenu(currentAccount, engine);
            } catch (Exception e) {
                try {
                    accountMenu = new ManagerMenu(currentAccount, engine);
                } catch (Exception c) {
                    accountMenu = new UserMenu(currentAccount, engine);
                }
            }
            new Main().changeScene(new Pane(accountMenu.showBaseMenu()));

        } else {
            throw new IllegalArgumentException("The username or password is incorrect");
        }
    }
}
