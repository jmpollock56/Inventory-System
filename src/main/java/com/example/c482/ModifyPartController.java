package com.example.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    private static Part modifablePart;


    @FXML public static TextField modPartName;
    @FXML public static TextField modPartInv;
    @FXML public static TextField modPartMax;
    @FXML public static TextField modPartCost;
    @FXML public static TextField modPartSwitch;
    @FXML public static TextField modPartMin;
    @FXML public TextField modPartId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Modify a Part Page!'");

    }
    public static void loadPartData(Part selectedPart){

        modifablePart = selectedPart;

        int partId = modifablePart.getId();
        String partName = modifablePart.getName();
        int partInv = modifablePart.getStock();
        double partCost = modifablePart.getPrice();
        int partMax = modifablePart.getMax();
        int partMin = modifablePart.getMin();


        //modPartId.setText(Integer.toString(partId));
        modPartName.setText("Hey");
        modPartInv.setText(Integer.toString(partInv));
        modPartCost.setText(Double.toString(partCost));
        modPartMax.setText(Integer.toString(partMax));
        modPartMin.setText(Integer.toString(partMin));




    }


    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
