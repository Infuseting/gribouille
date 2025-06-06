package fr.infuseting.gribouille;

import fr.infuseting.gribouille.controller.Controller;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
        stage.titleProperty().bind(Bindings.concat(
                "Gribouille - ",
                Bindings.when(controller.dessin.estModifieProperty())
                        .then(" *")
                        .otherwise(""),
                controller.dessin.nomDuFichierProperty()

        ));
        stage.setScene(scene);
        stage.getIcons().add(new javafx.scene.image.Image(HelloApplication.class.getResourceAsStream("logo.png")));
        stage.getScene().setOnKeyPressed(event -> controller.onKeyPressed(event.getText()));

        stage.show();
        closeWindow(stage, controller);



    }



    public void closeWindow(Stage stage, Controller controller) {
        stage.setOnCloseRequest(event -> {
            if (controller.dessin.estModifieProperty().get()) {
                if (!controller.onQuitter(controller)) {
                    event.consume();
                }
            }

        });
    }



    public static void main(String[] args) {
        launch();
    }
}