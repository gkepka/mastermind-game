package controller;

import events.GameSelectionEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.game.Player;

import java.io.IOException;

public class GameSelectionController extends AnchorPane {

    private static final Integer[] difficultyLevels = { 5, 6, 7, 8, 9, 10, 11, 12 };

    private Player player;

    @FXML
    private Label playerLabel;
    @FXML
    private Button newGame;
    @FXML
    private ChoiceBox<Integer> difficulty;

    private final EventHandler<ActionEvent> onNewGame = e -> {
        var gameDifficulty = difficulty.getValue();
        var event = new GameSelectionEvent(gameDifficulty);

        this.fireEvent(event);
    };

    public GameSelectionController() {
        try {
            var url = getClass().getResource("/view/gameSelectionView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            newGame.addEventHandler(ActionEvent.ACTION, onNewGame);
            difficulty.getItems().addAll(difficultyLevels);
            difficulty.setValue(12);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Player getModel() {
        return player;
    }

    public void setModel(Player player) {
        this.player = player;
        playerLabel.setText(String.format("Witaj %s!", player.getLogin()));
    }
}
