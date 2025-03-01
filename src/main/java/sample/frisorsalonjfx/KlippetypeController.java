package sample.frisorsalonjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.frisorsalonjfx.Database.BestillingRepo;
import sample.frisorsalonjfx.Database.KlippetypeRepo;

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

    public KlippetypeController() {
        this.klippeType = new KlippeTypeLogic(new KlippetypeRepo());
    }



    @FXML
    public void opretKlippeType() {

        String klippetype = txfKlippetype.getText();
        int pris = Integer.parseInt(txfPris.getText());
        int klippeTid = Integer.parseInt(txfKlippeTid.getText());

        klippeType.createKlippeType(klippetype, pris, klippeTid);

    }

}
