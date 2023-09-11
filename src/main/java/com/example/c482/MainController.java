package com.example.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class MainController implements Initializable {

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            System.out.println("I am initialized");
        }



        @FXML
        public Button closeButton;

        @FXML
        public void handleCloseButtonAction(ActionEvent event) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
        @FXML
        public void moveToAddPart(ActionEvent event) throws IOException {
            Parent addPartForm = FXMLLoader.load(getClass().getResource("add-part.fxml"));
            Scene addPartScene = new Scene(addPartForm);
            Stage addPartStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }
        @FXML
        public void moveToModifyPart(ActionEvent event) throws IOException {
            Parent modifyPartForm = FXMLLoader.load(getClass().getResource("modify-part.fxml"));
            Scene modifyPartScene = new Scene(modifyPartForm);
            Stage modifyPartStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();
        }
        @FXML
        public void moveToAddProduct(ActionEvent event) throws IOException {
            Parent addProductForm = FXMLLoader.load(getClass().getResource("add-product.fxml"));
            Scene addProductScene = new Scene(addProductForm);
            Stage addProductStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            addProductStage.setScene(addProductScene);
            addProductStage.show();
        }
        @FXML
        public void moveToModifyProduct(ActionEvent event) throws IOException {
            Parent modifyProductForm = FXMLLoader.load(getClass().getResource("modify-product.fxml"));
            Scene modifyProductScene = new Scene(modifyProductForm);
            Stage modifyProductStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            modifyProductStage.setScene(modifyProductScene);
            modifyProductStage.show();
        }


    }



