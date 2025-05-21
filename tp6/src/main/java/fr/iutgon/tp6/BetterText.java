package fr.iutgon.tp6;

import javafx.css.PseudoClass;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import java.text.DecimalFormat;

public class BetterText<T> extends TableCell<T, Number> {

    @Override
    protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        PseudoClass negatifClass = PseudoClass.getPseudoClass("negatif");
        this.setTextAlignment(TextAlignment.RIGHT);
        if (empty || item == null) {
            setText(null);
            setStyle("");
            setGraphic(null);
            pseudoClassStateChanged(negatifClass, false);
        } else {
            setText(String.format("%.2f", item.doubleValue()));
            if (item.doubleValue() < 0) {
                pseudoClassStateChanged(negatifClass, true);
            } else {
                pseudoClassStateChanged(negatifClass, false);
            }
        }
    }
}