package fr.infuseting.tp3;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class GrilleModel {
    private List<SimpleStringProperty> labels = new ArrayList<>(List.of(
            new SimpleStringProperty("1"), new SimpleStringProperty("2"), new SimpleStringProperty("3"),
            new SimpleStringProperty("4"), new SimpleStringProperty("5"), new SimpleStringProperty("6"),
            new SimpleStringProperty("7"), new SimpleStringProperty("8"), new SimpleStringProperty("9")
    ));
    public GrilleModel() {

    }

    public SimpleStringProperty getCase(int lg, int col) {
        return labels.get(lg * 3 + col);
    }
    public void setCase(int lg, int col, String value) {
        labels.get(lg * 3 + col).setValue(value);
    }
}
