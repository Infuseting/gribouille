package fr.infuseting.gribouille;

import fr.infuseting.gribouille.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CadreGribouille.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Controller controller = fxmlLoader.getController();
        controller.dessin.setNomDuFichier("Nouveau Dessin");
        // Bind the stage title to the nomDuFichier property
        stage.titleProperty().bind(controller.dessin.nomDuFichierProperty());
        stage.setScene(scene);

        stage.getScene().setOnKeyPressed(event -> controller.onKeyPressed(event.getText()));

        stage.show();
        closeWindow(stage, controller);

    }



    public void closeWindow(Stage stage, Controller controller) {
        stage.setOnCloseRequest(event -> {
            if (!controller.onQuitter()) {
                event.consume();
            }
        });
    }



    public static void main(String[] args) {
        launch();
    }
}