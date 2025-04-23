package fr.infuseting.gribouille;

import fr.infuseting.gribouille.modele.Dessin;
import fr.infuseting.gribouille.modele.Figure;
import fr.infuseting.gribouille.modele.Trace;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private MenuItem AboutButton;

    @FXML
    private Menu AideDropdown;

    @FXML
    private Canvas Canvas;

    @FXML
    private MenuItem ChargerButton;

    @FXML
    private ColorPicker ColorPicker;

    @FXML
    private RadioMenuItem CrayonButton;

    @FXML
    private Menu DessinDropdown;

    @FXML
    private Menu EpaisseurButton;

    @FXML
    private RadioMenuItem EtoileButton;

    @FXML
    private MenuItem ExportButton;

    @FXML
    private Menu OutilsDropdown;

    @FXML
    private MenuItem QuitButton;

    @FXML
    private MenuItem SaveButton;

    @FXML
    private ToggleGroup Tool;

    @FXML
    private ToggleGroup Width;

    @FXML
    private Rectangle blackColor;

    @FXML
    private Rectangle cyanColor;

    @FXML
    private Rectangle greenColor;

    @FXML
    private Rectangle pinkColor;

    @FXML
    private Rectangle purpleColor;

    @FXML
    private Rectangle redColor;

    @FXML
    private Rectangle whiteColor;

    @FXML
    private Rectangle yellowColor;

    @FXML
    private Pane CanvasFond;

    @FXML
    private Label xLabel;

    @FXML
    private Label yLabel;

    private Dessin dessin;
    private Trace currentTrace;

    public void setDessin(Dessin dessin) {
        this.dessin = dessin;
    }

    private final SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty prevY = new SimpleDoubleProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CanvasFond.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            CanvasFond.setPrefWidth(newValue.getWidth());
            CanvasFond.setPrefHeight(newValue.getHeight());
        });
        // Bind the Canvas to the Pane
        Canvas.widthProperty().bind(CanvasFond.widthProperty());
        Canvas.heightProperty().bind(CanvasFond.heightProperty());
        // Add listener to redraw figures when Canvas size changes
        Canvas.widthProperty().addListener((observable, oldValue, newValue) -> redraw());
        Canvas.heightProperty().addListener((observable, oldValue, newValue) -> redraw());
        CanvasFond.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Circle circle = new Circle(event.getX(), event.getY(), 5);
                circle.setMouseTransparent(true);
                CanvasFond.getChildren().add(circle);
                event.consume();
            }
        });
        xLabel.textProperty().bind(prevX.asString("X: %.2f"));
        yLabel.textProperty().bind(prevY.asString("Y: %.2f"));
    }

    @FXML
    private void onMousePressed(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        // Create a new Trace and add it to the Dessin
        currentTrace = new Trace(1, "black", x, y); // Default thickness and color
        dessin.addFigure(currentTrace);
        prevX.set(mouseEvent.getX());
        prevY.set(mouseEvent.getY());
    }

    @FXML
    private void onMouseDragged(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        // Add points to the current Trace
        if (currentTrace != null) {
            currentTrace.addPoint(x, y);
        }

        // Draw the current segment on the Canvas
        Canvas.getGraphicsContext2D().strokeLine(prevX.getValue(), prevY.getValue(), x, y);
        prevX.set(mouseEvent.getX());
        prevY.set(mouseEvent.getY());
    }

    @FXML
    private void onMouseMoved(MouseEvent mouseEvent) {
        prevX.set(mouseEvent.getX());
        prevY.set(mouseEvent.getY());
    }

    private void redraw() {
        // Clear the Canvas
        Canvas.getGraphicsContext2D().clearRect(0, 0, Canvas.getWidth(), Canvas.getHeight());

        // Redraw all figures in the Dessin
        for (Figure figure : dessin.getFigures()) {
            if (figure instanceof Trace trace) {
                var gc = Canvas.getGraphicsContext2D();
                gc.setLineWidth(trace.getEpaisseur());
                gc.setStroke(javafx.scene.paint.Paint.valueOf(trace.getCouleur()));

                for (int i = 1; i < trace.getPoints().size(); i++) {
                    double x1 = trace.getPoints().get(i - 1).getX();
                    double y1 = trace.getPoints().get(i - 1).getY();
                    double x2 = trace.getPoints().get(i).getX();
                    double y2 = trace.getPoints().get(i).getY();
                    gc.strokeLine(x1, y1, x2, y2);
                }
            }
        }
    }

}
