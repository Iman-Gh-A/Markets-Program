package ir.ac.kntu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.menu.LoginMenu;
import ir.ac.kntu.model.classes.markets.FruitShop;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.markets.Restaurant;
import ir.ac.kntu.model.classes.markets.SuperMarket;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Admin;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.products.Fruit;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.classes.products.SuperProduct;
import ir.ac.kntu.model.enums.RestaurantType;
import ir.ac.kntu.util.RandomHelper;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    private static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        Engine engine = new Engine();
        LoginMenu loginMenu = new LoginMenu(engine);
        Button startButton = new Button("Start");
        ProgressIndicator pb = new ProgressIndicator();
        startButton.setOnAction(Event-> {
            createFakeObject(engine);
            loginMenu.getLoginPain();
        });

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(startButton,pb);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane,700,500);
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
        createFakeFruitShops(engine);
    }

    private void createFakeAccounts(Engine engine) {
        Account userTemp1 = new User("1111111111","Iman Ghasemi Arani", "iman", "imangh","Iran-Tehran","09-207-410-787");
        Account adminTemp1 = new Admin("2222222222","Admin", "admin", "admin");
        Account managerTemp1 = new Manager("3333333333","Ali Emami Manager", "ali", "alie");
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
        Product foodTemp1 = new Food("Kabab",20000.0);
        Product foodTemp2 = new Food("Joje",25000.0);
        Product foodTemp3 = new Food("Pasta",15000.0);
        Product foodTemp4 = new Food("Morgh",30000.0);
        Product foodTemp5 = new Food("soop",10000.0);
        restaurantTemp1.addProduct(foodTemp1);
        restaurantTemp1.addProduct(foodTemp3);
        restaurantTemp1.addProduct(foodTemp5);
        restaurantTemp2.addProduct(foodTemp2);
        restaurantTemp2.addProduct(foodTemp4);
        for (int i = 0; i < RandomHelper.getRandomInt(10,100); i++) {
            RestaurantType temp = null;
            switch (RandomHelper.getRandomInt(1,3)) {
                case 1:
                    temp = RestaurantType.ECONOMIC;
                    break;
                case 2:
                    temp = RestaurantType.LUXURY;
                    break;
                case 3:
                    temp = RestaurantType.MEDIUM;
                    break;
                default:
            }
            Market newRestaurant = new Restaurant(RandomHelper.getRandomWord(),"iran-Tehran",temp,"sunday - monday");
            engine.getMarketService().addMarket(newRestaurant);
            for (int j = 0; j < RandomHelper.getRandomInt(0,10); j++) {
                Product newFood = new Food(RandomHelper.getRandomWord(),RandomHelper.getRandomCost());
                newRestaurant.addProduct(newFood);
            }
        }
    }

    private void createFakeSuperMarkets(Engine engine) {
        Market superTemp1 = new SuperMarket("shabanehrozzy poonak", "iran-tehran-poonak",8,20, 2);
        Market superTemp2 = new SuperMarket("darbar", "iran-tehran", 5,20, 3);
        Market superTemp3 = new SuperMarket("Azadi", "iran", 12,20, 4);
        Market superTemp4 = new SuperMarket("iran", "iran-tehran", 19,20, 5);
        engine.getMarketService().addMarket(superTemp1);
        engine.getMarketService().addMarket(superTemp2);
        engine.getMarketService().addMarket(superTemp3);
        engine.getMarketService().addMarket(superTemp4);
        Product superProductTemp1 = new SuperProduct("Mast",14000.0,20);
        Product superProductTemp2 = new SuperProduct("Paneer",7000.0,15);
        Product superProductTemp3 = new SuperProduct("Adams",25000.0,10);
        Product superProductTemp4 = new SuperProduct("Sheer",10000.0,30);
        Product superProductTemp5 = new SuperProduct("pofak",10000.0,0);
        Product superProductTemp6 = new SuperProduct("Chips",12000.0,2);
        superTemp1.addProduct(superProductTemp1);
        superTemp1.addProduct(superProductTemp3);
        superTemp1.addProduct(superProductTemp5);
        superTemp2.addProduct(superProductTemp2);
        superTemp2.addProduct(superProductTemp6);
        superTemp3.addProduct(superProductTemp4);
        for (int i = 0; i < RandomHelper.getRandomInt(10,100); i++) {
            int start = RandomHelper.getRandomInt(1,22);
            int end = RandomHelper.getRandomInt(start+1,23);
            Market newSuperMarket = new SuperMarket(RandomHelper.getRandomWord(),"iran-tehran-",start,end,2);
            engine.getMarketService().addMarket(newSuperMarket);
            for (int j = 0; j < RandomHelper.getRandomInt(0,10); j++) {
                Product newSuperFood = new SuperProduct(RandomHelper.getRandomWord(),RandomHelper.getRandomCost(),RandomHelper.getRandomInt(0,50));
                newSuperMarket.addProduct(newSuperFood);
            }
        }
    }

    private void createFakeFruitShops(Engine engine) {
        Market fruitShopTemp1 = new FruitShop("Asghar Fruit","iran-mashad",8,20, 2);
        Market fruitShopTemp2 = new FruitShop("Fruit poonak","iran-kojast",6,23, 3);
        Market fruitShopTemp3 = new FruitShop("Iman meveh","iran-mobham",12,13, 4);
        Market fruitShopTemp4 = new FruitShop("Fruits","iran",8,10, 5);
        engine.getMarketService().addMarket(fruitShopTemp1);
        engine.getMarketService().addMarket(fruitShopTemp2);
        engine.getMarketService().addMarket(fruitShopTemp3);
        engine.getMarketService().addMarket(fruitShopTemp4);
        Product fruitTemp1 = new Fruit("apple",1000.0,100);
        Product fruitTemp2 = new Fruit("Orange",2000.0,200);
        Product fruitTemp3 = new Fruit("banana",5000.0,50);
        Product fruitTemp4 = new Fruit("carrot",15000.0,500);
        fruitShopTemp1.addProduct(fruitTemp1);
        fruitShopTemp1.addProduct(fruitTemp3);
        fruitShopTemp1.addProduct(fruitTemp4);
        fruitShopTemp3.addProduct(fruitTemp2);
        for (int i = 0; i < RandomHelper.getRandomInt(10,100); i++) {
            int start = RandomHelper.getRandomInt(1,22);
            int end = RandomHelper.getRandomInt(start+1,23);
            Market newFruitShop = new FruitShop(RandomHelper.getRandomWord(),"iran-tehran-",start,end,2);
            engine.getMarketService().addMarket(newFruitShop);
            for (int j = 0; j < RandomHelper.getRandomInt(0,10); j++) {
                Product newFruit = new Fruit(RandomHelper.getRandomWord(),RandomHelper.getRandomCost(),RandomHelper.getRandomInt(0,50));
                newFruitShop.addProduct(newFruit);
            }
        }
    }

}