package fr.infuseting.tp3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GrilleController implements Initializable {
    @FXML
    private GridPane grille;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grille.setStyle("-fx-background-color: seashell;");
    }
}
