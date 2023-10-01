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
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    public TextField partSearch;
    public TableView allPartTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partStock;
    public TableColumn partCost;
    public TableColumn assocPartId;
    public TableColumn assocPartName;
    public TableColumn assocPartCost;
    public TableColumn assocPartStock;
    public TableView assocPartTable;
    public TextField productNameField;
    public TextField productIdField;
    public TextField productStockField;
    public TextField productMinField;
    public TextField productMaxField;
    public TextField productCostField;

    int productIndex;
    Product product;
    int productId;

    public void populateTables(int index, Product selectedProduct){
        productIndex = index;
        product = selectedProduct;

        productId = product.getId();
        String productName = product.getName();
        double productCost = product.getPrice();
        int productStock = product.getStock();
        int productMin = product.getMin();
        int productMax = product.getMax();

        productIdField.setText(Integer.toString(productId));
        productNameField.setText(productName);
        productStockField.setText(Integer.toString(productStock));
        productCostField.setText(Double.toString(productCost));
        productMaxField.setText(Integer.toString(productMax));
        productMinField.setText(Integer.toString(productMin));



        System.out.println(product.getAssociatedParts());

        assocPartTable.setItems(product.getAssociatedParts());

        assocPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Modify a Product Page!'");

        allPartTable.setItems(Inventory.getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCost.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public void addPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) allPartTable.getSelectionModel().getSelectedItem();

        product.addAssociatedPart(selectedPart);
        System.out.println(product.getAssociatedParts());
        assocPartTable.setItems(product.getAssociatedParts());

        assocPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void removePart(ActionEvent actionEvent) {
        Part selectedPart = (Part) assocPartTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal of Part");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure that you want to remove " + selectedPart.getName() + " from the Product?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            product.deleteAssociatedPart(selectedPart);
        } else {
            Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            notDeleted.setTitle("Confirmation");
            notDeleted.setContentText("Part was not removed");
            notDeleted.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> confirm = notDeleted.showAndWait();
        }
    }

    public void saveModifyPart(ActionEvent actionEvent) throws IOException {
        if ((productNameField.getText().isEmpty() || productNameField.getText().isEmpty() || productCostField.getText().isEmpty() || productMaxField.getText().isEmpty() || productMinField.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Missing");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill Out All of the Fields");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        String newProdName = productNameField.getText();
        int newProdStock = Integer.parseInt(productStockField.getText());
        double newProdCost = Double.parseDouble(productCostField.getText());
        int newProdMax = Integer.parseInt(productMaxField.getText());
        int newProdMin = Integer.parseInt(productMinField.getText());

        if (newProdMin > newProdMax){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Minimum is higher than Maximum");
            alert.setHeaderText("Warning");
            alert.setContentText("The Minimum stock should be lower than the Maximum stock");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;

        }

        if((newProdMin > newProdStock) || (newProdMax < newProdStock)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory Should be between the Min and Max values");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }



        product = new Product(productId, newProdName, newProdCost, newProdStock, newProdMax, newProdMin);

        Inventory.addProduct(product);

        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
