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
import model.Player;

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
    @FXML
    private Button registerButton;

    private final EventHandler<ActionEvent> registerHandler = e -> {
        PlayerDao registerPLayer = new PlayerDao();
        if(emailPattern.matcher(email.getText()).matches() && password.getLength()>0 && login.getLength()>0
                && password.getText().equals(confirmPassword.getText())){

            if(!registerPLayer.checkIfTaken(login.getText(), email.getText())){
                new PlayerDao().create(login.getText(), email.getText(), password.getText());
            } else {
                System.out.println("Konto o danym loginie lub mailu jest zajęte");
            }
        }else {
            System.out.println("Złe dane");
            System.out.println(
                    "Email is " + (emailPattern.matcher(email.getText()).matches()
                            ? "valid"
                            : "invalid")
            );
        }
    };

    public RegisterController() {
        super();

        try {
            var url = getClass().getResource("/view/registerView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            registerButton.addEventHandler(ActionEvent.ACTION, registerHandler);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        // TODO: Baza danych
        if(emailPattern.matcher(email.getText()).matches() && password.getLength()>0 && login.getLength()>0 && password.equals(confirmPassword)){
            new PlayerDao().save(new Player(login.toString(), email.toString(), password.toString()));
        }
        System.out.println(
                "Email is " + (emailPattern.matcher(email.getText()).matches()
                    ? "valid"
                    : "invalid")
        );
    }
}
