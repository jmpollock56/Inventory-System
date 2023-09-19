package com.example.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPartController  implements Initializable {


   @FXML public RadioButton inHouseRadio;
    @FXML public RadioButton outsourcedRadio;
    @FXML public Label changeText;
    @FXML public TextField partId;
    @FXML public TextField partName;
    @FXML public TextField partInv;
    @FXML public TextField partCost;
    @FXML public TextField partMax;
    @FXML public TextField partMin;
    @FXML public TextField partSwitch;
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


    public void savePart(ActionEvent actionEvent) {

        Random random = new Random();

        int randomId = random.nextInt(9000) + 1000;
        if (inHouseRadio.isSelected()){
            String partNameVal = partName.getText();
            int partInvVal = Integer.parseInt(partInv.getText());
            int partCostVal = Integer.parseInt(partCost.getText());
            int partMaxVal = Integer.parseInt(partMax.getText());
            int partMinVal = Integer.parseInt(partMin.getText());
            int partMachineId = Integer.parseInt(partSwitch.getText());

            Inventory.addPart(new InHouse(1, "name", 1,1,1,1,1));
        } else if (outsourcedRadio.isSelected()) {

        }
    }
}


