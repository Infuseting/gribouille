module fr.infuseting.tp1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.infuseting.tp1 to javafx.fxml;
    exports fr.infuseting.tp1;
}