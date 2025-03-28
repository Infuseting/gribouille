module fr.infuseting.gribouille {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.infuseting.gribouille to javafx.fxml;
    exports fr.infuseting.gribouille;
}