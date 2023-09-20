package com.example.c482;

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

    public class MainController implements Initializable {
        @FXML
        public TableView partsTable;
        @FXML
        public TableColumn partIdCol;
        @FXML
        public TableColumn partNameCol;
        @FXML
        public TableColumn partInventoryCol;
        @FXML
        public TableColumn priceCostCol;
        @FXML
        public TableColumn productIdCol;
        @FXML
        public TableColumn productNameCol;
        @FXML
        public TableColumn productInventoryCol;
        @FXML
        public TableColumn productCostCol;
        @FXML
        public TableView productTable;
        @FXML
        public TextField partSearch;
        @FXML
        public TextField productSearch;

        @FXML
        public Button closeButton;

        private static boolean firstTime = true;

        private void populateTables() {
            if(!firstTime){
                return;
            }
            firstTime = false;


            Inventory.addProduct(new Product(1, "Big Bike", 10.99, 200, 1, 45));
            Inventory.addProduct(new Product(2, "Little Trike", 12.99, 100, 1, 15));
            Inventory.addProduct(new Product(3, "Motorcycle", 15.99, 200, 1, 50));



            Inventory.addPart(new InHouse(1, "Chain", 10.99, 100, 1, 24, 11111));
            Inventory.addPart(new InHouse(2, "Wheel", 5.99, 10, 1, 20, 11211));
            Inventory.addPart(new InHouse(3, "Sprocket", 8.99, 200, 1, 50, 11211));


        }

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

        @FXML
        public void handleCloseButtonAction(ActionEvent event) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        @FXML
        public void moveToAddPart(ActionEvent event) throws IOException {
            Parent addPartForm = FXMLLoader.load(getClass().getResource("add-part.fxml"));
            Scene addPartScene = new Scene(addPartForm);
            Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }

        @FXML
        public void moveToModifyPart(ActionEvent event) throws IOException {
            Parent modifyPartForm = FXMLLoader.load(getClass().getResource("modify-part.fxml"));
            Scene modifyPartScene = new Scene(modifyPartForm);
            Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();
        }

        @FXML
        public void moveToAddProduct(ActionEvent event) throws IOException {
            Parent addProductForm = FXMLLoader.load(getClass().getResource("add-product.fxml"));
            Scene addProductScene = new Scene(addProductForm);
            Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            System.out.println(productTable.getItems());
            addProductStage.setScene(addProductScene);
            addProductStage.show();
        }

        @FXML
        public void moveToModifyProduct(ActionEvent event) throws IOException {
            Parent modifyProductForm = FXMLLoader.load(getClass().getResource("modify-product.fxml"));
            Scene modifyProductScene = new Scene(modifyProductForm);
            Stage modifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            modifyProductStage.setScene(modifyProductScene);
            modifyProductStage.show();
        }


        public void deletePart(ActionEvent actionEvent) {
            Part selectedItem = (Part) partsTable.getSelectionModel().getSelectedItem();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion of Part");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure that you want to delete " + selectedItem.getName() + "?");

            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                partsTable.getItems().remove(selectedItem);
            } else {
                System.out.println("Canceled");
            }

        }

        public void deleteProductBtn(ActionEvent actionEvent) {

            Product selectedItem = (Product) productTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion of Part");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure that you want to delete " + selectedItem.getName() + "?");

            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                partsTable.getItems().remove(selectedItem);
            } else {
                System.out.println("Canceled");
            }
            productTable.getItems().remove(selectedItem);
        }

        @FXML
        public void showPartResults(ActionEvent actionEvent) {
            String q = partSearch.getText();

            ObservableList<Part> parts = Inventory.lookupPart(q);

            if (parts.size() == 0) {
                try {
                    int partId = Integer.parseInt(q);
                    Part numPart = (Part) Inventory.lookupPart(partId);
                    if (numPart != null) {
                        parts.add(numPart);
                    }
                } catch (NumberFormatException e) {
                    //ignore
                }
            }
            partsTable.setItems(parts);


        }

        public void showProductResults(ActionEvent actionEvent) {
            String p = productSearch.getText();

            ObservableList<Product> products = Inventory.lookupProduct(p);

            if (products.size() == 0) {
                try {
                    int productId = Integer.parseInt(p);
                    Product numProduct = (Product) Inventory.lookupProduct(productId);
                    if (numProduct != null) {
                        products.add(numProduct);
                    }
                } catch (NumberFormatException e) {
                    //ignore
                }
            }
            productTable.setItems(products);


        }




    }



