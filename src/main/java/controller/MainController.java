package controller;


import javafx.fxml.FXML;
import model.Board;
import model.Game;

public class MainController {

    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {

    }

    public void setModel(Game game) {
        boardController.setModel(game.getBoard());
    }

}

