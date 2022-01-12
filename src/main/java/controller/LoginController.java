package controller;

import dao.PlayerDao;
import events.ViewUpdateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.io.IOException;

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
        if(loginPlayer.loginCheck(login.getText(),password.getText()).isPresent()) {
            var event = new ViewUpdateEvent(ViewUpdateEvent.USER_LOGON);
            this.fireEvent(event);
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
}
