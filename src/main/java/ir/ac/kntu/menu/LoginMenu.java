package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Account;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginMenu {
    private final Engine engine;

    public LoginMenu(Engine engine) {
        this.engine = engine;
    }

    public void getLoginPain() {
        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(200);
        labelUsername.setLayoutY(200);

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setLayoutX(270);
        usernameField.setLayoutY(195);

        Label labelPassword = new Label("Password");
        labelPassword.setLayoutX(200);
        labelPassword.setLayoutY(230);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setLayoutX(270);
        passwordField.setLayoutY(225);

        Label labelErrors = new Label();
        labelErrors.setLayoutX(200);
        labelErrors.setLayoutY(260);
        labelErrors.setTextFill(Color.RED);

        Button signInButton = new Button("Sign in");
        signInButton.setLayoutX(380);
        signInButton.setLayoutY(300);

        Button signUpButton = new Button("Sign up");
        signUpButton.setLayoutX(270);
        signUpButton.setLayoutY(300);

        signInButton.setOnAction(Event->{
            try {
                signInButtonPressed(usernameField.getText().trim(), passwordField.getText());
                labelErrors.setText("");
            } catch (Exception e) {
                labelErrors.setText(e.getMessage());
            }
        });

        signUpButton.setOnAction(Event-> {
            SignUpMenu signUpMenu = new SignUpMenu(engine);
            signUpMenu.getSignUpMenu();
        });

        new Main().changeScene(new Pane(labelUsername,usernameField,labelPassword,passwordField,labelErrors,signInButton,signUpButton));
    }

    private void signInButtonPressed(String username, String password) throws Exception {
        Account currentAccount = engine.getAccountService().searchAccountByUsername(username);
        if (currentAccount != null && currentAccount.getPassword().equals(password)) {
            try {
                AdminMenu adminMenu = new AdminMenu(currentAccount, engine);
                adminMenu.showMenu();
            } catch (IOException e) {
                try {
                    ManagerMenu managerMenu = new ManagerMenu(currentAccount, engine);
                    managerMenu.showMenu();
                } catch (IOException c) {
                    UserMenu userMenu = new UserMenu(currentAccount, engine);
                    userMenu.showMenu();
                }
            }
        } else {
            throw new IllegalArgumentException("The username or password is incorrect");
        }
    }
}
