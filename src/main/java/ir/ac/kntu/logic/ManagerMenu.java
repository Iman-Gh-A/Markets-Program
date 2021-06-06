package ir.ac.kntu.logic;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.markets.*;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Delivery;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.products.Fruit;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.classes.products.SuperProduct;
import ir.ac.kntu.model.enums.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;
import java.util.stream.IntStream;

public class ManagerMenu implements AccountMenu{
    private final Manager account;
    private final Engine engine;

    public ManagerMenu(Account account, Engine engine) {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.MANAGER)) {
            throw new IllegalArgumentException("Account is not manager");
        }
        this.account = (Manager) account;
    }

    @Override
    public Pane showBaseMenu() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(700);
        borderPane.setPrefHeight(500);
        Label labelName = new Label("Manager\t\t\n"+account.getName()+"\t");
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
        manageMarketButton.setOnAction(Event -> borderPane.setCenter(manageMarket()));
        exitButton.setOnAction(Event-> new LoginMenu(engine).getLoginPain());
        return borderPane;
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
        borderPane.setCenter(centerBorderPaneForCreateMarket(labelError));
        return borderPane;
    }

    private Pane centerBorderPaneForCreateMarket(Label labelError) {
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
        Button createButton = new Button("Create");
        VBox baseVBox = new VBox(hBox,hBoxTemp,createButton);
        baseVBox.setSpacing(10);
        choiceBoxType.setOnAction(Event-> choiceBoxTypeHandler(hBoxTemp,new Label[]{labelWorkHour,labelRestaurantType,labelStartTime,labelEndTime,labelCapacity},
                            new ChoiceBox[]{choiceBoxType,choiceBoxRestaurantType,startTime,endTime,choiceBoxCapacity},workHourField));
        startTime.setOnAction(Event-> {
            endTime.getItems().clear();
            IntStream.range(startTime.getValue() +1, 24).forEach(i -> endTime.getItems().add(i));
        });
        createButton.setOnAction(Event-> createButtonHandler(new ChoiceBox[]{choiceBoxType,choiceBoxRestaurantType,startTime,endTime,choiceBoxCapacity},
                            new TextField[]{nameField,addressField,workHourField},labelError));
        return baseVBox;
    }

    private void createButtonHandler(ChoiceBox[] choiceBoxes, TextField[] textFields, Label labelError) {
        Market newMarket;
        try {
            if (choiceBoxes[0].getValue().equals(MarketType.SUPER)) {
                newMarket = new SuperMarket(textFields[0].getText().trim(),textFields[1].getText().trim(),(Integer) choiceBoxes[2].getValue(),(Integer) choiceBoxes[3].getValue(),(Integer) choiceBoxes[4].getValue());
            } else if (choiceBoxes[0].getValue().equals(MarketType.FRUITSHOP)){
                newMarket = new FruitShop(textFields[0].getText().trim(),textFields[1].getText().trim(),(Integer) choiceBoxes[2].getValue(),(Integer) choiceBoxes[3].getValue(),(Integer) choiceBoxes[4].getValue());
            } else {
                newMarket = new Restaurant(textFields[0].getText().trim(),textFields[1].getText().trim(),(RestaurantType) choiceBoxes[1].getValue(),textFields[2].getText().trim());
            }
            account.setMarket(newMarket);
            engine.getMarketService().addMarket(newMarket);
            labelError.setTextFill(Color.GREEN);
            labelError.setText("Successfully Crested");
        } catch (Exception e) {
            labelError.setTextFill(Color.RED);
            labelError.setText(e.getMessage());
        }
    }

    private void choiceBoxTypeHandler(HBox hBoxTemp,Label[] labels,ChoiceBox[] choiceBoxes,TextField workHourField) {
        if (choiceBoxes[0].getValue().equals(MarketType.RESTAURANT)) {
            hBoxTemp.getChildren().clear();
            hBoxTemp.getChildren().addAll(labels[0],workHourField,labels[1],choiceBoxes[1]);
        } else {
            hBoxTemp.getChildren().clear();
            HBox tempHBox = new HBox(labels[2],choiceBoxes[2],labels[3],choiceBoxes[3]);
            tempHBox.setSpacing(10);
            VBox temp = new VBox(tempHBox,new HBox(labels[4],choiceBoxes[4]));
            hBoxTemp.getChildren().addAll(temp);
        }
    }

    private Pane viewOrEditMarket() {
        BorderPane borderPane = new BorderPane();
        Label labelError = new Label("");
        borderPane.setTop(new VBox(new Label(""),new Label(""),new Label("View or edit your market"),new Label(""),labelError,new Label("")));
        TextField nameField = new TextField(account.getMarket().getName());
        TextField addressField = new TextField(account.getMarket().getAddress());
        VBox leftLeftVBox = new VBox(new Label("Market's Name:"),new Label("Address:"));
        leftLeftVBox.setSpacing(25);
        VBox leftVBox = new VBox(nameField,addressField);
        leftVBox.setSpacing(10);
        VBox rightVBox = new VBox(new Label("Market's type: "+account.getMarket().getMarketType().toString().toLowerCase()));
        rightVBox.setSpacing(25);
        HBox hBoxTemp = new HBox();
        hBoxTemp.setSpacing(15);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox);
        Button saveButton = new Button("Save");
        VBox baseVBox = new VBox(hBox,hBoxTemp,saveButton);
        baseVBox.setSpacing(10);
        borderPane.setCenter(baseVBox);
        TextField workHourField = new TextField();
        ChoiceBox<Integer> startTime = new ChoiceBox<>();
        ChoiceBox<Integer> endTime = new ChoiceBox<>();
        ChoiceBox<Integer> choiceBoxCapacity = new ChoiceBox<>();
        ChoiceBox<RestaurantType> choiceBoxRestaurantType = new ChoiceBox<>();
        if (account.getMarket().getMarketType().equals(MarketType.RESTAURANT)) {
            Restaurant temp = (Restaurant) account.getMarket();
            workHourField = new TextField(temp.getWorkHour());
            choiceBoxRestaurantType.getItems().addAll(RestaurantType.values());
            choiceBoxRestaurantType.setValue(temp.getType());
            hBoxTemp.getChildren().addAll(new Label("Schedule"),workHourField,new Label("Restaurant's Type"),choiceBoxRestaurantType);
        }else {
            ScheduleMarket temp = (ScheduleMarket) account.getMarket();
            startTime.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22);
            startTime.setValue(temp.getStartTime());
            IntStream.range(startTime.getValue() +1, 24).forEach(i -> endTime.getItems().add(i));
            endTime.setValue(temp.getEndTime());
            choiceBoxCapacity.getItems().addAll(2,3,4,5,6,7,8,9,10);
            choiceBoxCapacity.setValue(temp.getCapacity());
            HBox tempHBox = new HBox(new Label("The Start Time of market is"),startTime,new Label("and the end Time is"),endTime);
            tempHBox.setSpacing(10);
            VBox tempVBox = new VBox(tempHBox,new HBox(new Label("Capacity"),choiceBoxCapacity));
            hBoxTemp.getChildren().addAll(tempVBox);
            startTime.setOnAction(Event-> startTimeHandler(endTime,startTime));
        }
        TextField finalWorkHourField = workHourField;
        saveButton.setOnAction(Event-> saveButtonHandler(labelError, new ChoiceBox[]{choiceBoxRestaurantType,startTime,endTime,choiceBoxCapacity}, new TextField[]{nameField,addressField,finalWorkHourField}));
        return borderPane;
    }

    private void startTimeHandler(ChoiceBox<Integer> endTime, ChoiceBox<Integer> startTime) {
        endTime.getItems().clear();
        IntStream.range(startTime.getValue() +1, 24).forEach(i -> endTime.getItems().add(i));
    }

    private void saveButtonHandler(Label labelError,ChoiceBox[] choiceBoxes,TextField[] textFields) {
        Market newMarket;
        try {
            if (account.getMarket().getMarketType().equals(MarketType.SUPER)) {
                newMarket = new SuperMarket(textFields[0].getText().trim(),textFields[1].getText().trim(),(Integer) choiceBoxes[1].getValue(),(Integer) choiceBoxes[2].getValue(),(Integer) choiceBoxes[3].getValue());
            } else if (account.getMarket().getMarketType().equals(MarketType.FRUITSHOP)){
                newMarket = new FruitShop(textFields[0].getText().trim(),textFields[1].getText().trim(),(Integer) choiceBoxes[1].getValue(),(Integer) choiceBoxes[2].getValue(),(Integer) choiceBoxes[3].getValue());
            } else {
                newMarket = new Restaurant(textFields[0].getText().trim(),textFields[1].getText().trim(),(RestaurantType) choiceBoxes[0].getValue(), textFields[2].getText().trim());
            }
            engine.getMarketService().updateMarket(account.getMarket(),newMarket);
            labelError.setTextFill(Color.GREEN);
            labelError.setText("Successfully updated");
        } catch (Exception e) {
            labelError.setTextFill(Color.RED);
            labelError.setText(e.getMessage());
        }
    }

    private Pane manageMarket() {
        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneInner = new BorderPane();
        ListView<VBox> listViewOrders = new ListView<>();
        listViewOrders.setPlaceholder(new Label("Nothing"));
        listViewOrders.setPrefHeight(140);
        Label labelError = new Label("");
        for (Order currentOrder : account.getMarket().getOrders()) {
            if (!currentOrder.getStatus().equals(OrderStatus.DELIVERED)) {
                Button changeStatus = new Button("Change status to next step");
                changeStatus.setOnAction(e-> labelError.setText("\t" + currentOrder.updateStatus(account.getMarket().getDeliveries().size() > 0 ? account.getMarket().getDeliveries().get(new Random().nextInt(account.getMarket().getDeliveries().size())) : null)));
                listViewOrders.getItems().add(new VBox(new Label(currentOrder.toString()),changeStatus));
            } else {
                listViewOrders.getItems().add(new VBox(new Label(currentOrder.toString())));
            }
        }
        VBox vBoxOrderHistory = new VBox(new HBox(new Label("Order History"),labelError),listViewOrders,new Label());
        borderPaneInner.setTop(vBoxOrderHistory);
        ListView<Product> listViewProducts = new ListView<>();
        listViewProducts.setPlaceholder(new Label("Nothing"));
        listViewProducts.setPrefHeight(110);
        listViewProducts.getItems().addAll(account.getMarket().getProducts());
        Button addProductButton = new Button("add Product");
        Button seeCommentsButton = new Button("Comments");
        VBox vBoxProduct = new VBox(new Label("Products"),listViewProducts,new HBox(seeCommentsButton,addProductButton),new Label());
        borderPaneInner.setCenter(vBoxProduct);
        ListView<Delivery> listViewDeliveries = new ListView<>();
        listViewDeliveries.setPlaceholder(new Label("Nothing"));
        listViewDeliveries.setPrefHeight(110);
        listViewDeliveries.getItems().addAll(account.getMarket().getDeliveries());
        Button addDeliveryButton = new Button("add Delivery");
        Button editDeliveryButton = new Button("Edit Delivery");
        VBox vBoxDelivery = new VBox(new Label("Deliveries"),listViewDeliveries,new HBox(addDeliveryButton,editDeliveryButton),new Label());
        borderPaneInner.setBottom(vBoxDelivery);
        borderPane.setCenter(borderPaneInner);
        Button backButton = new Button("Back");
        borderPane.setBottom(backButton);
        editDeliveryButton.setOnAction(e-> {
            if (listViewDeliveries.getSelectionModel().getSelectedItems().size() > 0) {
                borderPaneInner.setBottom(editDeliveryButtonHandler(listViewDeliveries.getSelectionModel().getSelectedItems().get(0)));
            }
        });
        addProductButton.setOnAction(e-> borderPaneInner.setCenter(addProductButtonHandler()));
        addDeliveryButton.setOnAction(e-> borderPaneInner.setBottom(addDeliveryButtonHandler()));
        seeCommentsButton.setOnAction(e-> seeCommentsButtonPressed(listViewProducts.getSelectionModel().getSelectedItems()));
        backButton.setOnAction(e-> backButtonHandler(new ListView[]{listViewProducts,listViewDeliveries},new VBox[]{vBoxOrderHistory,vBoxProduct,vBoxDelivery},borderPaneInner));
        return borderPane;
    }

    private void backButtonHandler(ListView[] listViews, VBox[] vBoxes,BorderPane borderPaneInner) {
        borderPaneInner.setTop(vBoxes[0]);
        borderPaneInner.setCenter(vBoxes[1]);
        listViews[0].getItems().clear();
        listViews[0].getItems().addAll(account.getMarket().getProducts());
        borderPaneInner.setBottom(vBoxes[2]);
        listViews[1].getItems().clear();
        listViews[1].getItems().addAll(account.getMarket().getDeliveries());
    }

    private void seeCommentsButtonPressed(ObservableList<Product> temp) {
        if (temp.size() != 0) {
            Stage commentStage = new Stage();
            BorderPane borderPane = new BorderPane();
            ListView<Comment> listView = new ListView<>();
            listView.setPlaceholder(new Label("Nothing"));
            listView.setPrefWidth(500);
            listView.getItems().addAll(temp.get(0).getComments());
            borderPane.setCenter(listView);
            commentStage.setScene(new Scene(borderPane));
            commentStage.show();
        }
    }

    private Pane addProductButtonHandler() {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("add Product"));
        TextField productNameField = new TextField();
        productNameField.setPromptText("name");
        Spinner<Double> costSpinner = new Spinner<>(1000.0,10000000.0,1000,500);
        Button addButton = new Button("add");
        Label labelError = new Label("");
        VBox leftLeftVBox;
        VBox leftVBox;
        Spinner<Integer> counterSpinner = new Spinner<>();
        if (!account.getMarket().getMarketType().equals(MarketType.RESTAURANT)) {
            counterSpinner = new Spinner<>(0,10000,5,5);
            leftLeftVBox = new VBox(new Label("Product' name:"), new Label("Number of Product:"));
            leftVBox = new VBox(productNameField,counterSpinner);
        } else {
            leftLeftVBox = new VBox(new Label("Product' name:"));
            leftVBox= new VBox(productNameField);
        }
        VBox rightVBox = new VBox(new Label("Cost:"));
        VBox rightRightVBox = new VBox(costSpinner,new HBox(addButton,labelError));
        leftLeftVBox.setSpacing(23);
        leftVBox.setSpacing(10);
        rightRightVBox.setSpacing(10);
        rightVBox.setSpacing(23);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        hBox.setSpacing(5);
        borderPane.setCenter(hBox);
        Spinner<Integer> finalCounterSpinner = counterSpinner;
        addButton.setOnAction(e-> {
            try {
                Product newProduct;
                if (account.getMarket().getMarketType().equals(MarketType.RESTAURANT)) {
                    newProduct = new Food(productNameField.getText().trim(),costSpinner.getValue());
                } else if (account.getMarket().getMarketType().equals(MarketType.SUPER)) {
                    newProduct = new SuperProduct(productNameField.getText().trim(),costSpinner.getValue(), finalCounterSpinner.getValue());
                } else {
                    newProduct = new Fruit(productNameField.getText().trim(),costSpinner.getValue(), finalCounterSpinner.getValue());
                }
                account.getMarket().addProduct(newProduct);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("added "+newProduct.getName());
            } catch (Exception exception) {
                labelError.setTextFill(Color.RED);
                labelError.setText(exception.getMessage());
            }
        });
        return borderPane;
    }

    private Pane addDeliveryButtonHandler() {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("add Delivery"));
        Label labelError = new Label("");
        TextField deliveryNameField = new TextField();
        deliveryNameField.setPromptText("name");
        TextField idField = new TextField();
        idField.setPromptText("id");
        ChoiceBox<VehicleType> vehicleTypeChoiceBox = new ChoiceBox<>();
        vehicleTypeChoiceBox.getItems().addAll(VehicleType.values());
        vehicleTypeChoiceBox.setValue(VehicleType.MOTOR);
        Button addButton = new Button("add");
        VBox leftLeftVBox = new VBox(new Label("Name:"),new Label("Vehicle's Type"),new Label(),new Label());
        VBox leftVBox = new VBox(deliveryNameField,vehicleTypeChoiceBox);
        VBox rightVBox = new VBox(new Label("ID:"));
        VBox rightRightVBox = new VBox(idField,new HBox(addButton,labelError));
        leftLeftVBox.setSpacing(23);
        leftVBox.setSpacing(10);
        rightVBox.setSpacing(23);
        rightRightVBox.setSpacing(10);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        borderPane.setCenter(hBox);
        hBox.setSpacing(5);
        addButton.setOnAction(e-> {
            try {
                Delivery newDelivery = new Delivery(deliveryNameField.getText().trim(),idField.getText().trim(),vehicleTypeChoiceBox.getValue());
                account.getMarket().addDelivery(newDelivery);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("added " + newDelivery.getName());
            } catch (Exception exception) {
                labelError.setTextFill(Color.RED);
                labelError.setText(exception.getMessage());
            }
        });
        return borderPane;
    }

    private Pane editDeliveryButtonHandler(Delivery delivery) {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("edit Delivery"));
        Label labelError = new Label("");
        TextField deliveryNameField = new TextField(delivery.getName());
        deliveryNameField.setPromptText("name");
        TextField idField = new TextField(delivery.getId());
        idField.setPromptText("id");
        ChoiceBox<VehicleType> vehicleTypeChoiceBox = new ChoiceBox<>();
        vehicleTypeChoiceBox.getItems().addAll(VehicleType.values());
        vehicleTypeChoiceBox.setValue(delivery.getVehicleType());
        Button addButton = new Button("Save");
        VBox leftLeftVBox = new VBox(new Label("Name:"),new Label("Vehicle's Type"),new Label(),new Label());
        VBox leftVBox = new VBox(deliveryNameField,vehicleTypeChoiceBox);
        VBox rightVBox = new VBox(new Label("ID:"));
        VBox rightRightVBox = new VBox(idField,new HBox(addButton,labelError));
        leftLeftVBox.setSpacing(23);
        leftVBox.setSpacing(10);
        rightVBox.setSpacing(23);
        rightRightVBox.setSpacing(10);
        HBox hBox = new HBox(leftLeftVBox,leftVBox,rightVBox,rightRightVBox);
        borderPane.setCenter(hBox);
        hBox.setSpacing(5);
        addButton.setOnAction(e-> {
            try {
                Delivery newDelivery = new Delivery(deliveryNameField.getText().trim(),idField.getText().trim(),vehicleTypeChoiceBox.getValue());
                account.getMarket().updateDelivery(delivery,newDelivery);
                labelError.setTextFill(Color.GREEN);
                labelError.setText("updated");
            } catch (Exception exception) {
                labelError.setTextFill(Color.RED);
                labelError.setText(exception.getMessage());
            }
        });
        return borderPane;
    }

}
