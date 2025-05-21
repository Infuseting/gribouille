package fr.infuseting.gribouille.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import fr.infuseting.gribouille.Dialogues;
import fr.infuseting.gribouille.modele.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;

public class Controller implements Initializable {
    public final Dessin dessin = new Dessin();
    public Figure actualFigure;
    public Outil outilCourant = new OutilCrayon(this);
    public final SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    public final SimpleDoubleProperty prevY = new SimpleDoubleProperty();
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
    public final SimpleObjectProperty<Paint> couleur = new SimpleObjectProperty<Paint>(Color.BLACK);

    @FXML public MenusController menusController;
    @FXML public DessinController dessinController;
    @FXML public StatutController statutController;
    @FXML public CouleursController couleursController;
    public final Stack<List<Figure>> undoStack = new Stack<>();
    public final Stack<List<Figure>> redoStack = new Stack<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menusController.setControleur(this);
        dessinController.setControleur(this);
        statutController.setControleur(this);
        couleursController.setControleur(this);

        statutController.xLabel.textProperty().bind(prevX.asString("%.0f"));
        statutController.yLabel.textProperty().bind(prevY.asString("%.0f"));
        statutController.epaisseur.textProperty().bind(epaisseur.asString());
        statutController.color.textProperty().bind(couleur.asString());
        statutController.tool.setText("Crayon");
        dessinController.Canvas.heightProperty().addListener((observableValue, oldValue, newValue) -> dessine());
        dessinController.Canvas.widthProperty().addListener((observableValue, oldValue, newValue) -> dessine());

        dessinController.Canvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(
                        new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
                        () -> this.onAnnuler()
                );
                newScene.getAccelerators().put(
                        new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
                        () -> this.onRetablir()
                );
                newScene.getAccelerators().put(
                        new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN),
                        () -> this.onEffacerTout()
                );
            }
        });
    }


    public void onCrayon() {
        outilCourant = new OutilCrayon(this);

        statutController.tool.setText("Crayon");
    };

    public void onEtoile() {
        outilCourant = new OutilEtoile(this);
        statutController.tool.setText("Etoile");
    };

    public void setCouleur(Paint color) {
        couleur.set(color);
        dessinController.setCouleur(color);
    }
    public void setEpaisseur(int epaisseur) {
        this.epaisseur.set(epaisseur);
        dessinController.setEpaisseur(epaisseur);
    }

    public void dessine() {
        GraphicsContext gc = dessinController.Canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Figure f : dessin.getFigures()) {
            for (int i = 1; i < f.getPoints().size(); i++) {
                gc.setLineWidth(f.getEpaisseur());
                gc.setStroke(Paint.valueOf(f.getCouleur()));
                if (f instanceof  Trace) {

                    double x0 = f.getPoints().get(i-1).getX();
                    double y0 = f.getPoints().get(i-1).getY();
                    double x1 = f.getPoints().get(i).getX();
                    double y1 = f.getPoints().get(i).getY();
                    gc.strokeLine(x0, y0, x1, y1);
                } else if (f instanceof Etoile) {
                    double x = f.getPoints().get(0).getX();
                    double y = f.getPoints().get(0).getY();

                    double x1 = f.getPoints().get(i).getX();
                    double y1 = f.getPoints().get(i).getY();
                    gc.strokeLine(x, y, x1, y1);

                }

            }
        }
    }

    public void newForme() {
        if (actualFigure != null) {
            outilCourant.initForme(prevX.get(), prevY.get());

        }
    }
    public void onKeyPressed(String key) {
        switch (key.toLowerCase()) {
            case "c": // Change to Crayon
                onCrayon();
                newForme();
                break;
            case "e": // Change to Etoile
                onEtoile();
                newForme();
                break;
            case "+": // Increase thickness
                setEpaisseur(epaisseur.get() + 1);
                actualFigure = actualFigure.changeEpaisseur(epaisseur.get());
                dessin.addFigure(actualFigure);
                break;
            case "-": // Decrease thickness
                if (epaisseur.get() > 1) {
                    setEpaisseur(epaisseur.get() - 1);
                    actualFigure = actualFigure.changeEpaisseur(epaisseur.get());
                    dessin.addFigure(actualFigure);
                }
                break;
            case "r":
                setCouleur(Color.RED);
                actualFigure = actualFigure.changeCouleur(couleur.get().toString());
                dessin.addFigure(actualFigure);
                break;
            case "b": // Change color to Blue
                setCouleur(Color.BLUE);
                actualFigure = actualFigure.changeCouleur(couleur.get().toString());
                dessin.addFigure(actualFigure);
                break;
            case "g": // Change color to Green
                setCouleur(Color.GREEN);
                actualFigure = actualFigure.changeCouleur(couleur.get().toString());
                dessin.addFigure(actualFigure);
                break;
            default:
                break;
        }
        newForme();
    }

    public boolean onQuitter(Controller controller) {
        if (Dialogues.confirmation(controller)) {
            return true;
        }
        return false;
    }
    public void onCharger() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un dessin");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers dessin", "*.grb")
        );
        Stage stage = (Stage) dessinController.Canvas.getScene().getWindow();
        java.io.File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            dessin.charge(file.getAbsolutePath());
            dessin.setNomDuFichier(file.getName());
            dessine();
        }
    }
    public boolean onSauvegarde() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder le dessin");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers dessin", "*.grb")
        );
        Stage stage = (Stage) dessinController.Canvas.getScene().getWindow();
        java.io.File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            dessin.sauveSous(file.getAbsolutePath());
            dessin.setNomDuFichier("Gribouille - " + file.getName());
            return true;
        }
        return false;
    }
    public void onExporter() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter le dessin");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image PNG", "*.png")
        );
        Stage stage = (Stage) dessinController.Canvas.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            WritableImage image = dessinController.Canvas.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'exportation");
                alert.setHeaderText("Impossible d'exporter l'image");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void onEffacerTout() {
        undoStack.push(new ArrayList<>(dessin.getFigures()));
        dessin.getFigures().clear();
        dessine();
    }

    public void onAnnuler() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new ArrayList<>(dessin.getFigures()));
            List<Figure> previous = undoStack.pop();
            dessin.setFigures(previous);
            dessine();
        }
    }

    public void onRetablir() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new ArrayList<>(dessin.getFigures()));
            List<Figure> next = redoStack.pop();
            dessin.setFigures(next);
            dessine();
        }
    }
}