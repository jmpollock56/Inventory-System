package com.jpollock.c482;

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
import java.util.Random;
import java.util.ResourceBundle;

/** This class is used to add a new Product the to system. */
public class AddProductController implements Initializable {

    /** TableView for the allPartTable */
    @FXML private TableView allPartTable;

    /** TableColumn for the allPartId */
    @FXML private TableColumn allPartId;

    /** TableColumn for the allPartName */
    @FXML private TableColumn allPartName;

    /** TableColumn for the allPartInv */
    @FXML private TableColumn allPartInv;

    /** TableColumn for the allPartCost */
    @FXML private TableColumn allPartCost;

    /** TableView for the associatedPartTable */
    @FXML private TableView associatedPartTable;

    /** TableColumn for the selectedPartId */
    @FXML public TableColumn selectedPartId;

    /** TableColumn for the selectedPartName */
    @FXML public TableColumn selectedPartName;

    /** TableColumn for the selectedPartInv */
    @FXML public TableColumn selectedPartInv;

    /** TableColumn for the selectedPartCost */
    @FXML public TableColumn selectedPartCost;

    /** TextField for the productNameField */
    @FXML public TextField productNameField;

    /** TextField for the productInvField */
    @FXML public TextField productInvField;

    /** TextField for the productCostField */
    @FXML public TextField productCostField;

    /** TextField for the productMaxField */
    @FXML public TextField productMaxField;

    /** TextField for the productMinField */
    @FXML public TextField productMinField;

    /** TextField for the partSearch */
    @FXML public TextField partSearch;

    /** Variable for the Product ID. */
    int randomProdId;

    /** Variable for the Product Name. */
    String prodName;
    /** Variable for the Product Stock. */
    int prodStock;
    /** Variable for the Product Cost. */
    double prodCost;
    /** Variable for the Product Minimum. */
    int prodMin;
    /** Variable for the Product Maximum. */
    int prodMax;

    /** New base Product object that will be updated with the information the user provides. */
    Product currentProduct = new Product(0, "", 0.0, 0, 0, 0);

    /** This method initializes the add Product page with the parts in the allPartTable that will be used to add to
     * the product
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartTable.setItems(Inventory.getAllParts());

        allPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** This method is used to cancel out of the current window and the user is sent back to the main page.
     * @param event button click
     */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }
    /**
     * On 'Save' button click the Product is saved to the allProducts ObservableList.
     * RUNTIME ERROR: Caused by: java.lang.NullPointerException.
     * This error would happen in this method whenever the 'Save' button was clicked.
     * The way that the issue was resolved was I had to delcare the 'currentProduct'
     * outside the method as well as giving it default values so that there were always
     * some information to be sent to the addProducts method.
     * @param actionEvent button click
     */
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

        if (!isInteger(productMaxField.getText())){
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

        if (!isInteger(productInvField.getText())){
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



        currentProduct = new Product(randomProdId, prodName, prodCost, prodStock, prodMin, prodMax);

        Inventory.addProduct(currentProduct);

        Parent mainForm = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm);
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /** This method is used to add a Part to the associated Parts table so that it is associated with the Product being
     * created
     * @param actionEvent button click
     */
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

    /** This method is used to remove an associated Part from the associated Parts tables so it is not associated with
     * the Product being created.
     * @param actionEvent button click*/
    public void removeAssocPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Select a Part You Would Like to Remove");

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
            currentProduct.deleteAssociatedPart(selectedPart);
        } else {
            Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            notDeleted.setTitle("Confirmation");
            notDeleted.setContentText("Part was not removed");
            notDeleted.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> confirm = notDeleted.showAndWait();
        }
    }

    /**
     * This method is used when a user presses the 'Enter' key, to find a specific Part based on ID or name.
     */
    public void partSearch(){
        String q = partSearch.getText();

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


}
