package sample.frisorsalonjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class KlippetypeController {

    @FXML
    private TextField txfKlippetype;
    @FXML
    private TextField txfPris;
    @FXML
    private TextField txfKlippeTid;

    @FXML
    private Button btnOpret;

    private IKlippeType klippeType;

    public KlippetypeController() {}

    public void setKlippeType(IKlippeType klippeType) {
        this.klippeType = klippeType;
    }

    @FXML
    public void opretKlippeType() {

        String klippetype = txfKlippetype.getText();
        int pris = Integer.parseInt(txfPris.getText());
        int klippeTid = Integer.parseInt(txfKlippeTid.getText());

        klippeType.createKlippeType(klippetype, pris, klippeTid);

    }

}
