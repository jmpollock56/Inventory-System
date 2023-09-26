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
    @FXML public static TextField modPartId;

    @FXML public TextField modPartName;
    @FXML public TextField modPartInv;
    @FXML public TextField modPartMax;
    @FXML public TextField modPartCost;
    @FXML public TextField modPartSwitch;
    @FXML public TextField modPartMin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Modify a Part Page!'");


    }
    public static void loadPartData(Part selectedPart){
        modifablePart = selectedPart;
        modPartId.setText(Integer.toString(modifablePart.getId()));
    }


    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
