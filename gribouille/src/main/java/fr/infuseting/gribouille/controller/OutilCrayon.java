package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Trace;

public class OutilCrayon extends Outil {
    public OutilCrayon(Controller c) {
        super(c);
    }

    public void onMousePressed(double x, double y) {
        controleur.actualFigure = new Trace(controleur.epaisseur.get(), "black", x, y); // Default thickness and color
        controleur.dessin.addFigure(controleur.actualFigure);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    }

    public void onMouseDragged(double x, double y) {

        // Add points to the current Trace
        if (controleur.actualFigure != null) {
            controleur.actualFigure.addPoint(x, y);
        }

        // Draw the current segment on the Canvas
        controleur.dessinController.trace(controleur.prevX.getValue(), controleur.prevY.getValue(), x, y);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    }

}