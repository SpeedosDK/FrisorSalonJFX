package sample.frisorsalonjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedarbejderController {


    @FXML
    private TextField txfName;
    @FXML
    private TextField txfPassword;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private CheckBox cbAdmin;

    @FXML
    private TableView<Medarbejder> tblMedarbejder;
    @FXML
    private TableColumn<Medarbejder, String> tcName;

    MedarbejderLogic medarbejderLogic = new MedarbejderLogic();

    private ObservableList<Medarbejder> medarbejderList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblMedarbejder.setItems(medarbejderLogic.getAllMedarbejder());
    }

    @FXML
    public void addMedarbejder() {
        String name = txfName.getText();
        String password = txfPassword.getText();
        Boolean admin = cbAdmin.isSelected();
        try {
            medarbejderLogic.addMedarbejder(name, password, admin);
            medarbejderList.clear();
            medarbejderList.addAll(medarbejderLogic.getAllMedarbejder());
            tblMedarbejder.setItems(medarbejderList);
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

    }



}
