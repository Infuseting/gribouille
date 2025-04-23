package fr.infuseting.gribouille;

import fr.infuseting.gribouille.modele.Dessin;
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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CadreGribouille.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Dessin dessin = new Dessin();
        dessin.setNomDuFichier("Nouveau Dessin");
        HelloController controller = fxmlLoader.getController();
        controller.setDessin(dessin);

        // Bind the stage title to the nomDuFichier property
        stage.titleProperty().bind(dessin.nomDuFichierProperty());
        stage.setScene(scene);
        stage.show();
        closeWindow(stage);

    }



    public void closeWindow(Stage stage) {
        stage.setOnCloseRequest(event -> {
            if (!Dialogues.confirmation()) {
                event.consume(); // Annule la fermeture de la fenêtre
            }
        });
    }

    public class Dialogues{
        public static boolean confirmation() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de fermeture");
            alert.setContentText("Êtes-vous sûr de vouloir fermer la fenêtre ?");

            Optional<ButtonType> result = alert.showAndWait();
            return result.orElse(ButtonType.NO) == ButtonType.OK;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}