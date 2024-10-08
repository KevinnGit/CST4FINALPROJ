package com.example.cst;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TOH extends Application {

    public static final int NUM_CIRCLES = 3;

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cst/TOH.fxml"));
            Parent root = loader.load();

            // Set the scene with the loaded FXML layout
            Scene scene = new Scene(root);

            // Configure the stage
            stage.setTitle("Tower of Hanoi");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the FXML file for TOH: " + e.getMessage());
        }
    }
}
