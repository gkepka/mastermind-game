package controller;

import events.ViewUpdateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameSelectionController extends AnchorPane {
    private final EventHandler<ActionEvent> onNewGame = e -> {
        // TODO: jakoś trzeba poinformować MasterMind'a o wybranym poziomie trudności
        // żeby przygotował i zastosował odpowiedni model
        // można np. zrobić pole w ViewUpdateEvent

        var event = new ViewUpdateEvent(ViewUpdateEvent.NEW_GAME);
        this.fireEvent(event);
    };

    @FXML
    private Button newGame;

    public GameSelectionController() {
        try {
            var url = getClass().getResource("/view/gameSelectionView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            newGame.addEventHandler(ActionEvent.ACTION, onNewGame);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
