package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.frisorsalonjfx.IBestillinger;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;

import java.io.IOException;
import java.time.*;
import java.util.List;

public class BestillingController {

    private IBestillinger bestilling;

    @FXML
    private ChoiceBox<Medarbejder> cbMedarbejder;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<LocalTime> cbTid;

    @FXML
    private ChoiceBox<Kunde> kundeChoiceBox;

    @FXML
    private ChoiceBox<Klippetype> cbKlippetype;

    @FXML
    private Button btnOpretBestilling;

    @FXML
    private Label medarbejderHarTravlt;

    @FXML
    private Button backButton;

    public BestillingController() {}

    public void setIBestillinger(IBestillinger bestilling) {
        this.bestilling = bestilling;
        initData();
    }

    public void initData() {
        List<Medarbejder> medarbejdere = bestilling.getMedarbejdere();
        cbMedarbejder.getItems().addAll(medarbejdere);
        List<Kunde> kunder = bestilling.getKunde();
        kundeChoiceBox.getItems().addAll(kunder);
        List<Klippetype> klippetyper = bestilling.getKlippetype();
        cbKlippetype.getItems().addAll(klippetyper);
        List<LocalTime> tider = bestilling.getTimeAvailable();
        cbTid.getItems().addAll(tider);
    }

    public void nyBestilling() {
        Medarbejder medarbejder = cbMedarbejder.getSelectionModel().getSelectedItem();
        LocalDateTime bestilling_dato = datePicker.getValue().atStartOfDay();
        LocalTime bestilling_time = cbTid.getValue();
        Kunde kunde = kundeChoiceBox.getSelectionModel().getSelectedItem();
        Klippetype klippetype = cbKlippetype.getSelectionModel().getSelectedItem();
        boolean aktiv = true;

        if (bestilling.opretBestilling(1, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype, aktiv)) {
            medarbejderHarTravlt.setVisible(false);
        } else {
            medarbejderHarTravlt.setVisible(true);
        }
    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør bestillinger.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
