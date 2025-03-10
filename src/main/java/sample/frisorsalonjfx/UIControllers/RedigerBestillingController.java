package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.frisorsalonjfx.Database.BestillingRepo;
import sample.frisorsalonjfx.Database.KlippetypeRepo;
import sample.frisorsalonjfx.Database.KundeRepo;
import sample.frisorsalonjfx.Database.MedarbejderRepo;
import sample.frisorsalonjfx.IBestillinger;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;
import sample.frisorsalonjfx.UseCases.BestillingLogic;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class RedigerBestillingController {

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
    private Button setBestillingButton;

    @FXML
    private Label bestillingRedigeretText;

    @FXML
    private Button backButton;

    @FXML
    private IBestillinger iBestillinger;

    private Bestilling valgtBestilling;

    public void setIBestillinger(IBestillinger bestilling) {
        this.bestilling = bestilling;
    }

    public RedigerBestillingController() {
        this.iBestillinger = new BestillingLogic(new BestillingRepo(), new MedarbejderRepo(), new KlippetypeRepo(), new KundeRepo());
    }

    public void setValgtBestilling(Bestilling valgtBestilling) {
        this.valgtBestilling = valgtBestilling;
        initData();
    }

    @FXML
    public void initData()  {
        List<Medarbejder> medarbejdere = bestilling.getMedarbejdere();
        cbMedarbejder.getItems().addAll(medarbejdere);
        List<Kunde> kunder = bestilling.getKunde();
        kundeChoiceBox.getItems().addAll(kunder);
        List<Klippetype> klippetyper = bestilling.getKlippetype();
        cbKlippetype.getItems().addAll(klippetyper);
        List<LocalTime> tider = bestilling.getTimeAvailable();
        cbTid.getItems().addAll(tider);
        bestillingRedigeretText.setVisible(false);

        if (valgtBestilling != null) {
            kundeChoiceBox.setValue(valgtBestilling.getKunde());
            cbTid.setValue(valgtBestilling.getBestilling_time());
            cbMedarbejder.setValue(valgtBestilling.getMedarbejder());
            datePicker.setValue(valgtBestilling.getBestilling_dato().toLocalDate());
            cbKlippetype.setValue(valgtBestilling.getKlippetype());
        }

    }


    @FXML
    public void redigerBestilling() {
        valgtBestilling.setMedarbejder(cbMedarbejder.getValue());
        valgtBestilling.setBestilling_dato(datePicker.getValue().atStartOfDay());
        valgtBestilling.setBestilling_time(cbTid.getValue());
        valgtBestilling.setKunde(kundeChoiceBox.getValue());
        valgtBestilling.setKlippetype(cbKlippetype.getValue());

        iBestillinger.updateBestilling(valgtBestilling);

        bestillingRedigeretText.setVisible(true);
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
