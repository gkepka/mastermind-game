package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Board;
import model.Code;
import model.Guess;

import java.util.List;

public class BoardController {

    @FXML
    private GuessController guessController;

    private Board board;
    private List<GuessController> guesses;

    @FXML
    public void initialise() {

    }

    public void setModel(Board board) {
        this.board = board;
        guessController.setModel(board, board.getGuess(0)); // boże ale gówno
    }
}
