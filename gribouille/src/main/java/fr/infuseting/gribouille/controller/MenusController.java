package fr.infuseting.gribouille.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenusController  implements Initializable {

    @FXML public ToggleGroup Tool;
    @FXML public ToggleGroup Width;
    @FXML public RadioMenuItem CrayonButton;
    @FXML public RadioMenuItem EtoileButton;

    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tool.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                String id = ((RadioMenuItem) newValue).getId();

                if (id.equals("etoile")) {
                    controleur.onEtoile();
                }
                else if (id.equals("crayon")) {
                    controleur.onCrayon();
                }
            }
        });
    }

    @FXML
    private void onQuitte() {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}