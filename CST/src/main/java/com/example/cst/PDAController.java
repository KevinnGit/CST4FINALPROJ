package com.example.cst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class PDAController {

    @FXML
    private AnchorPane ap1;
    @FXML
    private Button bttn_pda; // Button to trigger PDA check

    @FXML
    private TextField input_pda; // TextField for user input

    @FXML
    private ImageView imageView; // ImageView to display the result image

    @FXML
    private TextArea ta_pda; // TextArea for displaying transition steps

    private PDA pda; // PDA instance

    @FXML
    public void initialize() {
        pda = new PDA(); // Initialize the PDA instance
    }

    // Method triggered by the button click to check input against PDA
    @FXML
    protected void handleCheck(ActionEvent event) {
        String input = input_pda.getText(); // Get the input from the TextField
        int n = input.length() / 2; // Calculate n based on the input length

        // Clear the text area before displaying transitions
        ta_pda.clear();

        // Display the initial state before processing
        ta_pda.appendText("Processing input: " + input + "\n");

        // Check if the input is accepted by the PDA and get transitions
        List<String> transitions = pda.isAccepted(input, n);

        // Display all transitions in the TextArea
        for (String transition : transitions) {
            ta_pda.appendText(transition + "\n");
        }

        // Check acceptance and show the corresponding image
        if (transitions.get(transitions.size() - 1).contains("accepted")) {
            showImage("zuccess.jpg"); // Accepted image
        } else {
            showImage("failure.jpg"); // Rejected image
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
                loadScene("NDFA.fxml");
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
