package fr.infuseting.tp3;

import java.util.List;

public class GrilleModel {
    private List<String> labels = List.of(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9"
    );

    public String getCase(int lg, int col) {
        return labels.get(lg * 3 + col);
    }
    public void setCase(int lg, int col, String value) {
        labels.set(lg * 3 + col, value);
    }
}
