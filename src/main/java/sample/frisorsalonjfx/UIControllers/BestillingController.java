package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import sample.frisorsalonjfx.IBestillinger;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;

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

    public BestillingController() {}
    public void setIBestillinger(IBestillinger bestilling) {
        this.bestilling = bestilling;
        initData();
    }

    public void initData() {
        List<Medarbejder> medarbejdere = bestilling.getMedarbejder();
        cbMedarbejder.getItems().addAll(medarbejdere);
        List<Kunde> kundere = bestilling.getKunde();
        kundeChoiceBox.getItems().addAll(kundere);
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

        if (bestilling.opretBestilling(1, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype)) {
            medarbejderHarTravlt.setVisible(false);
        } else {
            medarbejderHarTravlt.setVisible(true);
        }
    }

    public void goBack() {}
}
