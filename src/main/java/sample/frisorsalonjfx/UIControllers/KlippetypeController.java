package sample.frisorsalonjfx.UIControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.frisorsalonjfx.Database.KlippetypeRepo;
import sample.frisorsalonjfx.IKlippeType;
import sample.frisorsalonjfx.UseCases.KlippeTypeLogic;
import sample.frisorsalonjfx.Model.Klippetype;

import java.io.IOException;

public class KlippetypeController {

    @FXML
    private TextField txfKlippetype;
    @FXML
    private TextField txfPris;
    @FXML
    private TextField txfKlippeTid;
    @FXML
    private TableView<Klippetype> tvKlippetype;
    @FXML
    private TableColumn<Klippetype, String> colKlippetypeName;
    @FXML
    private TableColumn<Klippetype, Integer> colKlippePris;
    @FXML
    private TableColumn<Klippetype, Integer> colKlippeTid;

    @FXML
    private Button btnOpret;

    @FXML
    private Button backButton;

    private ObservableList<Klippetype> listKlippetype = FXCollections.observableArrayList();
    private IKlippeType klippeType;

    public KlippetypeController() {
        this.klippeType = new KlippeTypeLogic(new KlippetypeRepo());

    }

    @FXML
    private void initialize() {
        colKlippetypeName.setCellValueFactory(new PropertyValueFactory<>("klippeStil"));
        colKlippePris.setCellValueFactory(new PropertyValueFactory<>("price"));
        colKlippeTid.setCellValueFactory(new PropertyValueFactory<>("timeForCut"));
        if (klippeType != null) {
            listKlippetype.addAll(klippeType.getKlippeTyper());
            tvKlippetype.setItems(klippeType.getKlippeTyper());
        }
    }


    @FXML
    public void opretKlippeType() {

        String klippetype = txfKlippetype.getText();
        int pris = Integer.parseInt(txfPris.getText());
        int klippeTid = Integer.parseInt(txfKlippeTid.getText());
        klippeType.createKlippeType(klippetype, pris, klippeTid);

        listKlippetype.clear();
        listKlippetype.addAll(klippeType.getKlippeTyper());
        tvKlippetype.setItems(listKlippetype);

    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
