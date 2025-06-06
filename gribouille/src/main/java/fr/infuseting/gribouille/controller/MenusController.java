package fr.infuseting.gribouille.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class MenusController  implements Initializable {

    @FXML public ToggleGroup Tool;
    @FXML public ToggleGroup Width;
    @FXML public RadioMenuItem CrayonButton;
    @FXML public RadioMenuItem EtoileButton;

    @FXML public MenuItem SaveButton;
    @FXML public MenuItem ChargerButton;
    @FXML public MenuItem ExportButton;
    public RadioMenuItem ColorPickerButton;
    public RadioMenuItem GommeButton;
    public RadioMenuItem PotPeintureButton;
    public MenuItem AboutButton;


    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tool.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue != null) {
                String id = ((RadioMenuItem) newValue).getId();
                System.out.println(id);
                if (id.equals("EtoileButton")) {
                    controleur.onEtoile();
                }
                else if (id.equals("CrayonButton")) {
                    controleur.onCrayon();

                }
                else if (id.equals("GommeButton")) {
                    controleur.onGomme();
                }
                else if (id.equals("ColorPickerButton")) {
                    controleur.onColorPicker();
                }
                else if (id.equals("PotPeintureButton")) {
                    controleur.onPotPeinture();
                }
            }
        });
        Width.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue != null) {
                String id = ((RadioMenuItem) newValue).getText();
                controleur.epaisseur.set(Integer.parseInt(id));
                controleur.setEpaisseur(Integer.parseInt(id));
            }
        });

        SaveButton.setOnAction(event -> {
            controleur.onSauvegarde();
        });


        ChargerButton.setOnAction(event -> {
            controleur.onCharger();
        });
        ExportButton.setOnAction(event -> {
            controleur.onExporter();
        });

        AboutButton.setOnAction(event -> {
            controleur.onAPropos();
        });


    }

    @FXML
    private void onQuitte() {
        if (controleur.onQuitter(controleur)) {
            Platform.exit();
        }
    }


}