package fr.infuseting.tp3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GrilleController implements Initializable {
    @FXML
    private GridPane grille;
    private GrilleModel grilleModel;


    public GrilleController(GrilleModel grilleModel) {
        this.grilleModel = grilleModel;

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grille.setStyle("-fx-background-color: seashell;");
        for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {

                grilleModel.setCase(lg, col, String.format("L%dC%d", lg, col));
                Label label = new Label();
                label.textProperty().bind(grilleModel.getCase(lg, col));
                label.setMaxWidth(1000);
                label.setMaxHeight(1000);
                label.setAlignment(Pos.CENTER);
                int finalLg = lg;
                int finalCol = col;
                label.setOnMouseClicked(event -> {
                    grilleModel.setCase(finalLg, finalCol, "bonjour");
                });
                grille.add(label, col, lg);
            }
        }
    }
}
