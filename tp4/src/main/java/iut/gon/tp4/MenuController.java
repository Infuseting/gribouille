package iut.gon.tp4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;

import java.io.IOException;


public class MenuController {

    private GrilleModel modele;
    private Scores table;

    private @FXML MenuBar menuBar;

    public void setParams(GrilleModel modele, Scores table) {
        this.modele = modele;
        this.table = table;
    }

    @FXML
    public void onMenuNouvelle(ActionEvent evt) {
        modele.nouvellePartie();
    }

    @FXML
    public void onMenuTable(ActionEvent evt) {
        FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("table.fxml"));
        try {
            menuBar.getScene().setRoot(fxmlLoader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TableController tableController = fxmlLoader.getController();
        tableController.setScores(table);
    }

    @FXML
    public void onMenuQuitter(ActionEvent evt) {
        Platform.exit();
    }
}