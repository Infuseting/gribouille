package fr.infuseting.tp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    GrilleModel grilleModel = new GrilleModel();
    GrilleController grilleController = new GrilleController(grilleModel);
    fxmlLoader.setController(grilleController);
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
}

public static void main(String[] args) {
    launch();
}
}