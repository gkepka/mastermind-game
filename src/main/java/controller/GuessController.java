package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import model.Board;
import model.Guess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuessController extends HBox {

    @FXML
    private SVGPath checkmark;

    private Guess guess;
    private Board board;
    private List<CodePegController> codePegs;
    private EventHandler check = event -> {
        if (guess == null || guess.isVerified()) return;
        guess.verifyGuess();
        System.out.println("weryfikacja działa");
    };

    private ObjectProperty<Boolean> isActive;

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
    }

    // initialize() jest zawsze wołane zaraz po konstruktorze, o ile jest zdefioniowane
    public void initialize() {
        codePegs = new ArrayList<>();
        for (Node child : this.getChildren()) {
            if (child instanceof CodePegController) {
                codePegs.add((CodePegController) child);
            }
        }
        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, check);
        isActive = new SimpleObjectProperty<>();
    }

    public void setModel(Board board, Guess guess) {
        this.board = board;
        this.guess = guess;
        this.guess.activate();
        isActive.bind(guess.isActiveObjectProperty());
        isActive.addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue) {
                checkmark.removeEventHandler(MouseEvent.MOUSE_CLICKED, check);
            }
        });
        // TODO: Wymyśleć lepszy sposób na ustawianie modeli
        codePegs.forEach(codePeg -> codePeg.setModel(guess.getMyCode().getCodePeg(codePegs.indexOf(codePeg))));
    }

}
