package controller;


import events.GameFinishedEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import model.Game;

import java.io.IOException;

public class GameController extends BorderPane {
    @FXML
    private BoardController boardController;

    public GameController() {
        try {
            var url = getClass().getResource("/view/gameView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.setClassLoader(getClass().getClassLoader());
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void setModel(Game game) {
        boardController.setModel(game.getBoard());

        game.overProperty().addListener(over -> this.fireEvent(new GameFinishedEvent(game.getResult())));
    }

}

