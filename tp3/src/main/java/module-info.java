module fr.infuseting.tp3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.infuseting.tp3 to javafx.fxml;
    exports fr.infuseting.tp3;
}