package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Figure;

public abstract class Outil {

    protected Controller controleur;

    public Outil(Controller c) {
        controleur = c;
    }
    public void initForme(double x, double y) {};

    public void onMousePressed(double x, double y) {};

    public void onMouseDragged(double x, double y) {};

}
