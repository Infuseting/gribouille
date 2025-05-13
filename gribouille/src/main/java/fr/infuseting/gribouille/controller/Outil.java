package fr.infuseting.gribouille.controller;

import fr.infuseting.gribouille.modele.Figure;

public abstract class Outil {

    protected Figure figureCourante;
    protected Controller controleur;

    public Outil(Controller c) {
        controleur = c;
    }

    public void onMousePress() {};

    public void onMouseDrag() {};
}
