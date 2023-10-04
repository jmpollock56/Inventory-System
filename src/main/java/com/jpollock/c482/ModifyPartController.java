package com.jpollock.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

/** This class is used to control the Modify Part form so that the user is able to edit the Part they have selected. */
public class ModifyPartController implements Initializable {

    /** RadioButton for modInHouseRadio. */
    @FXML public RadioButton modInHouseRadio;

    /** RadioButton for modOutsourcedRadio. */
    @FXML public RadioButton modOutsourcedRadio;

    /** Part Object created to be initialized with the user selected Part. */
    Part modifablePart;

    /** TextField for modPartName. */
    @FXML public TextField modPartName;

    /** TextField for modPartInv. */
    @FXML public TextField modPartInv;

    /** TextField for modPartMax. */
    @FXML public TextField modPartMax;

    /** TextField for modPartCost. */
    @FXML public TextField modPartCost;

    /** TextField for modPartSwitch. */
    @FXML public TextField modPartSwitch;

    /** TextField for modPartMin. */
    @FXML public TextField modPartMin;

    /** TextField for modPartId. */
    @FXML public TextField modPartId;

    /** Label for switchField. */
    @FXML public Label switchField;

    /** int for the index of the selected Part. */
    int index = 0;


    /** This method is used to initialize the Modify Part page.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /** This method is used to load the Part data into their correct TextFields so that the user is able to see what they
     * might need to change.
     * @param selectedPart user selected part
     * @param partIndex part index
     */
    public void loadPartData(int partIndex, Part selectedPart){
        modifablePart = selectedPart;
        index = partIndex;
        if (selectedPart instanceof InHouse){
            modInHouseRadio.setSelected(true);

            InHouse inHousePart = (InHouse) selectedPart;


            int partId = modifablePart.getId();
            String partName = modifablePart.getName();
            int partInv = modifablePart.getStock();
            double partCost = modifablePart.getPrice();
            int partMax = modifablePart.getMax();
            int partMin = modifablePart.getMin();
            int partMachineId = inHousePart.getMachineId();

            modPartId.setText(Integer.toString(partId));
            modPartName.setText(partName);
            modPartInv.setText(Integer.toString(partInv));
            modPartCost.setText(Double.toString(partCost));
            modPartMax.setText(Integer.toString(partMax));
            modPartMin.setText(Integer.toString(partMin));
            modPartSwitch.setText(Integer.toString(partMachineId));

        } else if (selectedPart instanceof Outsourced){
            modOutsourcedRadio.setSelected(true);
            switchField.setText("Company Name");
            Outsourced outsourcedPart = (Outsourced) selectedPart;

            int partId = modifablePart.getId();
            String partName = modifablePart.getName();
            int partInv = modifablePart.getStock();
            double partCost = modifablePart.getPrice();
            int partMax = modifablePart.getMax();
            int partMin = modifablePart.getMin();
            String partCompanyName = outsourcedPart.getCompanyName();


            modPartId.setText(Integer.toString(partId));
            modPartName.setText(partName);
            modPartInv.setText(Integer.toString(partInv));
            modPartCost.setText(Double.toString(partCost));
            modPartMax.setText(Integer.toString(partMax));
            modPartMin.setText(Integer.toString(partMin));
            modPartSwitch.setText(partCompanyName);

        }




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

    /** This method is used to save the modified part and send it off to be updated in the Inventory.
     * @param actionEvent button click. */
    public void saveModifyPart(ActionEvent actionEvent) throws IOException {

        if ((modPartName.getText().isEmpty() || modPartInv.getText().isEmpty() || modPartCost.getText().isEmpty() || modPartMax.getText().isEmpty() || modPartMin.getText().isEmpty() || switchField.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Missing");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill Out All of the Fields");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(modPartInv.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(modPartMin.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Minimum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(modPartMax.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Maximum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isDouble(modPartCost.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Price/ Cost should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        int newPartId = Integer.parseInt(modPartId.getText());
        String newPartName = modPartName.getText();
        int newPartInv = Integer.parseInt(modPartInv.getText());;
        int newPartMin = Integer.parseInt(modPartMin.getText());;
        int newPartMax = Integer.parseInt(modPartMax.getText());;
        double newPartCost = Double.parseDouble(modPartCost.getText());;

        if ((newPartMin > newPartMax)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Minimum is higher than Maximum");
            alert.setHeaderText("Warning");
            alert.setContentText("The Minimum stock should be lower than the Maximum stock");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;

        }

        if((newPartInv > newPartMin) && (newPartMax < newPartInv)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory Should be between the Min and Max values");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if(modInHouseRadio.isSelected()){
            if(!isInteger(modPartSwitch.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText("Warning");
                alert.setContentText("Machine ID should be a number");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            int newPartMachineId = Integer.parseInt(modPartSwitch.getText());

            InHouse modifiedInPart = new InHouse(newPartId, newPartName, newPartCost, newPartInv, newPartMin, newPartMax, newPartMachineId);

            Inventory.updatePart(index, modifiedInPart);
            System.out.println(Inventory.getAllParts());

        } else if (modOutsourcedRadio.isSelected()){
            String newPartCompanyName = modPartSwitch.getText();

            Outsourced modifiedOutPart = new Outsourced(newPartId, newPartName, newPartCost, newPartInv, newPartMin, newPartMax, newPartCompanyName);

            Inventory.updatePart(index, modifiedOutPart);
            System.out.println(Inventory.getAllParts());
        }

        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();

    }

    /** This method is used to change the Label Text on the switchField based on a RadioButton click.
     * @param actionEvent RadioButton selection. */
    public void inHouseRadioClick(ActionEvent actionEvent) {
        switchField.setText("Machine ID");
    }

    /** This method is used to change the Label Text on the switchField based on a RadioButton click.
     * @param actionEvent RadioButton selection. */
    public void outsourcedRadioClick(ActionEvent actionEvent) {
        switchField.setText("Company Name");
    }

    /** This method is used to cancel the current operations and send the user back to the main page of the app.
     * @param event button click. */
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
