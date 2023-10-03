package com.jpollock.c482;

/** This InHouse contains a constructor as well as a setter and getter for creating and retrieving information about
 * InHouse Part.
 */
public class InHouse extends Part{
     private int machineId;

     /** The InHouse method is a constructor that creates an InHouse part with these parameters
      * @param id the Part ID
      * @param name the Part Name
      * @param price the Part Price
      * @param min  the Part Minimum
      * @param max the Part Maximum
      * @param stock the Part Stock
      * @param machineId the Part Machine ID
      */
     public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
         this.id = id;
          this.name = name;
          this.price = price;
          this.stock = stock;
          this.min = min;
          this.max = max;
          this.machineId = machineId;

     }

    /**
     * set the machineId
     * @param machineId the Part Machine ID
     */
     public void setMachineId(int machineId){ this.machineId = machineId; }

    /**
     * @return the Part machineId
     */
     public int getMachineId(){ return machineId; }
}
