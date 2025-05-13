module iut.gin.tp3 {
  requires javafx.controls;
  requires javafx.fxml;
    requires java.desktop;


    opens iut.gon.tp4 to javafx.fxml;
  exports iut.gon.tp4;
}