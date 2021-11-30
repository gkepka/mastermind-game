package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.CodePeg;

public class CodePegController {

    @FXML
    private Circle button; // TODO: chujowa nazwa, niech ktoś zmieni

    private CodePeg peg;

    @FXML
    public void initialize() {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (peg == null) return; // TODO: wylaczyc handler jak kliknie sie checkmark
            peg.cycleColor();
        });
    }

    public void setModel(CodePeg peg) {
        this.peg = peg;
        button.fillProperty().bind(peg.colorObjectProperty());
    }
}
