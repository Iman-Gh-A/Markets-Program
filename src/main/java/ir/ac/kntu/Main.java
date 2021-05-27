package ir.ac.kntu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.menu.LoginMenu;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.markets.Restaurant;
import ir.ac.kntu.model.classes.markets.SuperMarket;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.classes.products.SuperProduct;
import ir.ac.kntu.model.enums.AccountType;
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
        createFakeAccounts(engine);
        createFakeRestaurants(engine);
        createFakeSuperMarkets(engine);
    }

    private void createFakeAccounts(Engine engine) {
        Account userTemp1 = new User("1111111111","Iman Ghasemi Arani", "iman", "imangh", AccountType.USER,"Iran-Tehran","09-207-410-787");
        Account adminTemp1 = new Account("2222222222","Admin", "admin", "admin", AccountType.ADMIN);
        Account managerTemp1 = new Manager("3333333333","Ali Emami Manager", "ali", "alie", AccountType.MANAGER);
        engine.getAccountService().addAccount(userTemp1);
        engine.getAccountService().addAccount(adminTemp1);
        engine.getAccountService().addAccount(managerTemp1);
    }

    private void createFakeRestaurants(Engine engine) {
        Market restaurantTemp1 = new Restaurant("Yas","Iran-Tehran", RestaurantType.MEDIUM,"sunday");
        Market restaurantTemp2 = new Restaurant("Paris","Iran-Tehran", RestaurantType.LUXURY,"saturday");
        Market restaurantTemp3 = new Restaurant("Baradaran","Iran-Tehran", RestaurantType.ECONOMIC,"monday sunday friday");
        Market restaurantTemp4 = new Restaurant("Yasian","Iran-Tehran", RestaurantType.LUXURY,"monday sunday friday");
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

    private void createFakeSuperMarkets(Engine engine) {
        Market superTemp1 = new SuperMarket("shabanehrozzy poonak", "iran-tehran-poonak", "8","20");
        Market superTemp2 = new SuperMarket("darbar", "iran-tehran", "5","20");
        Market superTemp3 = new SuperMarket("Azadi", "iran", "12","20");
        Market superTemp4 = new SuperMarket("iran", "iran-tehran", "19","20");
        engine.getMarketService().addMarket(superTemp1);
        engine.getMarketService().addMarket(superTemp2);
        engine.getMarketService().addMarket(superTemp3);
        engine.getMarketService().addMarket(superTemp4);
        Product superProductTemp1 = new SuperProduct("Mast",14.6,"20");
        Product superProductTemp2 = new SuperProduct("Paneer",7.5,"15");
        Product superProductTemp3 = new SuperProduct("Adams",25.0,"10");
        Product superProductTemp4 = new SuperProduct("Sheer",15.1,"30");
        Product superProductTemp5 = new SuperProduct("pofak",10.0,"0");
        Product superProductTemp6 = new SuperProduct("Chips",12.0,"2");
        superTemp1.addProduct(superProductTemp1);
        superTemp1.addProduct(superProductTemp3);
        superTemp1.addProduct(superProductTemp5);
        superTemp2.addProduct(superProductTemp2);
        superTemp2.addProduct(superProductTemp6);
        superTemp3.addProduct(superProductTemp4);
    }

}