package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import model.Board;
import model.Guess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuessController extends HBox {

    @FXML
    private VBox checkmark;

    @FXML
    private CodeController codeController;

    @FXML
    private HintPegController hintPegController;


    private Guess guess;
    private Board board;
    private BoardController boardController;
    private final EventHandler<MouseEvent> check = event -> {
        if (guess == null || guess.isVerified()) return;
        guess.verifyGuess();
        codeController.deactivate();
        boardController.nextGuess();
        System.out.println("weryfikacja działa");
    };

    public GuessController() {
        try {
            var url = getClass().getResource("/view/guessView.fxml");
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
        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, check);
    }

    public void setModel(Board board, Guess guess, BoardController boardController) {
        this.board = board;
        this.guess = guess;
        this.boardController = boardController;

        codeController.setModel(guess.getMyCode());
    }

    public void deactivate() {
        checkmark.removeEventHandler(MouseEvent.MOUSE_CLICKED, check);
    }

}
