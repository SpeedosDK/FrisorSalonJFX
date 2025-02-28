package sample.frisorsalonjfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import sample.frisorsalonjfx.Database.BestillingRepo;

import javafx.scene.control.*;
import java.time.*;
import java.util.List;

public class BestillingController {

    private IBestillinger bestilling;

    @FXML
    private ChoiceBox<Medarbejder> cbMedarbejder;

    @FXML
    private DatePicker cbDate;

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
    }
    public void nyBestilling() {
        List<Bestilling> bestillinger = bestilling.getBestillinger();
        Medarbejder medarbejder = cbMedarbejder.getSelectionModel().getSelectedItem();
        LocalDateTime bestilling_dato = cbDate.getValue().atStartOfDay();
        LocalTime bestilling_time = cbTid.getValue();
        Kunde kunde = kundeChoiceBox.getSelectionModel().getSelectedItem();
        Klippetype klippetype = cbKlippetype.getSelectionModel().getSelectedItem();

        if (bestilling.opretBestilling(medarbejder, bestilling_dato, bestilling_time, kunde, klippetype)) {
            medarbejderHarTravlt.setVisible(true);
        }

    }
}
