package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.enums.AccountType;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SignUpMenu {
    private final Engine engine;

    public SignUpMenu(Engine engine) {
        this.engine = engine;
    }

    public void getSignUpMenu() {
        Main main = new Main();
        TextField nameField = new TextField(); nameField.setPromptText("full name"); nameField.setLayoutX(173); nameField.setLayoutY(100);

        TextField usernameField = new TextField();usernameField.setPromptText("username");usernameField.setLayoutX(173);usernameField.setLayoutY(143);

        TextField idField = new TextField(); idField.setPromptText("id"); idField.setLayoutX(440); idField.setLayoutY(100);

        PasswordField passwordField = new PasswordField(); passwordField.setPromptText("password"); passwordField.setLayoutX(173); passwordField.setLayoutY(188);

        Label labelName = new Label("Name"); labelName.setLayoutX(127); labelName.setLayoutY(105);

        Label labelUsername = new Label("Username"); labelUsername.setLayoutX(98); labelUsername.setLayoutY(148);

        Label labelPassword = new Label("Password"); labelPassword.setLayoutX(103); labelPassword.setLayoutY(193);

        Label labelID = new Label("ID"); labelID.setLayoutX(416); labelID.setLayoutY(105);

        Label labelError = new Label(""); labelError.setLayoutX(103); labelError.setLayoutY(242); labelError.setTextFill(Color.RED);

        Label labelType = new Label("Account's type"); labelType.setLayoutX(416); labelType.setLayoutY(148);

        ChoiceBox selectBox = new ChoiceBox(); selectBox.setLayoutX(525); selectBox.setLayoutY(145); selectBox.setPrefWidth(100);
        selectBox.getItems().add(AccountType.ADMIN);
        selectBox.getItems().add(AccountType.USER);
        selectBox.getItems().add(AccountType.MANAGER);

        Button createUserButton = new Button("Create User"); createUserButton.setLayoutX(510); createUserButton.setLayoutY(300);

        Button backButton = new Button("Back"); backButton.setLayoutX(440); backButton.setLayoutY(300);

        createUserButton.setOnAction(Event->{
            try {
                Account newAccount = new Account(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),
                                                passwordField.getText(), (AccountType) selectBox.getValue());
                engine.getAccountService().addAccount(newAccount);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully created, press back and login.");
            } catch (Exception e) {
                labelError.setText(e.getMessage());
            }
        });
        backButton.setOnAction(Event-> {
            LoginMenu loginMenu = new LoginMenu(engine);
            loginMenu.getLoginPain();
        });

        main.changeScene(new Pane(nameField,usernameField,idField,passwordField
                                    ,labelName,labelUsername,labelPassword,labelID,labelError,selectBox,labelType
                                    ,backButton,createUserButton));
    }
}
