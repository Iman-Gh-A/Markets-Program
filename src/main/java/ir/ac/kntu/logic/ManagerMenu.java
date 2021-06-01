package ir.ac.kntu.logic;

import ir.ac.kntu.Main;
import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.markets.*;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.enums.AccountType;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.RestaurantType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.stream.IntStream;

public class ManagerMenu {
    private final Manager account;
    private final Engine engine;

    public ManagerMenu(Account account, Engine engine) {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.MANAGER)) {
            throw new IllegalArgumentException("Account is not manager");
        }
        this.account = (Manager) account;
    }

    public void showBaseMenu() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(700);
        borderPane.setPrefHeight(500);
        Label labelName = new Label("\t"+account.getName()+"\t");
        Button profileButton = new Button("Profile");
        profileButton.setPrefWidth(120);
        Button marketButton = new Button("Market");
        marketButton.setPrefWidth(120);
        Button manageMarketButton = new Button("Manage Market");
        manageMarketButton.setPrefWidth(120);
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(120);
        VBox leftVBox = new VBox(labelName,profileButton,marketButton,manageMarketButton,exitButton);
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        borderPane.setLeft(leftVBox);
        borderPane.setCenter(showProfileAndEdit());

        profileButton.setOnAction(Event-> borderPane.setCenter(showProfileAndEdit()));
        marketButton.setOnAction(Event-> {
            if (account.getMarket() == null) {
                borderPane.setCenter(createMarket());
            } else {
                borderPane.setCenter(viewOrEditMarket());
            }
        });
        manageMarketButton.setOnAction(Event ->{
//            borderPane.setCenter();
        });
        exitButton.setOnAction(Event-> new LoginMenu(engine).getLoginPain());

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
        Label labelName = new Label("Name");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelID = new Label("ID");
        Button saveButton = new Button("Save");
        VBox leftLeftVBox = new VBox(labelName,labelUsername,saveButton);
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,usernameField);
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
                Manager tempUser = new Manager(idField.getText().trim(),nameField.getText().trim(),usernameField.getText().trim(),"1234");
                engine.getAccountService().updateManager(account,tempUser,passwordField.getText());
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully Change");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        return borderPane;
    }

    private Pane createMarket() {
        BorderPane borderPane = new BorderPane();
        Label topLabel = new Label("Create your market");
        topLabel.setLayoutY(50);
        Label labelError = new Label("");
        Pane pane = new Pane(topLabel,labelError);
        pane.setPrefHeight(100);
        borderPane.setTop(pane);
        Label labelName = new Label("Market's Name:");
        Label labelAddress = new Label("Address:");
        Label labelMarketType = new Label("Market's type");
        Label labelStartTime = new Label("The Start Time of market is");
        Label labelEndTime = new Label("and the end Time is");
        TextField nameField = new TextField();
        nameField.setPromptText("market's name");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        ChoiceBox<MarketType> choiceBoxType = new ChoiceBox<>();
        choiceBoxType.getItems().addAll(MarketType.values());
        ChoiceBox<Integer> startTime = new ChoiceBox<>();
        startTime.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22);
        ChoiceBox<Integer> endTime = new ChoiceBox<>();
        Label labelCapacity = new Label("Capacity");
        ChoiceBox<Integer> choiceBoxCapacity = new ChoiceBox<>();
        choiceBoxCapacity.getItems().addAll(2,3,4,5,6,7,8,9,10);
        choiceBoxCapacity.setValue(2);
        Label labelWorkHour = new Label("Schedule");
        TextField workHourField = new TextField();
        Label labelRestaurantType = new Label("Restaurant's Type");
        ChoiceBox<RestaurantType> choiceBoxRestaurantType = new ChoiceBox<>();
        choiceBoxRestaurantType.getItems().addAll(RestaurantType.values());
        choiceBoxRestaurantType.setValue(RestaurantType.ECONOMIC);
        VBox leftLeftVBox = new VBox(labelName,labelAddress);
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,addressField);
        leftVBox.setSpacing(10);
        VBox rightVBox = new VBox(labelMarketType);
        rightVBox.setSpacing(25);
        VBox rightRightVBox = new VBox(choiceBoxType);
        rightRightVBox.setSpacing(10);
        HBox hBoxTemp = new HBox();
        hBoxTemp.setSpacing(15);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        VBox baseVBox = new VBox(hBox,hBoxTemp);
        baseVBox.setSpacing(10);
        borderPane.setCenter(baseVBox);
        Button createButton = new Button("Create");
        borderPane.setBottom(createButton);
        choiceBoxType.setOnAction(Event-> {
            if (choiceBoxType.getValue().equals(MarketType.RESTAURANT)) {
                hBoxTemp.getChildren().clear();
                hBoxTemp.getChildren().addAll(labelWorkHour,workHourField,labelRestaurantType,choiceBoxRestaurantType);
            } else {
                hBoxTemp.getChildren().clear();
                HBox tempHBox = new HBox(labelStartTime,startTime,labelEndTime,endTime);
                tempHBox.setSpacing(10);
                VBox temp = new VBox(tempHBox,new HBox(labelCapacity,choiceBoxCapacity));
                hBoxTemp.getChildren().addAll(temp);
            }
        });
        startTime.setOnAction(Event-> {
            endTime.getItems().clear();
            IntStream.range(startTime.getValue() +1, 24).forEach(i -> endTime.getItems().add(i));
        });
        createButton.setOnAction(Event-> {
            Market newMarket = null;
            try {
                if (choiceBoxType.getValue().equals(MarketType.SUPER)) {
                    newMarket = new SuperMarket(nameField.getText().trim(),addressField.getText().trim(),startTime.getValue(),endTime.getValue(),choiceBoxCapacity.getValue());
                } else if (choiceBoxType.getValue().equals(MarketType.FRUITSHOP)){
                    newMarket = new FruitShop(nameField.getText().trim(),addressField.getText().trim(),startTime.getValue(),endTime.getValue(),choiceBoxCapacity.getValue());
                } else {
                    newMarket = new Restaurant(nameField.getText().trim(),addressField.getText().trim(),choiceBoxRestaurantType.getValue(),workHourField.getText().trim());
                }
                account.setMarket(newMarket);
                engine.getMarketService().addMarket(newMarket);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully Crested");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }
        });
        return borderPane;
    }

    private Pane viewOrEditMarket() {
        BorderPane borderPane = new BorderPane();
        Label topLabel = new Label("View or edit your market");
        topLabel.setLayoutY(50);
        Label labelError = new Label("");
        Pane pane = new Pane(topLabel,labelError);
        pane.setPrefHeight(100);
        borderPane.setTop(pane);
        Label labelName = new Label("Market's Name:");
        Label labelAddress = new Label("Address:");
        Label labelMarketType = new Label("Market's type: "+account.getMarket().getMarketType().toString().toLowerCase());
        Label labelStartTime = new Label("The Start Time of market is");
        Label labelEndTime = new Label("and the end Time is");
        TextField nameField = new TextField(account.getMarket().getName());
        TextField addressField = new TextField(account.getMarket().getAddress());

        VBox leftLeftVBox = new VBox(labelName,labelAddress);
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,addressField);
        leftVBox.setSpacing(10);
        VBox rightVBox = new VBox(labelMarketType);
        rightVBox.setSpacing(25);

        HBox hBoxTemp = new HBox();
        hBoxTemp.setSpacing(15);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox);
        VBox baseVBox = new VBox(hBox,hBoxTemp);

        TextField workHourField = new TextField();
        ChoiceBox<Integer> startTime = new ChoiceBox<>();
        ChoiceBox<Integer> endTime = new ChoiceBox<>();
        ChoiceBox<Integer> choiceBoxCapacity = new ChoiceBox<>();
        ChoiceBox<RestaurantType> choiceBoxRestaurantType = new ChoiceBox<>();

        if (account.getMarket().getMarketType().equals(MarketType.RESTAURANT)) {
            Restaurant temp = (Restaurant) account.getMarket();
            Label labelWorkHour = new Label("Schedule");
            workHourField = new TextField(temp.getWorkHour());
            Label labelRestaurantType = new Label("Restaurant's Type");
            choiceBoxRestaurantType.getItems().addAll(RestaurantType.values());
            choiceBoxRestaurantType.setValue(temp.getType());
            hBoxTemp.getChildren().addAll(labelWorkHour,workHourField,labelRestaurantType,choiceBoxRestaurantType);

        }else {
            ScheduleMarket temp = (ScheduleMarket) account.getMarket();
            startTime.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22);
            startTime.setValue(temp.getStartTime());
            startTime.setValue(temp.getEndTime());
            Label labelCapacity = new Label("Capacity");
            choiceBoxCapacity.getItems().addAll(2,3,4,5,6,7,8,9,10);
            choiceBoxCapacity.setValue(temp.getCapacity());
            HBox tempHBox = new HBox(labelStartTime,startTime,labelEndTime,endTime);
            tempHBox.setSpacing(10);
            VBox tempVBox = new VBox(tempHBox,new HBox(labelCapacity,choiceBoxCapacity));
            hBoxTemp.getChildren().addAll(tempVBox);
            startTime.setOnAction(Event-> {
                endTime.getItems().clear();
                IntStream.range(startTime.getValue() +1, 24).forEach(i -> endTime.getItems().add(i));
            });
        }

        baseVBox.setSpacing(10);
        borderPane.setCenter(baseVBox);
        Button saveButton = new Button("Save");
        borderPane.setBottom(saveButton);

        TextField finalWorkHourField = workHourField;
        saveButton.setOnAction(Event-> {
            Market newMarket;
            try {
                if (account.getMarket().getMarketType().equals(MarketType.SUPER)) {
                    newMarket = new SuperMarket(nameField.getText().trim(),addressField.getText().trim(),startTime.getValue(),endTime.getValue(),choiceBoxCapacity.getValue());
                } else if (account.getMarket().getMarketType().equals(MarketType.FRUITSHOP)){
                    newMarket = new FruitShop(nameField.getText().trim(),addressField.getText().trim(),startTime.getValue(),endTime.getValue(),choiceBoxCapacity.getValue());
                } else {
                    newMarket = new Restaurant(nameField.getText().trim(),addressField.getText().trim(),choiceBoxRestaurantType.getValue(), finalWorkHourField.getText().trim());
                }
                engine.getMarketService().updateMarket(account.getMarket(),newMarket);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("Successfully updated");
            } catch (Exception e) {
                labelError.setTextFill(Color.RED);
                labelError.setText(e.getMessage());
            }

        });
        return borderPane;
    }
}
