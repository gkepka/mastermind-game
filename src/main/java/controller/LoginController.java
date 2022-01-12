package controller;

import dao.PlayerDao;
import events.ViewUpdateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class LoginController extends TilePane {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    private final EventHandler<ActionEvent> loginHandler = e -> {
        // TODO: połączenie z bazą
        PlayerDao loginPlayer = new PlayerDao();

        var loginText = login.getText();
        var passwordText = password.getText();

        if (loginText.isBlank()) {
            this.error("Podane dane sa niepoprawne", "Pole `login` jest wymagane.");
            return;
        }

        if (passwordText.isBlank()) {
            this.error("Podane dane sa niepoprawne", "Pole `haslo` jest wymagane.");
            return;
        }

        var player = loginPlayer.loginCheck(loginText, passwordText);

        if (player.isPresent()) {
            var event = new ViewUpdateEvent(ViewUpdateEvent.USER_LOGON);
            this.fireEvent(event);
        } else {
            this.error("Blad logowania",
                    "Podany uzytkownik nie istnieje lub haslo jest niepoprawne. Sprobuj ponownie.");
        }
    };

    public LoginController() {
        try {
            var url = getClass().getResource("/view/loginView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            loginButton.addEventHandler(ActionEvent.ACTION, loginHandler);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private Optional<ButtonType> error(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
