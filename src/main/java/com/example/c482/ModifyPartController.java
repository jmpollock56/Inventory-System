package com.example.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    public RadioButton modInHouseRadio;
    public RadioButton modOutsourcedRadio;
    public Part modifablePart;


    @FXML public TextField modPartName;
    @FXML public TextField modPartInv;
    @FXML public TextField modPartMax;
    @FXML public TextField modPartCost;
    @FXML public TextField modPartSwitch;
    @FXML public TextField modPartMin;
    @FXML public TextField modPartId;

    int index = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Modify a Part Page!'");

    }
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

    public void saveModifyPart(ActionEvent actionEvent){
        System.out.println(index);
        int newPartId = Integer.parseInt(modPartId.getText());
        String newPartName = modPartName.getText();
        int newPartInv = Integer.parseInt(modPartInv.getText());
        int newPartCost = Integer.parseInt(modPartCost.getText());
        int newPartMin = Integer.parseInt(modPartMin.getText());
        int newPartMax = Integer.parseInt(modPartMin.getText());

        if(modifablePart instanceof InHouse){
            int newPartMachineId = Integer.parseInt(modPartSwitch.getText());
            InHouse modifiedInPart = new InHouse(newPartId, newPartName, newPartCost, newPartInv, newPartMin, newPartMax, newPartMachineId);
            Inventory.updatePart(index, modifiedInPart);

        } else {
            String newPartCompanyName = modPartSwitch.getText();

            Outsourced modifiedOutPart = new Outsourced(newPartId, newPartName, newPartCost, newPartInv, newPartMin, newPartMax, newPartCompanyName);

            Inventory.updatePart(index, modifiedOutPart);
        }
    }


    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
