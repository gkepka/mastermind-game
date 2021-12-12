package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController extends VBox {
    private List<GuessController> guesses;
    private Board board;

    public BoardController() {
        try {
            var url = getClass().getResource("/view/boardView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.setClassLoader(getClass().getClassLoader());
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void initialize() {
        guesses = new ArrayList<>(Board.GUESS_COUNT);
        for (Node child : this.getChildren()) {
            if (child instanceof GuessController) {
                guesses.add((GuessController) child);
            }
        }
    }

    public void setModel(Board board) {
        this.board = board;
        for (int i = 0; i<Board.GUESS_COUNT; i++) {
            guesses.get(i).setModel(board.getGuess(i));
        }
    }
}
