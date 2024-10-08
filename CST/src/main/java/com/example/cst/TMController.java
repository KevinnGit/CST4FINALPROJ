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

import java.util.Objects;

public class TMController {

    @FXML
    private AnchorPane ap1;
    @FXML
    private Button bttn_tm; // Button to trigger TM check

    @FXML
    private TextField input_fb; // TextField for first binary input

    @FXML
    private TextField input_sb; // TextField for second binary input

    @FXML
    private TextField answer_tm; // TextField to show the result

    @FXML
    private ImageView imageView; // ImageView to display the result image

    @FXML
    private TextArea ta_tm; // TextArea for displaying transition steps

    private TM tm; // Turing Machine instance

    @FXML
    public void initialize() {
        // TM initialized later in handleAdd
    }

    // Method triggered by the button click to perform binary addition
    @FXML
    protected void handleAdd(ActionEvent event) {
        String binary1 = input_fb.getText().trim();
        String binary2 = input_sb.getText().trim();

        // Input validation to ensure binary numbers
        if (!binary1.matches("[01]+") || !binary2.matches("[01]+")) {
            ta_tm.setText("Invalid input. Please enter binary numbers only.");
            return;
        }

        // Clear the text area before displaying transitions
        ta_tm.clear();
        ta_tm.appendText("Adding: " + binary1 + " + " + binary2 + "\n");

        // Create a Turing machine instance with the combined input
        tm = new TM(binary1 + "+" + binary2);
        tm.run();

        // Get the steps and display in the TextArea
        String steps = tm.getSteps();
        ta_tm.appendText(steps);

        // Extract the final answer and display it
        String answer = tm.getFinalResult(); // Get the final result from the Turing Machine
        answer_tm.setText(answer); // Display the final answer

        // Check acceptance and show the corresponding image
        if (tm.isAccepted()) {
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
            imageView.setLayoutY(350.0); // Change to desired Y position
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
            default:
                ta_tm.appendText("Unknown button clicked: " + buttonId + "\n");
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
            ta_tm.appendText("Error loading scene: " + e.getMessage() + "\n");
        }
    }
}
