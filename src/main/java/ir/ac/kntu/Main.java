package ir.ac.kntu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.menu.LoginMenu;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.markets.Restaurant;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.AccountType;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.RestaurantType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

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

    private void createFakeObject(Engine engine) {
        Account userTemp1 = new User("1111111111","Iman Ghasemi Arani", "iman", "imangh", AccountType.USER,"Iran-Tehran","09-207-410-787");
        Account adminTemp1 = new Account("2222222222","Admin", "admin", "admin", AccountType.ADMIN);
        Account managerTemp1 = new Manager("3333333333","Ali Emami Manager", "ali", "alie", AccountType.MANAGER);
        engine.getAccountService().addAccount(userTemp1);
        engine.getAccountService().addAccount(adminTemp1);
        engine.getAccountService().addAccount(managerTemp1);
        Market restaurantTemp1 = new Restaurant("Yas","Iran-Tehran", MarketType.RESTAURANT,true, RestaurantType.MEDIUM,"sunday");
        Market restaurantTemp2 = new Restaurant("Paris","Iran-Tehran", MarketType.RESTAURANT,true, RestaurantType.LUXURY,"saturday");
        Market restaurantTemp3 = new Restaurant("Baradaran","Iran-Tehran", MarketType.RESTAURANT,true, RestaurantType.ECONOMIC,"monday sunday friday");
        Market restaurantTemp4 = new Restaurant("Yasian","Iran-Tehran", MarketType.RESTAURANT,true, RestaurantType.LUXURY,"monday sunday friday");
        engine.getMarketService().addMarket(restaurantTemp1);
        engine.getMarketService().addMarket(restaurantTemp2);
        engine.getMarketService().addMarket(restaurantTemp3);
        engine.getMarketService().addMarket(restaurantTemp4);
        Product foodTemp1 = new Food("Kabab",15.0);
        Product foodTemp2 = new Food("Joje",20.0);
        Product foodTemp3 = new Food("Pasta",10.0);
        Product foodTemp4 = new Food("Morgh",27.3);
        Product foodTemp5 = new Food("soop",5.0);
        restaurantTemp1.addProduct(foodTemp1);
        restaurantTemp1.addProduct(foodTemp3);
        restaurantTemp1.addProduct(foodTemp5);
        restaurantTemp2.addProduct(foodTemp2);
        restaurantTemp2.addProduct(foodTemp4);


    }

}