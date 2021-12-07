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
    private Board board;
    private List<GuessController> guesses;
    private GuessController currentGuess;
    private int guessCount = 0;

    public BoardController() {
        try {
            var url = getClass().getResource("/view/boardView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void initialize() {
        guesses = new ArrayList<>();
        for (Node child : this.getChildren()) {
            if (child instanceof GuessController) {
                guesses.add((GuessController) child);
            }
        }
        currentGuess = guesses.get(guessCount);
    }

    public void setModel(Board board) {
        this.board = board;
        currentGuess.setModel(board, board.getGuess(guessCount), this);
    }

    public void nextGuess() {
        guessCount++;
        if (guessCount < guesses.size()) {
            currentGuess.deactivate();
            currentGuess = guesses.get(guessCount);
            currentGuess.setModel(board, board.getGuess(guessCount), this);
        } else {
            // TODO: wyświetlanie kodu
            System.out.println("Last check");
        }
    }
}
