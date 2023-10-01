package com.example.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.ProtocolFamily;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){ allParts.addAll(newPart); }

    public static void addProduct(Product newProduct){ allProducts.addAll(newProduct);}

    public static Part lookupPart(int partId){

        for(int i = 0; i < allParts.size(); i++){
            Part x = allParts.get(i);

            if(x.getId() == partId){
                return x;
            }
        }
        return null;
    }


    public static Product lookupProduct(int productId){
        for (int i = 0; i < allProducts.size(); i++){
            Product x = allProducts.get(i);

            if (x.getId() == productId){
                return x;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part searchPart: allParts){
            if(searchPart.getName().contains(partName)){
                namedParts.add(searchPart);
            }
        }


        return namedParts;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        for(Product searchProduct: allProducts){
            if(searchProduct.getName().contains(productName)){
                namedProducts.add(searchProduct);
            }
        }
        return namedProducts;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    public static ObservableList<Part> getAllParts(){ return allParts; }

    public static ObservableList<Product> getAllProducts(){ return allProducts; }


}
