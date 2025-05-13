package fr.infuseting.gribouille.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.infuseting.gribouille.modele.Trace;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DessinController  implements Initializable {


    @FXML public Pane CanvasFond;
    @FXML public Canvas Canvas;

    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CanvasFond.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            CanvasFond.setPrefHeight(newValue.getHeight());
            CanvasFond.setPrefWidth(newValue.getWidth());
        });

        Canvas.heightProperty().bind(CanvasFond.heightProperty());
        Canvas.widthProperty().bind(CanvasFond.widthProperty());
    }

    public void efface() {
        Canvas.getGraphicsContext2D().clearRect(0, 0, Canvas.getWidth(), Canvas.getHeight());
    }

    public void trace(double x1, double y1, double x2, double y2) {
        Canvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }

    @FXML
    private void onMousePressed(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        // Create a new Trace and add it to the Dessin
        controleur.actualFigure = new Trace(1, "black", x, y); // Default thickness and color
        controleur.dessin.addFigure(controleur.actualFigure);
        controleur.prevX.set(mouseEvent.getX());
        controleur.prevY.set(mouseEvent.getY());
    }

    @FXML
    private void onMouseDragged(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        // Add points to the current Trace
        if (controleur.actualFigure != null) {
            controleur.actualFigure.addPoint(x, y);
        }

        // Draw the current segment on the Canvas
        Canvas.getGraphicsContext2D().strokeLine(controleur.prevX.getValue(), controleur.prevY.getValue(), x, y);
        controleur.prevX.set(mouseEvent.getX());
        controleur.prevY.set(mouseEvent.getY());
    }

    @FXML
    private void onMouseMoved(MouseEvent mouseEvent) {
        controleur.prevX.set(mouseEvent.getX());
        controleur.prevY.set(mouseEvent.getY());
    }
}
