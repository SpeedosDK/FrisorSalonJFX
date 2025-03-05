package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import sample.frisorsalonjfx.Database.MedarbejderRepo;
import sample.frisorsalonjfx.IAuthService;
import sample.frisorsalonjfx.LoginException;
import sample.frisorsalonjfx.Model.Medarbejder;
import sample.frisorsalonjfx.UseCases.LoginLogic;
import sample.frisorsalonjfx.UseCases.MedarbejderLogic;
import sample.frisorsalonjfx.UserHolder;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLoginLabel;

    //IAuthService authService;

    IAuthService authService;
    public LoginController() {}
    public LoginController(IAuthService authService) {
        this.authService = authService;
    }

    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }

    @FXML
    public void onLoginButtonClick() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!isValidInput(username, password)) {
            wrongLoginLabel.setText("Ugyldigt input. Prøv igen");
            return;
        }

        Medarbejder medarbejder;
        try {
            medarbejder = authService.login(username, password);
            if (medarbejder == null) {
                wrongLoginLabel.setText("Medarbejder findes ikke");
                return;
            }
            System.out.println(medarbejder.getName());
        } catch (LoginException e) {
            wrongLoginLabel.setText(e.getMessage());
            return;
        }

        try {
            changeToMenu();
        } catch (IOException e) {
            wrongLoginLabel.setText("Kunne ikke skifte scene");
            e.printStackTrace();
        }
    }

    private boolean isValidInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    @FXML
    public void changeToMenu() throws IOException {
        MedarbejderLogic medarbejderLogic = authService.getMedarbejderLogic();
        Medarbejder currentUser = authService.getMedarbejderLogic().getCurrentUser();

        UserHolder.getInstance().setCurrentUser(currentUser);


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();

        MenuController menuController = fxmlLoader.getController();
        menuController.setMedarbejderLogic(authService.getMedarbejderLogic());
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
