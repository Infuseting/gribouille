package fr.infuseting.tp3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GrilleController implements Initializable {
    @FXML
    private GridPane grille;

    private List<Label> labels = List.of(
            new Label("1"), new Label("2"), new Label("3"),
            new Label("4"), new Label("5"), new Label("6"),
            new Label("7"), new Label("8"), new Label("9")
    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grille.setStyle("-fx-background-color: seashell;");
        for (Label label : labels) {
            grille.add(label, labels.indexOf(label) % 3, labels.indexOf(label) / 3);
            label.setText(String.format("L%dC%d", labels.indexOf(label) / 3 + 1, labels.indexOf(label) % 3 + 1));
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            label.setStyle("-fx-alignment: center;");
            label.setOnMouseClicked(event -> {
                label.setText("Bonjour !");
            });
        }
    }
}
