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


    public void setModel(Board board) {
        this.board = board;
        guesses = new ArrayList<>(board.getGuessCount());
        for (int i = 0; i<board.getGuessCount(); i++) {
            var guessController = new GuessController();
            guessController.setModel(board.getGuess(i));
            guesses.add(guessController);
        }
        this.getChildren().addAll(guesses);
    }
}
