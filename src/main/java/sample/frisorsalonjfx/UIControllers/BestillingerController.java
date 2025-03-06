package sample.frisorsalonjfx.UIControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Medarbejder;
import sample.frisorsalonjfx.UseCases.BestillingLogic;
import sample.frisorsalonjfx.Database.*;
import sample.frisorsalonjfx.IBestillinger;

import java.io.IOException;
import java.time.*;
import java.util.List;

public class BestillingerController {

    @FXML
    private Button btnOpretBestilling;
    @FXML
    private Button btnRedigerBestilling;
    @FXML
    private Button btnSletBestilling;
    @FXML
    private Button btnFindBestilling;
    @FXML
    private Button btnSkiftAktiv;
    @FXML
    private CheckBox checkBoxVisInaktive;
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
    @FXML
    private TableColumn<Bestilling, String> colPris;
    @FXML
    private TableColumn<Bestilling, String> colAktiv;
    @FXML
    private Button backtoMenuButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField navnTextField;
    @FXML
    private ComboBox<Medarbejder> cbMedarbejder;
    @FXML
    private ObservableList<Bestilling> bestillingList = FXCollections.observableArrayList();
    @FXML
    private IBestillinger iBestillinger;
    @FXML
    private ComboBox<Klippetype> cbKlippetype;

    public BestillingerController() {
        this.iBestillinger = new BestillingLogic(new BestillingRepo(), new MedarbejderRepo(), new KlippetypeRepo(), new KundeRepo());
    }

    @FXML
    public void initialize() {
        // Kobl kolonner til Bestilling-objektets felter
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDato.setCellValueFactory(new PropertyValueFactory<>("bestilling_dato"));
        colTid.setCellValueFactory(new PropertyValueFactory<>("bestilling_time"));
        colAktiv.setCellValueFactory(new PropertyValueFactory<>("aktiv"));

        // Specifik binding til relaterede objekter (Kunde, Klippetype, Medarbejder, Aktiv)
        colKunde.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKunde().getName()));
        colKlippetype.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKlippetype().getKlippeStil()));
        colMedarbejder.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedarbejder().getName()));
        colPris.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKlippetype().getTimeForCut())));
        colAktiv.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAktiv() ? "Ja" : "Nej"));
        setCbKlippetype();
        setBestillingTableView();
    }

    @FXML
    public void setCbKlippetype() {
        ObservableList<Klippetype> klippetype = (ObservableList<Klippetype>) iBestillinger.getKlippetype();
        cbKlippetype.setItems(klippetype);
    }
    @FXML
    public void setBestillingTableView() {
        bestillingList.clear();
        loadBestillinger(checkBoxVisInaktive.isSelected());
    }

    @FXML
    public void skiftAktiv() {
        Bestilling bestilling = bestillingTableView.getSelectionModel().getSelectedItem();
        if (bestilling != null) {
            bestilling.setAktiv(!bestilling.getAktiv());
            iBestillinger.updateBestilling(bestilling);
            bestillingTableView.refresh();
        }
    }

    @FXML
    public void loadBestillinger(Boolean visInaktiveBestillinger) {
        if (iBestillinger != null) {
            List<Bestilling> bestillinger = iBestillinger.getBestillinger();

            iBestillinger.setGamleBestillingerInaktive(bestillinger); // sætter bestillinger hvis dato har været der som inaktive
            iBestillinger.sletGamleBestillinger(); // sletter bestillinger der er over 5 år gamle

            if (visInaktiveBestillinger) {
                bestillingList.addAll(bestillinger);
            } else {
                for (Bestilling bestilling : bestillinger) {
                    if (bestilling.getAktiv()) {
                        bestillingList.add(bestilling);
                    }
                }
            }
            bestillingTableView.setItems(bestillingList);
        }
    }


    @FXML
    public void changeToOpretBestilling() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør opret bestilling.fxml"));
        Parent root = fxmlLoader.load();

        BestillingController bestillingController = fxmlLoader.getController();
        bestillingController.setIBestillinger(iBestillinger);
        Stage stage = (Stage) btnOpretBestilling.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void changeToRedigerBestilling() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør rediger bestilling.fxml"));
        Parent root = fxmlLoader.load();

        RedigerBestillingController redigerBestillingController = fxmlLoader.getController();
        redigerBestillingController.setIBestillinger(iBestillinger);
        Bestilling valgtBestilling = bestillingTableView.getSelectionModel().getSelectedItem();
        redigerBestillingController.setValgtBestilling(valgtBestilling);

        Stage stage = (Stage) btnRedigerBestilling.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void sletBestilling() {
        Bestilling slettetBestilling = bestillingTableView.getSelectionModel().getSelectedItem();
        if (slettetBestilling != null) {
            iBestillinger.deleteBestilling(slettetBestilling);
            bestillingTableView.getItems().remove(slettetBestilling);
        }
    }





    @FXML
    public void findBestilling() {
        String searchedName = navnTextField.getText();
        Medarbejder searchedMedarbejder = cbMedarbejder.getSelectionModel().getSelectedItem();
        Klippetype klippetype = cbKlippetype.getSelectionModel().getSelectedItem();
        LocalDateTime searchedDate = datePicker.getValue() != null ? datePicker.getValue().atStartOfDay() : LocalDateTime.now();

        if (searchedName == null) {
            searchedName = "";
        }
        if (searchedMedarbejder == null) {
            searchedMedarbejder = new Medarbejder(1, null, null, false);
        }
        if (klippetype == null) {
            klippetype = new Klippetype(1, null, 0, 0);
        }

        bestillingList.clear();
        bestillingList.addAll(iBestillinger.findBestilling(searchedName, searchedMedarbejder.getName(), searchedDate, klippetype));
        bestillingTableView.setItems(bestillingList);
    }

    @FXML
    public void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) backtoMenuButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
