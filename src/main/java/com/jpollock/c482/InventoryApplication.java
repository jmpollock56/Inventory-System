package com.jpollock.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an app that is an Inventory System.
 * FUTURE_ENHANCEMENT: A feature that could enhance this current application is the ability to search through Parts and
 * Products that have already been created but are currently not in the inventory system. This would allow users to easily
 * go over and add them to the inventory without having to create new parts and products.
 */
public class InventoryApplication extends Application {
    /** This method sets the primary stage/page of the app and launches it. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),864, 384);
        primaryStage.setTitle("C482 IMS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /** This is the main method of the program. It will be the first method that gets called when you run the program.
     * LOGICAL ERROR Location @ moveToModifyProduct method in MainController.java
     * Javadoc's location in folder c482 > Javadoc
     * */
    public static void main(String[] args) {
        launch();
    }
}