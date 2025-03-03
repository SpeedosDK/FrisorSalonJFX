package sample.frisorsalonjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.frisorsalonjfx.Database.KlippetypeRepo;

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

}
