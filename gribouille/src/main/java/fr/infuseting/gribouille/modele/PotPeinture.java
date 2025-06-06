package fr.infuseting.gribouille.modele;

import java.io.PrintWriter;
import java.util.Scanner;

public class PotPeinture extends Figure{

    public PotPeinture(int epaisseur, String couleur, double x0, double y0) {
        super(epaisseur, couleur);
        points.add(new Point(x0, y0));
    }
    PotPeinture(Scanner scan) {
        super(scan);
    }

    @Override
    public void sauve(PrintWriter out) {
        out.print("P ");
        super.sauve(out);
        out.println();
    }
    @Override
    public Figure changeCouleur(String nouvelleCouleur) {
        return null;
    }

    @Override
    public Figure changeEpaisseur(int nouvelleEpaisseur) {
        return null;
    }
}
