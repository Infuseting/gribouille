package fr.infuseting.gribouille.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StatutController implements Initializable {

    @FXML public Label xLabel;
    @FXML public Label yLabel;
    @FXML public Label epaisseur;
    @FXML public Label tool;
    @FXML public Label color;

    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
}