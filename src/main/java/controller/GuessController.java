package controller;

import events.PegClickedEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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


    private Guess guess;
    private BoardController boardController;
    private final IntegerProperty touchedCodePegs = new SimpleIntegerProperty(0);

    private final EventHandler<MouseEvent> checkmarkClickedHandler = event -> {
        if (guess == null || !guess.isActive()) return;
        guess.verifyGuess();
        System.out.println("weryfikacja działa");
        codeController.deactivate();
        this.deactivate();
    };

    private final EventHandler<PegClickedEvent> pegClickedEventEventHandler = event -> {
        touchedCodePegs.set(touchedCodePegs.get()+1);
        if (touchedCodePegs.get() == 4) {
            System.out.println("Teraz się powinien checkmark pojawić.");
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
        checkmark.visibleProperty().bind(
                // checkmark appears if the guess is active (current) and all pegs have been clicked once
                (guess.activeProperty().and(touchedCodePegs.isEqualTo(Code.PEGS_COUNT)))
        );
        checkmark.addEventHandler(MouseEvent.MOUSE_CLICKED, checkmarkClickedHandler);
        this.addEventHandler(PegClickedEvent.PEG_CLICKED, pegClickedEventEventHandler);
        codeController.setModel(guess.getMyCode());
    }

    public void deactivate() {
        checkmark.removeEventHandler(MouseEvent.MOUSE_CLICKED, checkmarkClickedHandler);
        this.removeEventHandler(PegClickedEvent.PEG_CLICKED, pegClickedEventEventHandler);
    }
}
