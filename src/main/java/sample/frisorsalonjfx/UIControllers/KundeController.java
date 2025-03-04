package sample.frisorsalonjfx.UIControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.frisorsalonjfx.IKunder;
import sample.frisorsalonjfx.Model.Kunde;

import java.io.IOException;

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
    @FXML
    private Button sletKunde;

    @FXML
    private Button backButton;



    private IKunder iKunder;

    public KundeController() {
    }

    public void setIKunder(IKunder iKunder) {
        this.iKunder = iKunder;
        initData();
    }
    @FXML
    public void initialize() {
        colKundeName.setCellValueFactory(new PropertyValueFactory<Kunde, String>("name"));
        colKundeTelefon.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("Telefon"));
        colKundeEmail.setCellValueFactory(new PropertyValueFactory<Kunde, String>("Email"));

    }

    public void initData() {
        if (iKunder != null) {
            kundeListe.addAll(iKunder.getKunder());
            tblKunde.setItems(iKunder.getKunder());
        }
    }


    @FXML
    public void opretKunde() {
        String name = kundeName.getText();
        int telefon = Integer.parseInt(kundeTelefon.getText());
        String email = txfKundeEmail.getText();
        iKunder.createKunde(name, telefon, email);

        kundeListe.clear();
        kundeListe.addAll(iKunder.getKunder());
        tblKunde.setItems(kundeListe);
    }

    @FXML
    public void sletKunde() {
        Kunde slettetKunde = tblKunde.getSelectionModel().getSelectedItem();
        if (slettetKunde != null) {
            iKunder.deleteKunde(slettetKunde);
            tblKunde.getItems().remove(slettetKunde);
        }
    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
