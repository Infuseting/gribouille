package fr.infuseting.gribouille;

import fr.infuseting.gribouille.controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class Dialogues {
    public static boolean confirmation(Controller controller) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de fermeture");
        alert.setContentText("Êtes-vous sûr de vouloir fermer la fenêtre ? (Votre dessin n'est pas sauvegardé)");

        ButtonType boutonAnnuler = new ButtonType("Annuler", ButtonType.CANCEL.getButtonData());
        ButtonType boutonQuitter = new ButtonType("Quitter", ButtonType.NO.getButtonData());
        ButtonType boutonSauvegarderQuitter = new ButtonType("Sauvegarder et quitter", ButtonType.OK.getButtonData());

        alert.getButtonTypes().setAll(boutonAnnuler, boutonQuitter, boutonSauvegarderQuitter);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(boutonAnnuler) == boutonSauvegarderQuitter) {
            controller.onSauvegarde();
            return true;
        } else if (result.orElse(boutonAnnuler) == boutonQuitter) {
            return true;
        }
        return false;
    }
}