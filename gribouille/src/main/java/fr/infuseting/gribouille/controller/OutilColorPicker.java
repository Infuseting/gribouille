package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Etoile;
import fr.infuseting.gribouille.modele.Figure;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class OutilColorPicker extends Outil {

    private double x1, y1;

    public OutilColorPicker(Controller c) {
        super(c);
    }

    public void onMousePressed(double x, double y) {
        for (Figure f : controleur.dessin.getFigures().reversed()) {
            if (f.contains(x, y)) {
                controleur.couleur.set(Paint.valueOf(f.getCouleur()));
                controleur.couleursController.ColorPicker.setValue(Color.valueOf(f.getCouleur()));
                return;
            }
        }
        Color color = controleur.dessinController.Canvas.snapshot(null, null).getPixelReader().getColor((int)x, (int)y);
        if (color != null) {
            controleur.couleur.set(color);
            controleur.couleursController.ColorPicker.setValue(color);
            return;
        }

        controleur.couleur.set(Paint.valueOf(((Color) controleur.dessinController.CanvasFond.getBackground().getFills().get(0).getFill()).toString()));
        controleur.couleursController.ColorPicker.setValue(Color.valueOf(((Color) controleur.dessinController.CanvasFond.getBackground().getFills().get(0).getFill()).toString()));


    };

    public void onMouseDragged(double x, double y) {
        return;
    };


}
