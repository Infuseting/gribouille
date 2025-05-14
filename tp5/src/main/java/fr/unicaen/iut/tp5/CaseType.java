package fr.unicaen.iut.tp5;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public enum CaseType {
    INCONNUE(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(0.20, true), null))),
    LIBRE(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, null))),
    ECHEC(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, null))),
    MARQUEE(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, null)));

    private final Background background;

    CaseType(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }

    public static Background getBackground(String text) {
        switch (text) {
            case "?" : return INCONNUE.getBackground();
            case "X" : return ECHEC.getBackground();
            case "P" : return MARQUEE.getBackground();
            default:
                if (text.matches("[0-8]")) {
                    return LIBRE.getBackground();
                }
                return INCONNUE.getBackground();
        }
    }
}