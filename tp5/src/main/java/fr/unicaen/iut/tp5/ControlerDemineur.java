package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

    private ModeleDemineur modeleDemineur = new ModeleDemineur(0, 0, 0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inconnuInput.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());
        marquesInput.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());

        difficulty.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String selectedDifficulty = newValue.getUserData().toString();
                        initGrille(selectedDifficulty);
                    }
                }
        );

    }

    private void initGrille(String selectedDifficulty) {
        grid.getChildren().clear();
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();

        int[] data = ModeleDemineur.parseUserData(selectedDifficulty);
        modeleDemineur = new ModeleDemineur(data[0], data[1], data[2]);

        for (int i = 0; i < data[0]; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(32);
            grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < data[1]; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(32);
            grid.getColumnConstraints().add(column);
        }
        System.out.println("Finished creating grid with " + data[0] + " rows and " + data[1] + " columns.");

    }
}
