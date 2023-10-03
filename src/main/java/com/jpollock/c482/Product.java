package com.jpollock.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class has a constructor as well as setters and getters for create a Product and also have methods for getting
 * information. */
public class Product {

    /** ObservableList for the Product's associated Parts. */
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** Used for the Product ID. */
    private int id;

    /** Used for the Product Name. */
    private String name;

    /** Used for the Product Price. */
    private double price;

    /** Used for the Product Stock. */
    private int stock;

    /** Used for the Product Minimum. */
    private int min;

    /** Used for the Product Maximum. */
    private int max;

    /** This is a constructor for the Product object that takes in the following parameters to create a Product object
     * @param id the Product ID
     * @param name the Product Name
     * @param price the Product Price
     * @param stock the Product Stock
     * @param min the Product Minimum
     * @param max the Product Maximum
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Sets the ID for the Product
     * @param id the Product ID
     */
    public void setId(int id){
        this.id = id;
    }

    /** Sets the Name for the Product
     * @param name the Product Name
     */
    public void setName(String name){ this.name = name; }

    /** Sets the Price for the Product
     * @param price the Product Price
     */
    public void setPrice(double price){ this.price = price; }

    /** Sets the Stock for the Product
     * @param stock the Product Stock
     */
    public void setStock(int stock){ this.stock = stock; }

    /** Sets the Minimum for the Product
     * @param min the Product Minimum
     */
    public void setMin(int min){ this.min = min; }

    /** Sets the Maximum for the Product
     * @param max the Product Maximum
     */
    public void setMax(int max){ this.max = max; }

    /**
     * @return the id
     */
    public int getId(){ return id; }

    /**
     * @return the name
     */
    public String getName(){ return name; }

    /**
     * @return the price
     */
    public double getPrice(){ return price; }

    /**
     * @return the stock
     */
    public int getStock(){ return stock; }

    /**
     * @return the Minimum
     */
    public int getMin(){ return min; }

    /**
     * @return the Maximum
     */
    public int getMax(){ return max; }

    /** This method takes in a Part object and adds that Part to the ObservableList associatedParts
     * @param part the selected Part to be added to associatedParts.
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /** This method takes in a Part object that has been selected by the user and removes it from the ObservableList
     * associatedParts if that Part is in the ObservableList
     * @param selectedAssociatedPart the selected Part to be deleted from associatedParts. */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (associatedParts.contains(selectedAssociatedPart)){
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        return false;
    }

    /**
     * @return associatedParts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

}
