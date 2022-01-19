package controller;

import dao.GameDao;
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

    //                                                { 5, 6, 7, 8, 9, 10, 11, 12 }
    private static final Integer[] difficultyLevels = { 12, 11, 10, 9, 8, 7, 6, 5 };

    private Player player;

    @FXML
    private Label playerLabel;
    @FXML
    private Button newGame;
    @FXML
    private ChoiceBox<Integer> difficulty;
    @FXML
    private Label playerAverage;

    public GameSelectionController() {
        try {
            var url = getClass().getResource("/view/gameSelectionView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            newGame.addEventHandler(ActionEvent.ACTION, this::onNewGame);
            difficulty.getItems().addAll(difficultyLevels);
            difficulty.setValue(difficultyLevels[0]);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private void onNewGame(ActionEvent event) {
        var gameDifficulty = difficulty.getValue();
        var gameSelectionEvent = new GameSelectionEvent(gameDifficulty);

        this.fireEvent(gameSelectionEvent);
    }

    public Player getModel() {
        return player;
    }

    public void setModel(Player player) {
        var gameDao = new GameDao();
        this.player = player;
        playerLabel.setText(String.format("Witaj %s!", player.getLogin()));

        double average = gameDao.getPlayerStatistics(player);
        playerAverage.setText(String.format("Twoja średnia: %f", average));
    }
}
