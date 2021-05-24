package ir.ac.kntu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.menu.LoginMenu;
import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.enums.AccountType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        Engine engine = new Engine();
        createFakeObject(engine);
        LoginMenu loginMenu = new LoginMenu(engine);

        Button startButton = new Button("Start");
        startButton.setLayoutX(335);
        startButton.setLayoutY(235);

        startButton.setOnAction(Event-> loginMenu.getLoginPain());
        Scene scene = new Scene(new Pane(startButton),700,500);
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(Pane newPane) {
        stage.getScene().setRoot(newPane);
    }

    private void createFakeObject(Engine engine) throws IOException {
        Account userTemp1 = new Account("1111111111","Iman Ghasemi Arani", "iman", "imangh", AccountType.USER);
        Account adminTemp1 = new Account("2222222222","Admin", "admin", "admin", AccountType.ADMIN);
        Account managerTemp1 = new Account("3333333333","Ali Emami Manager", "ali", "alie", AccountType.MANAGER);
        engine.getAccountService().addAccount(userTemp1);
        engine.getAccountService().addAccount(adminTemp1);
        engine.getAccountService().addAccount(managerTemp1);

    }

}