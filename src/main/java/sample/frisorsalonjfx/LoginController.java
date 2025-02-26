package sample.frisorsalonjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.scene.control.*;

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

    Logic logic = new Logic();

    @FXML
    public void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loggedIn = logic.login(username, password);
        if (loggedIn) {
            try {
                changeToMenu();
            } catch (LoginException | IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            wrongLoginLabel.setText("Wrong username or password");
        }

    }
    
    @FXML
    public void changeToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Frisør hoved menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
