package controller;


import javafx.fxml.FXML;
import model.Board;

public class MainController {

    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {

    }

    public void setModel(Board board) {
        boardController.setModel(board);
    }

}

