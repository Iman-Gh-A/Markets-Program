package ir.ac.kntu;

import com.devskiller.jfairy.producer.company.Company;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.logic.LoginMenu;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.markets.*;
import ir.ac.kntu.model.classes.persons.*;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.products.Fruit;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.classes.products.SuperProduct;
import ir.ac.kntu.model.enums.AccountType;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.RestaurantType;
import ir.ac.kntu.model.enums.VehicleType;
import ir.ac.kntu.util.RandomHelper;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;


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
        Button createButton = new Button("Create");
        ProgressIndicator pb = new ProgressIndicator();

        startButton.setOnAction(Event-> loginMenu.getLoginPain());

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(createButton,startButton,pb);

        createButton.setOnAction(e-> {
            createFakeObject(engine);
            pb.setProgress(1);
        });

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
        createFakeObjectBig(engine);
        createFakeOrders(engine);
        createFakeOrders(engine);
    }

    private void createFakeAccounts(Engine engine) {
        Account userTemp1 = new User("1111111111","Iman Ghasemi Arani", "iman", "imangh","Iran-Tehran","09-207-410-787", false);
        Account adminTemp1 = new Admin("2222222222","Admin", "admin", "admin");
        Account managerTemp1 = new Manager("3333333333","Ali Emami Manager", "ali", "alie");
        Account managerTemp2 = new Manager("4444444444","Reza Emami Manager", "Temp", "1234");
        engine.getAccountService().addAccount(userTemp1);
        engine.getAccountService().addAccount(adminTemp1);
        engine.getAccountService().addAccount(managerTemp1);
        engine.getAccountService().addAccount(managerTemp2);
        Market restaurantTemp1 = new Restaurant("Yas","Iran-Tehran", RestaurantType.MEDIUM,"sunday");
        engine.getMarketService().addMarket(restaurantTemp1);
        Product foodTemp1 = new Food("Kabab",20000.0);
        Product foodTemp3 = new Food("Pasta",15000.0);
        Product foodTemp5 = new Food("soop",10000.0);
        restaurantTemp1.addProduct(foodTemp1);
        restaurantTemp1.addProduct(foodTemp3);
        restaurantTemp1.addProduct(foodTemp5);
        ((Manager)managerTemp1).setMarket(restaurantTemp1);
        Market superTemp1 = new SuperMarket("shabanehrozzy poonak", "iran-tehran-poonak",8,20, 2);
        engine.getMarketService().addMarket(superTemp1);
        Product superProductTemp1 = new SuperProduct("Mast",14000.0,20);
        Product superProductTemp3 = new SuperProduct("Adams",25000.0,10);
        Product superProductTemp5 = new SuperProduct("pofak",10000.0,0);
        superTemp1.addProduct(superProductTemp1);
        superTemp1.addProduct(superProductTemp3);
        superTemp1.addProduct(superProductTemp5);
        ((Manager)managerTemp2).setMarket(superTemp1);
    }

    public void createFakeObjectBig(Engine engine) {

        for (int i = 0; i < RandomHelper.getRandomInt(30,10000); i++) {
            Person fairyPerson = Fairy.create().person();
            Account newAccountFake;
            switch (new Random().nextInt(3)) {
                case 0:
                    newAccountFake = new Admin(RandomHelper.getRandomID(),fairyPerson.getFullName(), fairyPerson.getFirstName(), fairyPerson.getPassword());
                    break;
                case 1:
                    newAccountFake = new User(RandomHelper.getRandomID(),fairyPerson.getFullName(), fairyPerson.getFirstName(), fairyPerson.getPassword(),fairyPerson.getAddress().getAddressLine1(), RandomHelper.getRandomPhone(),new Random().nextBoolean());
                    break;
                default:
                    newAccountFake = new Manager(RandomHelper.getRandomID(),fairyPerson.getFullName(), fairyPerson.getFirstName(), fairyPerson.getPassword());
                    break;
            }
            try {
                engine.getAccountService().addAccount(newAccountFake);
                System.out.println(newAccountFake.toStringForKnow());
                if (newAccountFake.getAccountType().equals(AccountType.MANAGER)) {
                    Market marketTemp = createMarketFake();
                    engine.getMarketService().addMarket(marketTemp);
                    ((Manager)newAccountFake).setMarket(marketTemp);
                }
            } catch (Exception e) {
                i--;
            }

        }
    }

    private Market createMarketFake() {
        Person fairyPerson = Fairy.create().person();
        Company companyFairy = Fairy.create().company();
        Market newMarketFake = new Restaurant(companyFairy.getName().trim(), fairyPerson.getAddress().getAddressLine2(),RestaurantType.MEDIUM,"Sunday Monday");
        switch (new Random().nextInt(3)) {
            case 0:
                int temp = RandomHelper.getRandomInt(0,22);
                newMarketFake = new SuperMarket(companyFairy.getName().trim(),fairyPerson.getAddress().getAddressLine2(),temp,RandomHelper.getRandomInt(temp+1,23),RandomHelper.getRandomInt(1,10));
                break;
            case 1:
                int temp2 = RandomHelper.getRandomInt(0,22);
                newMarketFake = new FruitShop(companyFairy.getName().trim(),fairyPerson.getAddress().getAddressLine2(),temp2,RandomHelper.getRandomInt(temp2+1,23),RandomHelper.getRandomInt(1,10));
                break;
            default:
                break;
        }
        addProductToMarket(newMarketFake);
        addDeliveryToMarket(newMarketFake);
        return newMarketFake;
    }

    private void addProductToMarket(Market market) {
        switch (market.getMarketType()) {
            case RESTAURANT:
                for (int i = 0; i < RandomHelper.getRandomInt(0,10); i++) {
                    Product product = new Food(Fairy.create().person().getFirstName(),RandomHelper.getRandomCost());
                    market.addProduct(product);
                }
                break;
            case SUPER:
                for (int i = 0; i < RandomHelper.getRandomInt(0,10); i++) {
                    Product product = new SuperProduct(Fairy.create().person().getFirstName(),RandomHelper.getRandomCost(),RandomHelper.getRandomInt(1,1000));
                    market.addProduct(product);
                }
                break;
            default:
                for (int i = 0; i < RandomHelper.getRandomInt(0,10); i++) {
                    Product product = new Fruit(Fairy.create().person().getFirstName(),RandomHelper.getRandomCost(),RandomHelper.getRandomInt(1,1000));
                    market.addProduct(product);
                }
                break;
        }
    }

    private void addDeliveryToMarket(Market market) {
        for (int i = 0; i < RandomHelper.getRandomInt(1,10); i++) {
            Delivery delivery = new Delivery(Fairy.create().person().getFullName(),RandomHelper.getRandomID(),new Random().nextBoolean() ? VehicleType.CAR : VehicleType.MOTOR);
            market.addDelivery(delivery);
        }
    }

    private void createFakeOrders(Engine engine) {
        for (int i = 0; i < RandomHelper.getRandomInt(20,50); i++) {
            User orderingUser = (User) engine.getAccountService().getListOfAccountByType(AccountType.USER).get(new Random().nextInt(engine.getAccountService().getListOfAccountByType(AccountType.USER).size()));
            Market orderingMarket = engine.getMarketService().getMarkets().get(new Random().nextInt(engine.getMarketService().getMarkets().size()));
            ArrayList<Product> orderingProducts = new ArrayList<>();
            for (Product currentProduct: orderingMarket.getProducts()) {
                if (new Random().nextBoolean() && currentProduct.getAvailabilityInt() != 0) {
                    orderingProducts.add(currentProduct);
                }
            }
            int[] numbers = new int[orderingProducts.size()];
            Map.Entry<String,Integer> time = null;
            if (orderingMarket.getMarketType().equals(MarketType.RESTAURANT)) {
                Arrays.fill(numbers, 1);
            } else {
                Arrays.fill(numbers, 1);
                if (orderingMarket.getSchedule().size() != 0) {
                    time = orderingMarket.getSchedule().get(new Random().nextInt(orderingMarket.getSchedule().size()));
                }
            }
            try {
                Order newFakeOrder = new Order(orderingUser,orderingMarket,orderingProducts,numbers,time);
                engine.getOrderService().addOrder(newFakeOrder);
            } catch (Exception e) {
                i--;
            }
        }
    }
}