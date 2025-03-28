package iut.gon.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TP2App extends Application {

  private BorderPane contenu;
  private ListView<String> gauche;
  private ListView<String> droite;
  private Button versGauche;
  private Button versDroite;
  private Button retireTout;
  private Button ajouteTout;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
    contenu = fxmlLoader.load();
    Scene scene = new Scene(contenu);
    extraitIds(scene);

    prepareMenus((MenuBar) scene.lookup("#menus"));
    prepareListe();
    prepareBoutons();
    prepareFermeture(stage);

    stage.setTitle("Gestion de groupe");
    stage.setScene(scene);
    stage.show();
  }

  /** Prépare la fenêtre pour demander confirmation avant fermeture */
  private void prepareFermeture(Stage stage) {
    stage.setOnCloseRequest(event -> {
      //TODO confirmer ou consommer l'événement
    });
  }

  /** Prépare les actions des boutons */
  private void prepareBoutons() {
    ajouteTout.setOnAction(this::onAjouteTout);
    retireTout.setOnAction(this::onRetireTout);
    // TODO actions des deux boutons centraux
  }

  /** Ajoute tous les éléments de gauche dans la liste de droite
   Active le bouton "Retirer tout" et désactive le bouton "Ajouter tout" */
  private void onAjouteTout(ActionEvent actionEvent) {
    droite.getItems().addAll(gauche.getItems());
    gauche.getItems().clear();
    //TODO active/désactive les boutons
  }

  /** Ajoute tous les éléments de droite dans la liste de gauche
   Active le bouton "Ajouter tout" et désactive le bouton "Retirer tout" */
  private void onRetireTout(ActionEvent actionEvent) {
    //TODO
  }

  /** Prépare les menus et leurs événements */
  private void prepareMenus(MenuBar menus) {
    Menu fichierMenu = new Menu("_Fichiers");
    Menu aideMenu = new Menu("_Aide");
    MenuItem quitItem = new MenuItem("Quitter");
    MenuItem aboutItem = new MenuItem("À propos");
    quitItem.setOnAction(event-> Platform.exit());
    aboutItem.setOnAction(event -> {
      Alert alert = new Alert(Alert.AlertType.NONE);
      alert.setTitle("À propos");
      alert.setHeaderText("Gestion de groupe");
      alert.setContentText("TP2 de Gestion de groupe\n");
        alert.show();
    });
    fichierMenu.getItems().add(quitItem);
    aideMenu.getItems().add(aboutItem);

    menus.getMenus().addAll(fichierMenu, aideMenu);

  }

  /**
   Remplit la liste de gauche avec des valeurs
   Active le bouton "Ajouter tout"
   */
  private void prepareListe() {
    gauche.getItems().addAll("Arnaud", "Antoine", "Arthur", "Remy", "Romain", "Sylvain", "Thibault", "Thomas");
    if (gauche.getItems().size() > 0) {
      ajouteTout.setDisable(false);
    }


  }

  private void extraitIds(Scene scene) {
    gauche = (ListView<String>) scene.lookup("#gauche");
    droite = (ListView<String>) scene.lookup("#droite");
    versGauche = (Button) scene.lookup("#versGauche");
    versDroite = (Button) scene.lookup("#versDroite");
    retireTout = (Button) scene.lookup("#retireTout");
    ajouteTout = (Button) scene.lookup("#ajouteTout");
  }

  public static void main(String[] args) {
    launch();
  }
}
