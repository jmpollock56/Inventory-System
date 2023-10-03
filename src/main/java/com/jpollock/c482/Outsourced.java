package com.jpollock.c482;

/** This Outsourced contains a constructor as well as a setter and getter for creating and retrieving information about
 * Outsourced Part.
 */
public class Outsourced extends Part{

    /** Used for the Outsourced Part's Company Name. */
    private String companyName;

    /** The Outsourced method is a constructor that creates an Outsourced part with these parameters
     * @param id the Part ID
     * @param name the Part Name
     * @param price the Part Price
     * @param min  the Part Minimum
     * @param max the Part Maximum
     * @param stock the Part Stock
     * @param companyName the Part Company Name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.companyName = companyName;
    }

    /**
     * @param companyName set the companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName(){
        return companyName;
    }
}
