package fr.infuseting.gribouille.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import fr.infuseting.gribouille.modele.Trace;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

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
    public void gomme(double x1, double y1, double x2, double y2) {
        Canvas.getGraphicsContext2D().setStroke(Paint.valueOf("#FFFFFF"));
        Canvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
        Canvas.getGraphicsContext2D().setStroke(controleur.couleur.get());

    }

    public void setEpaisseur(int epaisseur) {
        Canvas.getGraphicsContext2D().setLineWidth(epaisseur);
    }
    public void setCouleur(Paint color) {
        Canvas.getGraphicsContext2D().setStroke(color);
    }


    @FXML
    private void onMousePressed(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        controleur.undoStack.push(new ArrayList<>(controleur.dessin.getFigures()));
        controleur.outilCourant.onMousePressed(x, y);
        controleur.redoStack.clear();

    }

    @FXML
    private void onMouseDragged(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        System.out.println(controleur.dessin.getFigures());
        controleur.outilCourant.onMouseDragged(x, y);
    }

    @FXML
    private void onMouseMoved(MouseEvent mouseEvent) {
        controleur.prevX.set(mouseEvent.getX());
        controleur.prevY.set(mouseEvent.getY());
    }

    public void potPeinture(double x, double y, Paint value) {
        int width = (int) Canvas.getWidth();
        int height = (int) Canvas.getHeight();
        var gc = Canvas.getGraphicsContext2D();
        var snapshot = Canvas.snapshot(null, null);
        int startX = (int) x;
        int startY = (int) y;
        javafx.scene.paint.Color targetColor = snapshot.getPixelReader().getColor(startX, startY);
        javafx.scene.paint.Color fillColor = (javafx.scene.paint.Color) value;
        if (colorsAreClose(targetColor, fillColor, 0.15)) return;

        boolean[][] visited = new boolean[width][height];
        java.util.ArrayDeque<int[]> stack = new java.util.ArrayDeque<>();
        stack.push(new int[]{startX, startY});

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int cx = pos[0];
            int cy = pos[1];

            if (cx < 0 || cy < 0 || cx >= width || cy >= height) continue;
            if (visited[cx][cy]) continue;
            if (!colorsAreClose(snapshot.getPixelReader().getColor(cx, cy), targetColor, 0.15)) continue;

            visited[cx][cy] = true;
            gc.getPixelWriter().setColor(cx, cy, fillColor);

            for (int[] dir : directions) {
                stack.push(new int[]{cx + dir[0], cy + dir[1]});
            }
        }
    }

    private boolean colorsAreClose(javafx.scene.paint.Color c1, javafx.scene.paint.Color c2, double tolerance) {
        double dr = c1.getRed() - c2.getRed();
        double dg = c1.getGreen() - c2.getGreen();
        double db = c1.getBlue() - c2.getBlue();
        double da = c1.getOpacity() - c2.getOpacity();
        return (dr * dr + dg * dg + db * db + da * da) < (tolerance * tolerance);
    }
}
