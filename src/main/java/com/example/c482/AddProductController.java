package com.example.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    public TableView allPartTable;
    public TableColumn allPartId;
    public TableColumn allPartName;
    public TableColumn allPartInv;
    public TableColumn allPartCost;
    public TableView associatedPartTable;
    public TableColumn selectedPartId;
    public TableColumn selectedPartName;
    public TableColumn selectedPartInv;
    public TableColumn selectedPartCost;
    public TextField productNameField;
    public TextField productInvField;
    public TextField productCostField;
    public TextField productMaxField;
    public TextField productMinField;

    int randomProdId;
    String prodName;
    int prodStock;
    double prodCost;
    int prodMin;
    int prodMax;

    Product currentProduct = new Product(0, "", 0.0, 0, 0, 0);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Add a Product Page!'");
        allPartTable.setItems(Inventory.getAllParts());

        allPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }



    public void saveNewProduct(ActionEvent actionEvent) throws IOException {
        Random random = new Random();
        randomProdId = random.nextInt(90000) + 10000;

        if ((productNameField.getText().isEmpty() || productNameField.getText().isEmpty() || productCostField.getText().isEmpty() || productMaxField.getText().isEmpty() || productMinField.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Missing");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill Out All of the Fields");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        prodName = productNameField.getText();
        prodStock = Integer.parseInt(productInvField.getText());
        prodCost = Double.parseDouble(productCostField.getText());
        prodMax = Integer.parseInt(productMaxField.getText());
        prodMin = Integer.parseInt(productMinField.getText());

        if (prodMin > prodMax){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Minimum is higher than Maximum");
            alert.setHeaderText("Warning");
            alert.setContentText("The Minimum stock should be lower than the Maximum stock");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;

        }

        if((prodMin > prodStock) || (prodMax < prodStock)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory Should be between the Min and Max values");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }



        currentProduct = new Product(randomProdId, prodName, prodCost, prodStock, prodMax, prodMin);

        Inventory.addProduct(currentProduct);

        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public void addPart(ActionEvent actionEvent) {

        Part selectedPart = (Part) allPartTable.getSelectionModel().getSelectedItem();

        currentProduct.addAssociatedPart(selectedPart);
        System.out.println(currentProduct.getAssociatedParts());
        associatedPartTable.setItems(currentProduct.getAssociatedParts());

        selectedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selectedPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    public void removeAssocPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedPartTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal of Part");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure that you want to remove " + selectedPart.getName() + " from the Product?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            currentProduct.deleteAssociatedPart(selectedPart);
        } else {
            Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            notDeleted.setTitle("Confirmation");
            notDeleted.setContentText("Part was not removed");
            notDeleted.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> confirm = notDeleted.showAndWait();
        }
    }


}
