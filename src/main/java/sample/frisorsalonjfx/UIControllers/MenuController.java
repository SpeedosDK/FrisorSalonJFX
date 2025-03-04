package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.frisorsalonjfx.Database.KundeRepo;
import sample.frisorsalonjfx.Database.MedarbejderRepo;
import sample.frisorsalonjfx.LogicHolder;
import sample.frisorsalonjfx.UseCases.KundeLogic;
import sample.frisorsalonjfx.UseCases.MedarbejderLogic;

import java.io.IOException;

public class MenuController {

    private MedarbejderLogic medarbejderLogic;

    @FXML
    private Button btnBestillinger;
    @FXML
    private Button btnKunder;
    @FXML
    private Button btnKlippetype;
    @FXML
    private Button btnMedarbejder;


    @FXML
    public void initialize() {
        MedarbejderLogic medarbejderLogic = new MedarbejderLogic(new MedarbejderRepo());
        setMedarbejderLogic(medarbejderLogic);
    }

    public void setMedarbejderLogic(MedarbejderLogic medarbejderLogic) {
        LogicHolder.getInstance().setMedarbejderLogic(medarbejderLogic);
    }
    @FXML
    public void changeToBestillinger() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør bestillinger.fxml"));
        Parent root = fxmlLoader.load();

//        BestillingerController bestillingerController = fxmlLoader.getController();
//        bestillingerController.setiBestillinger(new BestillingLogic(new BestillingRepo(), new MedarbejderRepo(), new KlippetypeRepo(), new KundeRepo()));
        Stage stage = (Stage) btnBestillinger.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToKunder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/OpretKunde.fxml"));
        Parent root = fxmlLoader.load();

        KundeController kundeController = fxmlLoader.getController();
        kundeController.setIKunder(new KundeLogic(new KundeRepo()));
        Stage stage = (Stage) btnKunder.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToKlippetype() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør klippetype.fxml"));
        Parent root = fxmlLoader.load();

//        KlippetypeController klippetypeController = fxmlLoader.getController();
//        klippetypeController.setKlippeType(new KlippeTypeLogic(new KlippetypeRepo()));
        Stage stage = (Stage) btnKlippetype.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToMedarbejder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør opret medarbejder.fxml"));
        Parent root = fxmlLoader.load();

        MedarbejderController medarbejderController = fxmlLoader.getController();
        medarbejderController.setiMedarbejder(LogicHolder.getInstance().getMedarbejderLogic());
        Stage stage = (Stage) btnMedarbejder.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
