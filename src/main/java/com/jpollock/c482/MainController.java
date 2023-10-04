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
import java.util.ResourceBundle;

/** This is the MainController class that is used to controller the Main page of the app.
 *
 * @author Josh Pollock
 */
    public class MainController implements Initializable {
        /** TableView for the Parts Table. */
        @FXML public TableView<Part> partsTable;

        /** TableColumn for the Part ID. */
        @FXML public TableColumn partIdCol;

        /** TableColumn for the Part Name. */
        @FXML public TableColumn partNameCol;

        /** TableColumn for the Part Stock. */
        @FXML public TableColumn partInventoryCol;

        /** TableColumn for the Part Price. */
        @FXML public TableColumn priceCostCol;

        /** TableColumn for the Product ID. */
        @FXML public TableColumn productIdCol;

        /** TableColumn for the Product Name. */
        @FXML public TableColumn productNameCol;

        /** TableColumn for the Product Stock. */
        @FXML public TableColumn productInventoryCol;

        /** TableColumn for the Product ICost. */
        @FXML public TableColumn productCostCol;

        /** TableView for the Products Table. */
        @FXML public TableView<Product> productTable;

        /** TextField for the Part search bar. */
        @FXML public TextField partSearch;

        /** TextField for the Product search bar. */
        @FXML public TextField productSearch;

        /** Button for the close action button. */
        @FXML public Button closeButton;

        /** Boolean to tell whether this is the first time the Main form has been opened. */
        private static boolean firstTime = true;

        /** This populateTables() method is used to create Parts and Products to be used for the application */
        private void populateTables() {
            if(!firstTime){
                return;
            }
            firstTime = false;


            Inventory.addProduct(new Product(70012, "Big Bike", 10.99, 200, 1, 300));
            Inventory.addProduct(new Product(64538, "Little Trike", 12.99, 100, 1, 150));
            Inventory.addProduct(new Product(43874, "Motorcycle", 15.99, 200, 1, 500));



            Inventory.addPart(new InHouse(4796, "Chain", 10.99, 100, 1, 300, 11111));
            Inventory.addPart(new InHouse(2256, "Wheel", 5.99, 10, 1, 200, 11211));
            Inventory.addPart(new Outsourced(3092, "Sprocket", 8.99, 200, 1, 500, "Miller"));


        }

        /** This initialize method is used to populate the Parts and Product tables with data.
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            populateTables();

            productTable.setItems(Inventory.getAllProducts());

            productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            partsTable.setItems(Inventory.getAllParts());

            partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            priceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }

        /** This method is used to close the application
         * @param event button click
         */
        @FXML
        public void handleCloseButtonAction(ActionEvent event) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        /** This method is used to send the user to the add part form.
         * @param event button click
         * @throws IOException */
        @FXML
        public void moveToAddPart(ActionEvent event) throws IOException {
            Parent addPartForm = FXMLLoader.load(getClass().getResource("add-part.fxml"));
            Scene addPartScene = new Scene(addPartForm);
            Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }

        /** This method is used to create a Part object based off what the user selected and then send the user to the
         * Modify Part form
         * @param event button click
         * @throws IOException
         */
        @FXML
        public void moveToModifyPart(ActionEvent event) throws IOException {
            Part selectedModifyPart = partsTable.getSelectionModel().getSelectedItem();
            int index = partsTable.getSelectionModel().getSelectedIndex();

            if(selectedModifyPart == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Part Selected");
                alert.setHeaderText("Warning");
                alert.setContentText("Please Select a Part You Would Like to Modify");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-part.fxml"));

            Parent modifyPartForm = loader.load();
            ModifyPartController modifyPartController = loader.getController();

            Scene modifyPartScene = new Scene(modifyPartForm);
            Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();


            modifyPartController.loadPartData(index, selectedModifyPart);
        }

        /** This method is used to send the user to the Add Product form.
         * @param event button click
         * @throws IOException
         */
        @FXML
        public void moveToAddProduct(ActionEvent event) throws IOException {
            Parent addProductForm = FXMLLoader.load(getClass().getResource("add-product.fxml"));
            Scene addProductScene = new Scene(addProductForm);
            Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            addProductStage.setScene(addProductScene);
            addProductStage.show();
        }

        /** This method is used to create a Product object based off what the user selected and then send the user to
         * the Modify Product form.
         * @param event button click
         * @throws IOException
         *
         * LOGICAL ERROR: In the beginning I was having an issue with getting the Associated Parts table to populate in
         * my ModifyProductController class. The way that this issue was resolved was I needed to create a ModifyProductController
         * object to set my loader.getController(); to it to associate it going across to another class.
         */
        @FXML
        public void moveToModifyProduct(ActionEvent event) throws IOException {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            int productIndex = productTable.getSelectionModel().getSelectedIndex();

            System.out.println(productIndex);

            if(selectedProduct == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Part Selected");
                alert.setHeaderText("Warning");
                alert.setContentText("Please Select a Product You Would Like to Modify");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-product.fxml"));
            Parent modifyProductForm = loader.load();
            ModifyProductController modifyProductController = loader.getController();

            Scene modifyPartScene = new Scene(modifyProductForm);
            Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();

            modifyProductController.populateTables(productIndex, selectedProduct);

        }

        /** This method is used to delete a Part that the user has selected.
         * @param actionEvent  button click
         */
        @FXML
        public void deletePartBtn(ActionEvent actionEvent) {
            Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();

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
            alert.setTitle("Confirm Deletion of Part");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure that you want to delete " + selectedPart.getName() + "?");

            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                Inventory.deletePart(selectedPart);
            } else {
                Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
                notDeleted.setTitle("Confirmation");
                notDeleted.setContentText("Part was not deleted");
                notDeleted.getButtonTypes().setAll(ButtonType.OK);
                Optional<ButtonType> confirm = notDeleted.showAndWait();
            }

        }

    /** This method is used to delete a Product that the user has selected. It will not delete if the Product has
     * associated parts.
     * @param actionEvent  button click
     */
        @FXML
        public void deleteProductBtn(ActionEvent actionEvent) {
            Product selectedProduct = (Product) productTable.getSelectionModel().getSelectedItem();

            if (selectedProduct == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selection Error");
                alert.setHeaderText("Warning");
                alert.setContentText("Please Select a Product You Would Like to Delete");

                alert.getButtonTypes().setAll(ButtonType.OK);

                Optional<ButtonType> result = alert.showAndWait();

                return;
            }

            if (selectedProduct.getAllAssociatedParts().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion of Part");
                alert.setHeaderText("Confirm");
                alert.setContentText("Are you sure that you want to delete " + selectedProduct.getName() + "?");

                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.YES) {
                    Inventory.deleteProduct(selectedProduct);
                } else {
                    Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
                    notDeleted.setTitle("Confirmation");
                    notDeleted.setContentText("Product was not deleted");
                    notDeleted.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> confirm = notDeleted.showAndWait();
                }
            } else {
                Alert notDeleted = new Alert(Alert.AlertType.CONFIRMATION);
                notDeleted.setTitle("Error in Deleting Product");
                notDeleted.setContentText("Product could not be deleted since it has associated Parts");
                notDeleted.getButtonTypes().setAll(ButtonType.OK);
                Optional<ButtonType> confirm = notDeleted.showAndWait();
            }


        }

        /** This method is used to search through the allParts list based off of Part Name or ID.
         * @param actionEvent button click
         */
        @FXML
        public void showPartResults(ActionEvent actionEvent) {
            String q = partSearch.getText();

            ObservableList<Part> parts = Inventory.lookupPart(q);

            if (!parts.isEmpty()){
                partsTable.setItems(parts);

            }

            if (parts.isEmpty()) {
                try {
                    int partId = Integer.parseInt(q);
                    Part numPart = (Part) Inventory.lookupPart(partId);
                    if (numPart != null) {
                        parts.add(numPart);
                        partsTable.setItems(parts);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Unable to find Part");
                        alert.setHeaderText("Error");
                        alert.setContentText("Unable to find Part");

                        alert.getButtonTypes().setAll(ButtonType.OK);

                        Optional<ButtonType> result = alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Unable to find Part");
                    alert.setHeaderText("Error");
                    alert.setContentText("Unable to find Part");

                    alert.getButtonTypes().setAll(ButtonType.OK);

                    Optional<ButtonType> result = alert.showAndWait();
                }
            }



        }

    /** This method is used to search through the allProducts list based off of Product Name or ID.
     * @param actionEvent button click
     */
        @FXML
        public void showProductResults(ActionEvent actionEvent) {
            String p = productSearch.getText();

            ObservableList<Product> products = Inventory.lookupProduct(p);

            if (!products.isEmpty()){
                productTable.setItems(products);
            }

            if (products.isEmpty()) {
                try {
                    int productId = Integer.parseInt(p);
                    Product numProduct = (Product) Inventory.lookupProduct(productId);
                    if (numProduct != null) {
                        products.add(numProduct);
                        productTable.setItems(products);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Unable to find Product");
                        alert.setHeaderText("Error");
                        alert.setContentText("Unable to find Product");

                        alert.getButtonTypes().setAll(ButtonType.OK);

                        Optional<ButtonType> result = alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Unable to find Product");
                        alert.setHeaderText("Error");
                        alert.setContentText("Unable to find Product");

                        alert.getButtonTypes().setAll(ButtonType.OK);

                        Optional<ButtonType> result = alert.showAndWait();
                }
            }

        }
    }




