package controller;

import dao.PlayerDao;
import events.PlayerLoginEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.util.Pair;
import model.game.Player;
import notification.MailSender;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegisterController extends TilePane {
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final String invalidDataHeader = "Podane dane sa niepoprawne";

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

    public RegisterController() {
        super();

        try {
            var url = getClass().getResource("/view/registerView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            registerButton.addEventHandler(ActionEvent.ACTION, this::registerHandler);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    // co z tym
    @FXML
    private void onRegister(ActionEvent event) {
        // TODO: Baza danych
        if (emailPattern.matcher(email.getText()).matches() && password.getLength() > 0 && login.getLength() > 0 && password.equals(confirmPassword)) {
            new PlayerDao().save(new Player(login.toString(), email.toString(), password.toString()));
        }
        System.out.println(
                "Email is " + (emailPattern.matcher(email.getText()).matches()
                        ? "valid"
                        : "invalid")
        );
    }

    private void error(String header, String content) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void registerHandler(ActionEvent e) {
        PlayerDao registerPlayer = new PlayerDao();

        var emailText = email.getText();
        var loginText = login.getText();
        var passwordText = password.getText();
        var confirmText = confirmPassword.getText();

        var errorMessage = this.checkData(emailText, loginText, passwordText, confirmText);

        if (errorMessage.isPresent()) {
            var messagePair = errorMessage.get();
            this.error(messagePair.getKey(), messagePair.getValue());
            return;
        }

        if (registerPlayer.checkIfTaken(loginText, emailText)) {
            this.error("Blad bazy danych", "Konto o danym loginie lub mailu jest zajęte.");
            return;
        }

        var playerOptional = registerPlayer.create(login.getText(), email.getText(), password.getText());
        MailSender.getInstance().sendMail(email.getText(), "Your account was registered in Mrozon-Mastermind Game");

        if (playerOptional.isPresent()) {
            var event = new PlayerLoginEvent(playerOptional.get());
            this.fireEvent(event);
        } else {
            this.error("Blad bazy danych",
                    "Nie udalo sie zarejestrowac uzytkownika. Sprobuj ponownie pozniej");
        }
    }

    private Optional<Pair<String, String>> checkData(String emailText, String loginText, String passwordText, String confrimText) {
        if (emailText.isBlank())
            return Optional.of(new Pair<>(invalidDataHeader, "Pole `login` jest wymagane."));

        if (loginText.isBlank())
            return Optional.of(new Pair<>(invalidDataHeader, "Pole `email` jest wymagane."));

        if (passwordText.isBlank() || confrimText.isBlank())
            return Optional.of(new Pair<>(invalidDataHeader, "Pola `haslo` i `potwierdz haslo` sa wymagane."));

        if (!passwordText.equals(confrimText))
            return Optional.of(new Pair<>(invalidDataHeader, "Pola `haslo` i `potwierdz haslo` maja rozne wartosci."));

        if (!emailPattern.matcher(emailText).matches())
            return Optional.of(new Pair<>(invalidDataHeader, "Pole `email` jest niepoprawnie zbudowane."));

        return Optional.empty();
    }
}
