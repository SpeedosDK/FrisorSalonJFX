package sample.frisorsalonjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Button btnGoBack;
    @FXML
    private CheckBox cbAdmin;

    @FXML
    private TableView<Medarbejder> tblMedarbejder;
    @FXML
    private TableColumn<Medarbejder, String> tcName;

    private IMedarbejder iMedarbejder;

    private ObservableList<Medarbejder> medarbejderList = FXCollections.observableArrayList();


    public MedarbejderController() {}

    public void setiMedarbejder(IMedarbejder iMedarbejder) {
        this.iMedarbejder = iMedarbejder;
        initializeData();
    }

    @FXML
    public void initialize() {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void initializeData() {
        if (iMedarbejder != null) {
            medarbejderList.addAll(iMedarbejder.getAllMedarbejder());
            tblMedarbejder.setItems(medarbejderList);
        }
    }

    @FXML
    public void addMedarbejder() {
        String name = txfName.getText();
        String password = txfPassword.getText();
        Boolean admin = cbAdmin.isSelected();
        try {
            iMedarbejder.addMedarbejder(name, password, admin);
            medarbejderList.clear();
            medarbejderList.addAll(iMedarbejder.getAllMedarbejder());
            tblMedarbejder.setItems(medarbejderList);
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void deleteMedarbejder() {
       try {
           if (tblMedarbejder.getSelectionModel().getSelectedItem() != null) {
               Medarbejder medarbejder = tblMedarbejder.getSelectionModel().getSelectedItem();
               iMedarbejder.deleteMedarbejder(medarbejder);
               tblMedarbejder.getItems().remove(medarbejder);
           } else {
               System.out.println("Vælg medarbejder først");
           }
       } catch (SecurityException e) {
           System.out.println(e.getMessage());
       }
    }

    @FXML
    public void goToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
