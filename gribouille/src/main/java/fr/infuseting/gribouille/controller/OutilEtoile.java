package fr.infuseting.gribouille.controller;


import fr.infuseting.gribouille.modele.Etoile;
import fr.infuseting.gribouille.modele.Trace;

public class OutilEtoile extends Outil {

    private double x1, y1;

    public OutilEtoile(Controller c) {
        super(c);
    }
    public void initForme(double x, double y) {
        controleur.actualFigure = new Trace(controleur.epaisseur.get(), controleur.couleur.asString().getValue(), x, y);
        controleur.dessin.addFigure(controleur.actualFigure);
        x1 = x;
        y1 = y;
    }

    public void onMousePressed(double x, double y) {
        initForme(x, y);
    };

    public void onMouseDragged(double x, double y) {
        // Add points to the current Trace
        if (controleur.actualFigure != null) {
            controleur.actualFigure.addPoint(x, y);
        }

        // Draw the current segment on the Canvas
        controleur.dessinController.trace(x1, y1, x, y);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    };


}
