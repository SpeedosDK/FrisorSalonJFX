package sample.frisorsalonjfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;
import java.io.IOException;
import java.time.*;

public class MenuController {

    @FXML
    private Button btnBestillinger;
    @FXML
    private Button btnKunder;
    @FXML
    private Button btnKlippetype;
    @FXML
    private Button btnMedarbejder;

    @FXML
    public void changeToBestillinger() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør bestillinger.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnBestillinger.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToKunder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OpretKunde.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnKunder.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToKlippetype() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør klippetype.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnKlippetype.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToMedarbejder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør opret medarbejder.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnMedarbejder.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
