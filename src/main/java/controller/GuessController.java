package controller;

import events.PegClickedEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Code;
import model.Guess;

import java.io.IOException;

public class GuessController extends HBox {

    @FXML
    private VBox checkmark;
    @FXML
    private CodeController codeController;
    @FXML
    private HintPegController hintPegController;

    private static final String CSS_CLASS = "currentGuess";
    private final IntegerProperty touchedCodePegs = new SimpleIntegerProperty(0);
    private Guess guess;

    private final EventHandler<MouseEvent> checkmarkClickedHandler = event -> {
        if (guess == null || !guess.isActive()) return;
        guess.verifyGuess();
        codeController.deactivate();
        removeCheckmarkClickedHandler();
    };

    private final EventHandler<PegClickedEvent> pegClickedHandler = event -> {
        touchedCodePegs.set(touchedCodePegs.get() + 1);

        if (touchedCodePegs.get() == 4) {
            System.out.println("Teraz się powinien checkmark pojawić.");
            removePegClickedHandler();
        }
    };

    // Jak obecny guess stanie się currentGuess (jego activeProperty zrobi się true), to zmień kolor tła.
    private final ChangeListener<Boolean> backgroundChangeHandler = (observable, oldValue, newValue) -> {
        if (newValue) {
            this.getStyleClass().add(CSS_CLASS);
            codeController.activate();
        } else {
            this.getStyleClass().remove(CSS_CLASS);
        }
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

    public void setModel(Guess guess) {
        this.guess = guess;
        addHandlers();

        // handler wywoływany z palca
        // to wygląda idiotycznie, ale nie wiem w jaki lepszy sposób obsłużyć pierwszy przypadek.
        backgroundChangeHandler.changed(guess.activeProperty(), false, guess.isActive());

        checkmark.visibleProperty().bind(
                // checkmark appears if the guess is active (current) and all pegs have been clicked once
                (guess.activeProperty().and(touchedCodePegs.isEqualTo(Code.PEGS_COUNT)))
        );

        codeController.setModel(guess.getMyCode());
        hintPegController.setModel(guess.getHints());
    }

    private void addHandlers() {
        guess.activeProperty().addListener(backgroundChangeHandler);
        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, checkmarkClickedHandler);
        this.addEventHandler(PegClickedEvent.PEG_CLICKED, pegClickedHandler); // metoda VBoxa
    }

    private void removeCheckmarkClickedHandler() {
        checkmark.removeEventHandler(MouseEvent.MOUSE_CLICKED, checkmarkClickedHandler);
    }

    private void removePegClickedHandler() {
        this.removeEventHandler(PegClickedEvent.PEG_CLICKED, pegClickedHandler);
    }

    public void deactivate() {
        removeCheckmarkClickedHandler();
        removePegClickedHandler();
        guess.activeProperty().removeListener(backgroundChangeHandler);
    }
}
