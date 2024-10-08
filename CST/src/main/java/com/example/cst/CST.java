package com.example.cst;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CST extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the main FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(CST.class.getResource("hello-view.fxml"));
        AnchorPane root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("GWAPA SI TROY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
