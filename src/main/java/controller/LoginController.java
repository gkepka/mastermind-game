package controller;

import events.ViewUpdateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController extends Pane {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    private final EventHandler<ActionEvent> loginHandler = event -> {
        // TODO: połączenie z bazą
        this.fireEvent(new ViewUpdateEvent());
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
