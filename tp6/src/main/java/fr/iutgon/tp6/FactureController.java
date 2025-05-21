package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;

  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      qte.setCellFactory(cell -> new TextFieldTableCell<>(new IntegerStringConverter()));
      List<Produit> produitList = FabriqueProduits.getProduits();
      produitList.removeIf(produit1 -> {
          return produit1.getNom().equalsIgnoreCase("Promotion");
      });
      produit.setCellFactory(cell -> new ChoiceBoxTableCell<>(new StringConverter<Produit>() {
          @Override
          public String toString(Produit produit) {
              return produit.getNom();
          }

          @Override
          public Produit fromString(String s) {
              return FabriqueProduits.getProduits()
                      .stream()
                      .filter(produit -> produit.getNom().equals(s))
                      .findFirst()
                      .orElse(null);
          }
      }, FXCollections.observableList(produitList)));

  }

  public void onAjouter(ActionEvent actionEvent) {
    Ligne ligne = new Ligne(new Random().nextInt(10) + 1, FabriqueProduits.getProduits().get(new Random().nextInt(FabriqueProduits.getProduits().size() - 1)) );
    table.getItems().add(ligne);
    Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>> callbackQuantite =
            new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
              @Override
              public ObservableValue<Number> call(TableColumn.CellDataFeatures<Ligne, Number> ligneProduitCellDataFeatures) {
                return ligneProduitCellDataFeatures.getValue().qteProperty();
              }
            };
    Callback<TableColumn.CellDataFeatures<Ligne, Produit>, ObservableValue<Produit>> callbackProduit =
            new Callback<TableColumn.CellDataFeatures<Ligne, Produit>, ObservableValue<Produit>>() {
              @Override
              public ObservableValue<Produit> call(TableColumn.CellDataFeatures<Ligne, Produit> ligneProduitCellDataFeatures) {
                return ligneProduitCellDataFeatures.getValue().produitProperty();
              }
            };
            Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>> callbackPrixUnit =
              new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
                @Override
                public ObservableValue<Number> call(TableColumn.CellDataFeatures<Ligne, Number> ligneNumberCellDataFeatures) {
                  return Bindings.selectFloat(ligneNumberCellDataFeatures.getValue().produitProperty(), "prix");
                }
            };
    Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>> callbackTotalHT =
            new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
              @Override
              public ObservableValue<Number> call(TableColumn.CellDataFeatures<Ligne, Number> ligneNumberCellDataFeatures) {
                return ligneNumberCellDataFeatures.getValue().totalHTProperty();
              }
            };
    Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>> callbackTotalTTC =
            new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
              @Override
              public ObservableValue<Number> call(TableColumn.CellDataFeatures<Ligne, Number> ligneNumberCellDataFeatures) {
                return ligneNumberCellDataFeatures.getValue().totalTTCProperty();
              }
            };

    qte.setCellValueFactory(cellData -> cellData.getValue().qteProperty().asObject());
    produit.setCellValueFactory(callbackProduit);
    prixUnitaire.setCellValueFactory(callbackPrixUnit);
    totalHT.setCellValueFactory(callbackTotalHT);
    totalTTC.setCellValueFactory(callbackTotalTTC);
  }
}
