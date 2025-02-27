package sample.frisorsalonjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class KundeController {

    @FXML
    private TextField kundeName;
    @FXML
    private TextField kundeTelefon;
    @FXML
    private TextField txfKundeEmail;

    @FXML
    private TableView<Kunde> tblKunde;

    @FXML
    private TableColumn<Kunde, String> colKundeName;
    @FXML
    private TableColumn<Kunde, Integer> colKundeTelefon;
    @FXML
    private TableColumn<Kunde, String> colKundeEmail;

    private ObservableList<Kunde> kundeListe = FXCollections.observableArrayList();
    @FXML
    private Button btnOpretKunde;

    KundeLogic kundeLogic = new KundeLogic();

    @FXML
    public void initialize() {
        colKundeName.setCellValueFactory(new PropertyValueFactory<Kunde, String>("name"));
        colKundeTelefon.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("Telefon"));
        colKundeEmail.setCellValueFactory(new PropertyValueFactory<Kunde, String>("Email"));
        tblKunde.setItems(kundeLogic.getKunder());
    }


    @FXML
    public void opretKunde() {
        String name = kundeName.getText();
        int telefon = Integer.parseInt(kundeTelefon.getText());
        String email = txfKundeEmail.getText();
        kundeLogic.createKunde(name, telefon, email);

        kundeListe.clear();
        kundeListe.addAll(kundeLogic.getKunder());
        tblKunde.setItems(kundeListe);
    }
}
