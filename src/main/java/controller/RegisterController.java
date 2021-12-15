package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterController extends TilePane {
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField login;

    public RegisterController() {
        super();

        try {
            var url = getClass().getResource("/view/registerView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        // TODO: Baza danych
        System.out.println(
                "Email is " + (emailPattern.matcher(email.getText()).matches()
                    ? "valid"
                    : "invalid")
        );
    }
}
