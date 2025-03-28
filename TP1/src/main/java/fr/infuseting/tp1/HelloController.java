package fr.infuseting.tp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String welcomeTextString = "Welcome to JavaFX Application!";
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(welcomeTextString);
    }

    public void onToggleButtonClick(ActionEvent actionEvent) {
        welcomeText.setText(welcomeText.getText().equals(welcomeTextString) ? "" : welcomeTextString);
    }
}