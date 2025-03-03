package sample.frisorsalonjfx.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import sample.frisorsalonjfx.IAuthService;
import sample.frisorsalonjfx.LoginException;

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

    IAuthService authService;


    public LoginController() {}
//    public LoginController(IAuthService authService) {
//        this.authService = authService;
//    }

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

        boolean loggedIn;
        try {
            loggedIn = authService.login(username, password);
        } catch (LoginException e) {
            wrongLoginLabel.setText(e.getMessage());
            return;
        }

        if (loggedIn) {
            try{
                changeToMenu();
            } catch (IOException e) {
                wrongLoginLabel.setText("Kunne ikke skifte scene");
                e.printStackTrace();
            }
        } else {
            wrongLoginLabel.setText("Forkert brugernavn eller password");
        }
    }

    private boolean isValidInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
    
    @FXML
    public void changeToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/frisorsalonjfx/Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
