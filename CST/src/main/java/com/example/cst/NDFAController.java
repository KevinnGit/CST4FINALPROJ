package com.example.cst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class NDFAController {

    @FXML
    private AnchorPane ap1;

    @FXML
    private Button bttn_ndfa; // Button to trigger NDFA check

    @FXML
    private TextField input_ndfa; // TextField for user input

    @FXML
    private ImageView imageView; // ImageView to display the result image

    private NDFA ndfa; // NDFA instance

    @FXML
    public void initialize() {
        ndfa = new NDFA(); // Initialize the NDFA instance
    }

    // Method triggered by the button click to check input against NDFA
    @FXML
    protected void handleCheck(ActionEvent event) {
        String input = input_ndfa.getText(); // Get the input from the TextField

        // Check if the input is accepted by the NDFA and show image accordingly
        if (ndfa.isAccepted(input)) {
            showImage("zuccess.jpg"); // Just the file name
        } else {
            showImage("failure.jpg"); // Just the file name
        }
    }

    // Method to show the image in the ImageView
    private void showImage(String imageFileName) {
        String path = "/com/example/cst/" + imageFileName; // Use relative path
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            imageView.setImage(image); // Set the image to the ImageView

            imageView.setLayoutX(450.0); // Change to desired X position
            imageView.setLayoutY(400.0); // Change to desired Y position
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    @FXML
    void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        switch (buttonId) {
            case "button_cfg":
                loadScene("CFG.fxml");
                break;
            case "button_dfa":
                loadScene("DFA.fxml");
                break;
            case "button_ndfa":
                loadScene("NDFA.fxml");// This should lead to the NDFAController
                break;
            case "button_pda":
                loadScene("PDA.fxml");
                break;
            case "button_tm":
                loadScene("TM.fxml");
                break;
            case "button_toh":
                loadScene("TOH.fxml");
                break;
        }
    }

    private void loadScene(String sceneName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = loader.load();
            Stage stage = (Stage) ap1.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println("Error loading scene: " + e.getMessage());
        }
    }
}
