package fr.infuseting.gribouille.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CouleursController implements Initializable {

    @FXML public ColorPicker ColorPicker;
    @FXML public Rectangle redColor;
    @FXML public Rectangle greenColor;
    @FXML public Rectangle purpleColor;
    @FXML public Rectangle cyanColor;
    @FXML public Rectangle pinkColor;
    @FXML public Rectangle yellowColor;
    @FXML public Rectangle blackColor;
    @FXML public Rectangle whiteColor;

    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
}