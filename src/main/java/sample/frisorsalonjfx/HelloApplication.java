package sample.frisorsalonjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.Database.MedarbejderRepo;
import sample.frisorsalonjfx.UIControllers.LoginController;
import sample.frisorsalonjfx.UseCases.LoginLogic;
import sample.frisorsalonjfx.UseCases.MedarbejderLogic;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Frisør login.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setAuthService(new LoginLogic(new MedarbejderRepo(), new MedarbejderLogic(new MedarbejderRepo())));

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Monikas frisørsalon");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}