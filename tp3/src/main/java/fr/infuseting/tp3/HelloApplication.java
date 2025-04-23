package fr.infuseting.tp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
    stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
        switch (event.getText()) {
            case "1": grilleModel.setCase(2,0, "Touche"); break;
            case "2": grilleModel.setCase(2,1, "Touche"); break;
            case "3": grilleModel.setCase(2,2, "Touche"); break;
            case "4": grilleModel.setCase(1,0, "Touche"); break;
            case "5": grilleModel.setCase(1,1, "Touche"); break;
            case "6": grilleModel.setCase(1,2, "Touche"); break;
            case "7": grilleModel.setCase(0,0, "Touche"); break;
            case "8": grilleModel.setCase(0,1, "Touche"); break;
            case "9": grilleModel.setCase(0,2, "Touche"); break;
            default: break;
        }
    });
    stage.setScene(scene);
    stage.show();
}

public static void main(String[] args) {
    launch();
}
}