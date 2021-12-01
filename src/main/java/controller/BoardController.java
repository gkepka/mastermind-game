package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private IntegerProperty guessCount;

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
        guessCount = new SimpleIntegerProperty();
    }

    public void setModel(Board board) {
        this.board = board;
        guessCount.bind(board.getCurrentGuessProperty());
        guessCount.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                board.deactivateGuess((Integer) oldValue);
            }
            if (newValue != null && (Integer) newValue < guesses.size()) {
                currentGuess = guesses.get((Integer) newValue);
                currentGuess.setModel(board, board.getGuess(guessCount.get()));
            }
        });
        currentGuess = guesses.get((Integer) guessCount.get());
        currentGuess.setModel(board, board.getGuess(guessCount.get()));
    }

}
