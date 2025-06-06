package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Etoile;
import fr.infuseting.gribouille.modele.Trace;
import javafx.scene.paint.Color;

public class OutilGomme extends Outil {
    public OutilGomme(Controller c) {
        super(c);
    }

    public void initForme(double x, double y) {
        controleur.actualFigure = new Trace(controleur.epaisseur.get(), ((Color) controleur.dessinController.CanvasFond.getBackground().getFills().get(0).getFill()).toString(), x, y);
        controleur.dessin.addFigure(controleur.actualFigure);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    }
    public void onMousePressed(double x, double y) {
        initForme(x, y);
    }

    public void onMouseDragged(double x, double y) {

        // Add points to the current Trace
        if (controleur.actualFigure != null) {
            controleur.actualFigure.addPoint(x, y);
        }

        // Draw the current segment on the Canvas
        controleur.dessinController.gomme(controleur.prevX.getValue(), controleur.prevY.getValue(), x, y);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    }

}