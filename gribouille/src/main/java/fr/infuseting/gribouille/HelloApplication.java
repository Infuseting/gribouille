package fr.infuseting.gribouille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    private double prevX;
    private double prevY;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CadreGribouille.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        closeWindow(stage);
        Canvas dessin = (Canvas) scene.lookup("#canvas");
        Pane pane = (Pane) dessin.getParent();
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Circle circle = new Circle(event.getX(), event.getY(), 5);
                circle.setMouseTransparent(true);
                pane.getChildren().add(circle);
                event.consume();
            }
        });
        dessin.setOnMousePressed(this::onMousePressedDrawOnCanvas);

        closeWindow(stage);
        dessin.setOnMouseDragged(this::onMouseDraggedDrawOnCanvas);

    }

    private void onMouseDraggedDrawOnCanvas(MouseEvent mouseEvent) {

        Canvas dessin = (Canvas) mouseEvent.getSource();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        dessin.getGraphicsContext2D().strokeLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
}

    private void onMousePressedDrawOnCanvas(MouseEvent mouseEvent) {
        Canvas dessin = (Canvas) mouseEvent.getSource();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        prevX = x;
        prevY = y;
    }
    public void closeWindow(Stage stage) {
        stage.setOnCloseRequest(event -> {
            if (!Dialogues.confirmation()) {
                event.consume();
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}

