package sample.frisorsalonjfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;
import java.io.IOException;
import java.time.*;

public class BestillingerController {

    DatabaseRepo db = new DatabaseRepo();

    @FXML
    private Button btnOpretBestilling;
    @FXML
    private Button btnRedigerBestilling;
    @FXML
    private Button btnSletBestilling;
    @FXML
    private TableView<Bestilling> bestillingTableView;
    @FXML
    private TableColumn<Bestilling, Integer> colId;
    @FXML
    private TableColumn<Bestilling, LocalDateTime> colDato;
    @FXML
    private TableColumn<Bestilling, LocalTime> colTid;
    @FXML
    private TableColumn<Bestilling, String> colKunde;
    @FXML
    private TableColumn<Bestilling, String> colKlippetype;
    @FXML
    private TableColumn<Bestilling, String> colMedarbejder;

    private ObservableList<Bestilling> bestillingList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Kobl kolonner til Bestilling-objektets felter
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDato.setCellValueFactory(new PropertyValueFactory<>("bestilling_dato"));
        colTid.setCellValueFactory(new PropertyValueFactory<>("bestilling_time"));

        // Specifik binding til relaterede objekter (Kunde, Klippetype, Medarbejder)
        colKunde.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKunde().getName()));
        colKlippetype.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKlippetype().getName()));
        colMedarbejder.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedarbejder().getName()));

        loadBestillinger();
    }

    private void loadBestillinger() {
        List<Bestilling> bestillinger = db.readBestillinger();
        bestillingList.setAll(bestillinger);
        bestillingTableView.setItems(bestillingList);
    }

    @FXML
    public void changeToOpretBestilling() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør opret bestilling.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnOpretBestilling.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void changeToRedigerBestilling() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør rediger bestilling.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnRedigerBestilling.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void sletBestilling() {
        Bestilling slettetBestilling = bestillingTableView.getSelectionModel().getSelectedItem();
        if (slettetBestilling != null) {
            bestillingTableView.getItems().remove(slettetBestilling); // fjerner objektet fra TableView
            db.deleteBestilling(slettetBestilling); // fjerner objektet fra databasen
        }
    }



}
