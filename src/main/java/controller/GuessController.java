package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import model.Board;
import model.Guess;


public class GuessController {

    @FXML
    private CodePegController codePeg1Controller;
    @FXML
    private CodePegController codePeg2Controller;
    @FXML
    private CodePegController codePeg3Controller;
    @FXML
    private CodePegController codePeg4Controller;

    @FXML
    private SVGPath checkmark;

    private Guess guess;
    private Board board;

    @FXML
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

        // TODO: to jest wyjatkowo chujowe
        // bardzo za to przepraszam, ale chciałem jakoś doprowadzić to do działania
        // może lepiej zrobić jeszcze jeden view z samym Code, ale już i tak za dużo nasrałem zagneieżdżonych fxml'i
        codePeg1Controller.setModel(guess.getMyCode().getCodePeg(0));
        codePeg2Controller.setModel(guess.getMyCode().getCodePeg(1));
        codePeg3Controller.setModel(guess.getMyCode().getCodePeg(2));
        codePeg4Controller.setModel(guess.getMyCode().getCodePeg(3));
    }
}
