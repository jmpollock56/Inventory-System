package com.example.c482;

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

public class AddPartController  implements Initializable {


   @FXML public RadioButton inHouseRadio;
    @FXML public RadioButton outsourcedRadio;
    @FXML public Label changeText;
    @FXML public TextField id;

    @FXML public TextField name;
    @FXML public TextField inventory;
    @FXML public TextField cost;
    @FXML public TextField min;
    @FXML public TextField max;
    @FXML public TextField switchField;
    int generatedPartId;
    int generatedProductId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Add a Part Page!'");
    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }


    public void inHouseRadioClick(ActionEvent actionEvent) {
        changeText.setText("Machine ID");
    }

    public void outsourcedRadioClick(ActionEvent actionEvent) {
        changeText.setText("Company Name");
    }


    public void savePart(ActionEvent actionEvent) throws IOException {

        Random random = new Random();

        int randomId = random.nextInt(9000) + 1000;
        if (inHouseRadio.isSelected()){
            String partNameVal = name.getText();
            int partInvVal = Integer.parseInt(inventory.getText());
            double partCostVal = Double.parseDouble(cost.getText());
            int partMaxVal = Integer.parseInt(max.getText());
            int partMinVal = Integer.parseInt(min.getText());
            int partMachineId = Integer.parseInt(switchField.getText());
            System.out.println(partMinVal);
            System.out.println(partMaxVal);

            if (partMinVal > partMaxVal){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Minimum is higher than Maximum");
                alert.setHeaderText("Warning");
                alert.setContentText("The Minimum stock should be lower than the Maximum stock");

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
            String productNameVal = name.getText();
            int productInvVal = Integer.parseInt(inventory.getText());
            double productCostVal = Double.parseDouble(cost.getText());
            int productMaxVal = Integer.parseInt(max.getText());
            int productMinVal = Integer.parseInt(min.getText());
            String productCompanyName = switchField.getText();

            if (productMinVal > productMaxVal){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Minimum is higher than Maximum");
                alert.setHeaderText("Warning");
                alert.setContentText("The Minimum stock should be lower than the Maximum stock");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;

            }

            Inventory.addPart(new Outsourced(randomId, productNameVal, productCostVal,productInvVal,productMinVal,productMaxVal,productCompanyName));
        }
    }
}


