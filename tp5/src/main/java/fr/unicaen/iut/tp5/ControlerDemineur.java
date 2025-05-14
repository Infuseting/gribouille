package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerDemineur implements Initializable {

    @FXML private ToggleGroup difficulty;
    @FXML private TextField inconnuInput;
    @FXML private TextField marquesInput;
    @FXML private GridPane grid;

    private ModeleDemineur modeleDemineur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        difficulty.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String selectedDifficulty = newValue.getUserData().toString();
                        initGrille(selectedDifficulty);
                        inconnuInput.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());
                        marquesInput.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());
                    }
                }
        );

    }

    private void initGrille(String selectedDifficulty) {
        grid.getChildren().clear();
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();

        int[] data = ModeleDemineur.parseUserData(selectedDifficulty);
        this.modeleDemineur = new ModeleDemineur(data[0], data[1], data[2]);
        inconnuInput.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());
        marquesInput.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());

        for (int row = 0; row < data[0]; row++) {
            for (int col = 0; col < data[1]; col++) {
                Label label = new Label();
                label.setPrefSize(31, 31);
                label.setBackground(CaseType.INCONNUE.getBackground());
                label.setStyle("-fx-alignment: center;");


                label.textProperty().bind(modeleDemineur.texteProperty(row, col));

                int finalRow = row;
                int finalCol = col;
                label.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY)  {
                        modeleDemineur.revele(finalRow, finalCol);

                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        modeleDemineur.marque(finalRow, finalCol);
                    }
                    label.setBackground(CaseType.getBackground(modeleDemineur.texteProperty(finalRow, finalCol).get()));
                });

                grid.add(label, col, row);
            }
        }
    }
}
