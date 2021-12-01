package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import model.Board;
import model.Guess;

import java.io.IOException;

public class GuessController extends HBox {

    @FXML
    private CodePegController codePeg1;
    @FXML
    private CodePegController codePeg2;
    @FXML
    private CodePegController codePeg3;
    @FXML
    private CodePegController codePeg4;
    @FXML
    private SVGPath checkmark;

    private Guess guess;
    private Board board;

    public GuessController() {
        try {
            var url = getClass().getResource("/view/guessView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (guess == null || guess.isVerified()) return;
            guess.verifyGuess();
            System.out.println("weryfikacja działa");
        });
    }

    // Teraz initialize nie jest wołane, wszystko jest robione w konstruktrze
    // WAŻNE: cokolwiek robione na zmmiennych, ma być robione po loader.load()
    // inaczej null pointer
    public void initialize() {
        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (guess == null || guess.isVerified()) return;
            guess.verifyGuess();
            System.out.println("weryfikacja działa");
        });
    }

    public void setModel(Board board, Guess guess) {
        this.board = board;
        this.guess = guess;

        // TODO: Wymyśleć lepszy sposób na ustawianie modeli
        codePeg1.setModel(guess.getMyCode().getCodePeg(0));
        codePeg2.setModel(guess.getMyCode().getCodePeg(1));
        codePeg3.setModel(guess.getMyCode().getCodePeg(2));
        codePeg4.setModel(guess.getMyCode().getCodePeg(3));
    }
}
