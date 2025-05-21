module fr.infuseting.gribouille {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens fr.infuseting.gribouille to javafx.fxml;
    exports fr.infuseting.gribouille;
    exports fr.infuseting.gribouille.controller;
    opens fr.infuseting.gribouille.controller to javafx.fxml;
}