package controller.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import model.game.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardController extends VBox {
    private List<GuessController> guesses;
    private Board board;

    public BoardController() {
        try {
            var url = getClass().getResource("/view/game/boardView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.setClassLoader(getClass().getClassLoader());
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setModel(Board board) {
        this.board = board;
        guesses = new ArrayList<>(board.getGuessCount());

        for (var guess : board.getGuesses()) {
            var guessController = new GuessController();
            guessController.setModel(guess);
            guesses.add(guessController);
        }

        Collections.reverse(guesses);
        this.getChildren().addAll(guesses);
    }
}
