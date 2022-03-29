package controller;

import events.GameExitEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class GameOverController extends AnchorPane {

    @FXML
    private Label result;

    @FXML
    private Button nextGameButton;

    public GameOverController() {
        try {
            var url = getClass().getResource("/view/gameOver.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            nextGameButton.addEventHandler(ActionEvent.ACTION, this::onNextGame);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void setResultValue(int result) {
        this.result.setText(
                String.format("Wynik: %d", result)
        );
    }

    public void onNextGame(ActionEvent event) {
        this.fireEvent(new GameExitEvent());
    }
}
