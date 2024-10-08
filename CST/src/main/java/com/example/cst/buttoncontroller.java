package com.example.cst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class buttoncontroller {

    @FXML
    private AnchorPane ap1;

    @FXML
    private Button button_cfg;
    @FXML
    private Button button_dfa;
    @FXML
    private Button button_ndfa;
    @FXML
    private Button button_pda;
    @FXML
    private Button button_tm;
    @FXML
    private Button button_toh;

    // Method to load a new scene
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

    @FXML
    void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        switch (buttonId) {
            case "button_cfg":
                loadScene("CFG.fxml");
                break;
            case "button_dfa":
                loadScene("DFA.fxml"); // This should lead to the DFAController
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
}
