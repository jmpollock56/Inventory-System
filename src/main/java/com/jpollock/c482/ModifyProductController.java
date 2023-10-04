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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/** This class controls the Modify Product page and allows the users to change the information of the selected Product. */
public class ModifyProductController implements Initializable {

    private final ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /** TextField for partSearchField. */
    @FXML public TextField partSearchField;

    /** TableView for allPartTable. */
    @FXML public TableView<Part> allPartTable;

    /** TableColumn for partId. */
    @FXML public TableColumn<Part, Integer> partId;

    /** TableColumn for partName. */
    @FXML public TableColumn<Part, String> partName;

    /** TableColumn for partStock. */
    @FXML public TableColumn<Part, Integer> partStock;

    /** TableColumn for partCost. */
    @FXML public TableColumn<Part, Double> partCost;

    /** TableColumn for assocPartId. */
    @FXML public TableColumn<Part, Integer> assocPartId;

    /** TableColumn for assocPartName. */
    @FXML public TableColumn<Part, String> assocPartName;

    /** TableColumn for assocPartCost. */
    @FXML public TableColumn<Part, Double> assocPartCost;

    /** TableColumn for assocPartStock. */
    @FXML public TableColumn<Part, Integer> assocPartStock;

    /** TableView for assocPartTable. */
    @FXML public TableView<Part> assocPartTable;

    /** TextField for productNameField. */
    @FXML public TextField productNameField;

    /** TextField for productIdField. */
    @FXML public TextField productIdField;

    /** TextField for productStockField. */
    @FXML public TextField productStockField;

    /** TextField for productMinField. */
    @FXML public TextField productMinField;

    /** TextField for productMaxField. */
    @FXML public TextField productMaxField;

    /** TextField for productCostField. */
    @FXML public TextField productCostField;

    /** integer to hold the index of the Product object that was selected. */
    int productIndex;

    Product modProd;

    /** Integer used to hold the productId. */
    int productId;

    /** This method is used to populate the associated Parts table.
     * @param index Product Index
     * @param selectedProduct Product Object that was selected.
     *
     */
    public void populateTables(int index, Product selectedProduct){
        productIndex = index;
        modProd = selectedProduct;

        associatedPartsList.addAll(modProd.getAllAssociatedParts());

        productId = modProd.getId();
        String productName = modProd.getName();
        double productCost = modProd.getPrice();
        int productStock = modProd.getStock();
        int productMin = modProd.getMin();
        int productMax = modProd.getMax();

        productIdField.setText(Integer.toString(productId));
        productNameField.setText(productName);
        productStockField.setText(Integer.toString(productStock));
        productCostField.setText(Double.toString(productCost));
        productMaxField.setText(Integer.toString(productMax));
        productMinField.setText(Integer.toString(productMin));

        assocPartTable.setItems(associatedPartsList);

        assocPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    /** This method is used to load the Part information into the All Parts table.
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the 'Modify a Product Page!'");

        allPartTable.setItems(Inventory.getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** This method is used to cancel the current operations and send the user back to the main page of the app.
     * @param event button click. */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /** This method is used to take the selected part from the all Parts table and add it to the Product's associated
     * parts table.
     * @param actionEvent button click
     */
    public void addPart(ActionEvent actionEvent) {
        Part selectedPart = allPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select a Part.");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        associatedPartsList.add(selectedPart);

        assocPartTable.setItems(associatedPartsList);

        assocPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is used to remove a part from the Products associated parts table.
     * @param actionEvent button click
     */
    public void removePart(ActionEvent actionEvent) {
        Part selectedPart = (Part) assocPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Select a Part You Would Like to Delete");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal of Part");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure that you want to remove " + selectedPart.getName() + " from the Product?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
                associatedPartsList.remove(selectedPart);
                assocPartTable.setItems(associatedPartsList);

        } else {
            Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            notDeleted.setTitle("Confirmation");
            notDeleted.setContentText("Part was not removed");
            notDeleted.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> confirm = notDeleted.showAndWait();
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

    /** This method is used to save the current Product and send it to be updated in the Inventory.
     * @param actionEvent button click
     */
    public void saveModifyProduct(ActionEvent actionEvent) throws IOException {
        if ((productNameField.getText().isEmpty() || productNameField.getText().isEmpty() || productCostField.getText().isEmpty() || productMaxField.getText().isEmpty() || productMinField.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Missing");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill Out All of the Fields");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(productStockField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Inventory should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(productMinField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Minimum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isInteger(productMaxField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Maximum should be a number");

            alert.getButtonTypes().setAll(ButtonType.OK);

            Optional<ButtonType> result = alert.showAndWait();

            return;
        }

        if (!isDouble(productCostField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Price/ Cost should be a number");

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



        Product modProduct = new Product(productId, newProdName, newProdCost, newProdStock, newProdMin, newProdMax);

        for (Part part: associatedPartsList){
            modProduct.addAssociatedPart(part);
        }

        Inventory.updateProduct(productIndex, modProduct);



        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /** This method is used to search for parts based on either name or ID. */
    public void partSearch(){
        String q = partSearchField.getText();

        ObservableList<Part> parts = Inventory.lookupPart(q);

        if (!parts.isEmpty()){
            allPartTable .setItems(parts);

        }

        if (parts.isEmpty()) {
            try {
                int partId = Integer.parseInt(q);
                Part numPart = (Part) Inventory.lookupPart(partId);
                if (numPart != null) {
                    parts.add(numPart);
                    allPartTable.setItems(parts);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Unable to find Part");
                    alert.setHeaderText("Error");
                    alert.setContentText("Unable to find Part based on the ID");

                    alert.getButtonTypes().setAll(ButtonType.OK);

                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Unable to find Part");
                alert.setHeaderText("Error");
                alert.setContentText("Unable to find Part based on the ID");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }
}
