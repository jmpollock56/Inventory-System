package com.jpollock.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class handles most of the methods that will be needed when interacting with the application. It also has
 * ObservableLists that will be used to store Parts and Products to be shown in a TableView. */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This method is used to add a Part to the allParts ObservableList
     * @param newPart new Part to be added to allParts
     */
    public static void addPart(Part newPart){ allParts.add(newPart); }

    /** This method is used to add a Product to the allProducts ObservableList
     * @param newProduct new Product to be added to allProducts
     */
    public static void addProduct(Product newProduct){ allProducts.add(newProduct);}

    /** This method is used to find a Part ID to return. If nothing is found it will return null.
     * @param partId the Part ID
     * @return the Part ID, if found.
     * @return null if the Part ID is not found. */
    public static Part lookupPart(int partId){

        for(int i = 0; i < allParts.size(); i++){
            Part x = allParts.get(i);

            if(x.getId() == partId){
                return x;
            }
        }
        return null;
    }


    /** This method is used to find a Product ID to return. If nothing is found it will return null.
     * @param productId the Product ID
     * @return the Product, if found.
     * @return null if the Product ID is not found. */
    public static Product lookupProduct(int productId){
        for (int i = 0; i < allProducts.size(); i++){
            Product x = allProducts.get(i);

            if (x.getId() == productId){
                return x;
            }
        }
        return null;
    }

    /** This method is used to find a Part Name to return. If nothing is found it will return null.
     * @param partName the Part Name
     * @return the Part, if found.
     * @return null if the Part Name is not found. */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part searchPart: allParts){
            if(searchPart.getName().toLowerCase().contains(partName)){
                namedParts.add(searchPart);
            }
        }


        return namedParts;
    }

    /** This method is used to find a Product Name to return. If nothing is found it will return null.
     * @param productName the Part Name
     * @return the Product, if found.
     * @return null if the Product Name is not found. */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        for(Product searchProduct: allProducts){
            if(searchProduct.getName().toLowerCase().contains(productName)){
                namedProducts.add(searchProduct);
            }
        }
        return namedProducts;
    }

    /** This method is used to update a Part object that has had its information changed.
     * @param index the index of the Part in the allParts ObservableList.
     * @param selectedPart the Part that has had its information changed. */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /** This method is used to update a Product object that has had its information changed.
     * @param index the index of the Product in the allProducts ObservableList.
     * @param newProduct the Product that has had its information changed. */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /** This method is used to delete a Part from allParts.
     * @param selectedPart Part to be deleted.
     * @return true if it has been deleted.
     * @return false if it has not been deleted. */
    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /** This method is used to delete a Product from allProduct.
     * @param selectedProduct Product to be deleted.
     * @return true if it has been deleted.
     * @return false if it has not been deleted. */
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return allParts
     */
    public static ObservableList<Part> getAllParts(){ return allParts; }

    /**
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts(){ return allProducts; }


}
