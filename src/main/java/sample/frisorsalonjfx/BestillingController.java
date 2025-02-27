package sample.frisorsalonjfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.*;
import java.awt.Button;
import java.time.*;


import java.awt.*;

public class BestillingController {

    BestillingLogic logic = new BestillingLogic();

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

    public void opretBestilling() {
        Medarbejder medarbejder = cbMedarbejder.getSelectionModel().getSelectedItem();
        LocalDateTime bestilling_dato = cbDate.getValue().atStartOfDay();
        LocalTime bestilling_time = cbTid.getValue();
        Kunde kunde = kundeChoiceBox.getSelectionModel().getSelectedItem();
        Klippetype klippetype = cbKlippetype.getSelectionModel().getSelectedItem();

        logic.nyBestilling(1, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype);
    }














}
