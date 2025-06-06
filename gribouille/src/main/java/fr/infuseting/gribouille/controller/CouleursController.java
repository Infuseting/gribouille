package fr.infuseting.gribouille.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.events.EventTarget;

import java.net.URL;
import java.util.ResourceBundle;

public class CouleursController implements Initializable {

    @FXML public ColorPicker ColorPicker;
    @FXML public Rectangle redColor;
    @FXML public Rectangle greenColor;
    @FXML public Rectangle purpleColor;
    @FXML public Rectangle cyanColor;
    @FXML public Rectangle pinkColor;
    @FXML public Rectangle yellowColor;
    @FXML public Rectangle blackColor;
    @FXML public Rectangle whiteColor;
    @FXML public VBox colorContainer;
    @FXML public TilePane rectangleContainer;

    private Controller controleur;

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blackColor.setArcHeight(10);
        blackColor.setArcWidth(10);
        blackColor.setStrokeWidth(5);
        ColorPicker.setValue(Color.BLACK);

        colorContainer.setOnMouseClicked(event -> {
            rectangleContainer.getChildren().forEach(node -> {
                if (node instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) node;
                    rectangle.setArcWidth(5);
                    rectangle.setArcHeight(5);
                    rectangle.setStrokeWidth(1);
                }
            });
            if (event.getTarget() instanceof Rectangle) {
                Rectangle clickedRectangle = (Rectangle) event.getTarget();
                controleur.couleur.set(clickedRectangle.getFill());
                ColorPicker.setValue((javafx.scene.paint.Color) clickedRectangle.getFill());
                controleur.setCouleur(clickedRectangle.getFill());
                clickedRectangle.setArcWidth(10);
                clickedRectangle.setArcHeight(10);
                clickedRectangle.setStrokeWidth(5);
            }
        });

        ColorPicker.setOnAction(event -> {
            controleur.couleur.set(ColorPicker.getValue());
            controleur.setCouleur(ColorPicker.getValue());
            rectangleContainer.getChildren().forEach(node -> {
                if (node instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) node;
                    rectangle.setArcWidth(5);
                    rectangle.setArcHeight(5);
                    rectangle.setStrokeWidth(1);
                }
            });
        });
    }
}