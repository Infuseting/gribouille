package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Figure;
import fr.infuseting.gribouille.modele.PotPeinture;
import fr.infuseting.gribouille.modele.Trace;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class OutilPotPeinture extends Outil {

    private double x1, y1;

    public OutilPotPeinture(Controller c) {
        super(c);
    }
    public void initForme(double x, double y) {
        controleur.actualFigure = new PotPeinture(controleur.epaisseur.get(), controleur.couleur.asString().getValue(), x, y);
        controleur.dessin.addFigure(controleur.actualFigure);
        controleur.prevX.set(x);
        controleur.prevY.set(y);
    }
    public void onMousePressed(double x, double y) {
        initForme(x, y);
        controleur.dessinController.potPeinture(x, y, controleur.couleur.getValue());




    };

    public void onMouseDragged(double x, double y) {
        return;
    };


}