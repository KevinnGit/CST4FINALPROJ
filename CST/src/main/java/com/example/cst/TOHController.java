package com.example.cst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class TOHController {

    @FXML
    private AnchorPane ap1;

    @FXML
    private TextArea ta_toh;

    @FXML
    private StackPane sp_t1, sp_t2, sp_t3; // Towers from the FXML

    @FXML
    private Button button_toh, button_cfg, button_dfa, button_ndfa, button_pda, button_tm;

    private Optional<Circle> selectedCircle = Optional.empty();

    // Define the sizes of the disks (radii) from smallest to largest
    private final double[] diskSizes = {40, 60, 80}; // Example radii for disks
    private final String[] diskNames = {"Disk 1", "Disk 2", "Disk 3"}; // Disk names for tracking moves


    @FXML
    private void initialize() {
        // Initialize circles on the first tower (sp_t1)
        setupInitialCircles();

        // Set event handlers for the towers to handle circle movement
        sp_t1.setOnMouseClicked(event -> handleTowerClick(sp_t1));
        sp_t2.setOnMouseClicked(event -> handleTowerClick(sp_t2));
        sp_t3.setOnMouseClicked(event -> handleTowerClick(sp_t3));
    }

    private void setupInitialCircles() {
        for (int i = 0; i < diskSizes.length; i++) {
            Circle circle = new Circle(diskSizes[i], Color.TRANSPARENT); // Use Color.TRANSPARENT for the fill
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(circle.getRadius() / 30.0);
            sp_t1.getChildren().add(circle); // Add to first tower initially
        }
    }


    private void handleTowerClick(StackPane tower) {
        if (selectedCircle.isPresent()) {
            Circle circleToMove = selectedCircle.get();
            boolean moveSuccessful = addCircleToTower(tower, circleToMove);
            if (moveSuccessful) {
                // Update the moves text area only if the move was successful
                String towerName = getTowerName(tower);
                int diskNumber = getDiskNumber(circleToMove);
                ta_toh.appendText(diskNames[diskNumber] + " moved to " + towerName + "\n");
            }
            selectedCircle = Optional.empty();
        } else {
            selectedCircle = Optional.ofNullable(getTopMostCircle(tower));
        }
    }


    private String getTowerName(StackPane tower) {
        if (tower == sp_t1) {
            return "Tower 1";
        } else if (tower == sp_t2) {
            return "Tower 2";
        } else {
            return "Tower 3";
        }
    }

    private int getDiskNumber(Circle circle) {
        for (int i = 0; i < diskSizes.length; i++) {
            if (circle.getRadius() == diskSizes[i]) {
                return i; // Return disk index (0-based)
            }
        }
        return -1; // Return -1 if not found
    }


    private Circle getTopMostCircle(StackPane tower) {
        return tower.getChildren().stream()
                .filter(node -> node instanceof Circle)
                .map(node -> (Circle) node)
                .min((c1, c2) -> Double.compare(c1.getRadius(), c2.getRadius()))
                .orElse(null);
    }

    private boolean addCircleToTower(StackPane tower, Circle circle) {
        Circle topMost = getTopMostCircle(tower);

        // If there are no circles in the tower, allow the circle to be placed
        if (topMost == null) {
            tower.getChildren().add(circle);
            updateCirclePositions(tower);
            return true; // Valid move
        }

        // Check if the topmost circle (disk) is smaller
        if (circle.getRadius() < topMost.getRadius()) {
            // Additional check for Disk 1
            if (circle.getRadius() == diskSizes[0]) { // Disk 1 is being moved
                // Check if Disk 2 is present in the tower
                Circle disk2 = tower.getChildren().stream()
                        .filter(c -> c instanceof Circle && ((Circle) c).getRadius() == diskSizes[1])
                        .map(c -> (Circle) c)
                        .findFirst()
                        .orElse(null);

                // Disk 1 can only be placed if Disk 2 is present or if the tower is empty
                if (disk2 != null || topMost.getRadius() == diskSizes[2]) {
                    tower.getChildren().add(circle);
                    updateCirclePositions(tower);
                    return true; // Valid move for Disk 1
                } else {
                    System.out.println("Invalid move: Disk 1 cannot be placed on an empty tower or on Disk 3.");
                    return false; // Invalid move
                }
            } else {
                // Allow larger disks to be placed on top of smaller disks
                tower.getChildren().add(circle);
                updateCirclePositions(tower);
                return true; // Valid move for larger disks
            }
        } else {
            // Invalid move; do not move the circle
            System.out.println("Invalid move: Cannot place a smaller disk on a larger disk.");
            return false; // Invalid move
        }
    }




    // Helper method to get a specific disk from a tower
    private Circle getSpecificDisk(StackPane tower, double radius) {
        return tower.getChildren().stream()
                .filter(node -> node instanceof Circle && ((Circle) node).getRadius() == radius)
                .map(node -> (Circle) node)
                .findFirst()
                .orElse(null);
    }



    private void updateCirclePositions(StackPane tower) {
        for (int i = 0; i < tower.getChildren().size(); i++) {
            Circle circle = (Circle) tower.getChildren().get(i);
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
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
            System.err.println("Error loading scene: " + e.getMessage());
        }
    }
}
