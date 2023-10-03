package com.jpollock.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * The Controller class that provides functionality to the Add Part form.
 */
public class AddPartController  implements Initializable {

    /** RadioButton for inHouseRadio. */
    @FXML private RadioButton inHouseRadio;

    /** RadioButton for outsourcedRadio. */
    @FXML private RadioButton outsourcedRadio;

    /** Label for changeText. */
    @FXML private Label changeText;

    /** TextField for name. */
    @FXML private TextField name;

    /** TextField for inventory. */
    @FXML private TextField inventory;

    /** TextField for cost. */
    @FXML private TextField cost;

    /** TextField for min. */
    @FXML private TextField min;

    /** TextField for max. */
    @FXML private TextField max;

    /** TextField for switchField. */
    @FXML private TextField switchField;

    /**
     * The initialize() method prints a confirmation to the console.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Add a Part Page!'");
    }

    /**
     * This method sends the user back to the main form when clicked, as well as erasing the progress made on the
     * Add Part form.
     * @param event button click
     * @throws IOException
     */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /**
     * This method takes in a Radio Button click and changes the changeText Label to 'Machine ID'.
     * @param actionEvent
     */
    public void inHouseRadioClick(ActionEvent actionEvent) {
        changeText.setText("Machine ID");
    }

    /**
     * This method takes in a Radio Button click and changes the changeText Label to 'Company Name'.
     * @param actionEvent button click
     */
    public void outsourcedRadioClick(ActionEvent actionEvent) {
        changeText.setText("Company Name");
    }

    /**
     * This method takes in text and returns whether that text, when parsed, is an integer.
     * @param text
     */
    public static boolean isInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * This method takes in text and returns whether that text, when parsed, is a double.
     * @param text
     */
    public static boolean isDouble(String text){
        try{
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * This method saves a part to the allParts ObservableList based on whether they are classified as In-House or
     * Outsourced.
     * @param actionEvent
     * @throws IOException
     */
    public void savePart(ActionEvent actionEvent) throws IOException {

        if ((name.getText().isEmpty() || inventory.getText().isEmpty() || cost.getText().isEmpty() || max.getText().isEmpty() || min.getText().isEmpty() || switchField.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Missing");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill Out All of the Fields");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(inventory.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(min.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Minimum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(max.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Maximum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isDouble(cost.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Price/ Cost should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        Random random = new Random();

        int randomId = random.nextInt(9000) + 1000;
        if (inHouseRadio.isSelected()){

            if(!isInteger(switchField.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText("Warning");
                alert.setContentText("Machine ID should be a number");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            String partNameVal = name.getText();
            int partInvVal = Integer.parseInt(inventory.getText());
            double partCostVal = Double.parseDouble(cost.getText());
            int partMaxVal = Integer.parseInt(max.getText());
            int partMinVal = Integer.parseInt(min.getText());
            int partMachineId = Integer.parseInt(switchField.getText());


            if (partMinVal > partMaxVal){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Minimum is higher than Maximum");
                alert.setHeaderText("Warning");
                alert.setContentText("The Minimum stock should be lower than the Maximum stock");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;

            }

            if((partMinVal > partInvVal) || (partMaxVal < partInvVal)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Inventory Error");
                alert.setHeaderText("Warning");
                alert.setContentText("Inventory Should be between the Min and Max values");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            Inventory.addPart(new InHouse(randomId, partNameVal, partCostVal,partInvVal,partMinVal,partMaxVal,partMachineId));

            Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
            Scene mainScene = new Scene(mainForm);
            Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            mainStage.setScene(mainScene);
            mainStage.show();

        } else if (outsourcedRadio.isSelected()) {

            if ((name.getText().isEmpty() || inventory.getText().isEmpty() || cost.getText().isEmpty() || max.getText().isEmpty() || min.getText().isEmpty() || switchField.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Missing");
                alert.setHeaderText("Warning");
                alert.setContentText("Please Fill Out All of the Fields");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            String outsourcedNameVal = name.getText();
            int outsourcedInvVal = Integer.parseInt(inventory.getText());
            double outsourcedCostVal = Double.parseDouble(cost.getText());
            int outsourcedMaxVal = Integer.parseInt(max.getText());
            int outsourcedMinVal = Integer.parseInt(min.getText());
            String outsourcedCompanyName = switchField.getText();

            if (outsourcedMinVal > outsourcedMaxVal){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Minimum is higher than Maximum");
                alert.setHeaderText("Warning");
                alert.setContentText("The Minimum stock should be lower than the Maximum stock");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;

            }

            if((outsourcedMinVal > outsourcedInvVal) || (outsourcedMaxVal < outsourcedInvVal)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Inventory Error");
                alert.setHeaderText("Warning");
                alert.setContentText("Inventory Should be between the Min and Max values");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            Inventory.addPart(new Outsourced(randomId, outsourcedNameVal, outsourcedCostVal,outsourcedInvVal,outsourcedMinVal,outsourcedMaxVal,outsourcedCompanyName));

            Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
            Scene mainScene = new Scene(mainForm);
            Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            mainStage.setScene(mainScene);
            mainStage.show();
        }
    }
}


