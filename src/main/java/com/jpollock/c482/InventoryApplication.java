package com.jpollock.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an app that is an Inventory System. */
public class InventoryApplication extends Application {
    /** This method sets the primary stage/page of the app and launches it. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 864, 384);
        primaryStage.setTitle("C482 IMS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /** This is the main method of the program. It will be the first method that gets called when you run the program. */
    public static void main(String[] args) {
        launch();
    }
}