package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.CodePeg;

import java.io.IOException;

public class CodePegController extends VBox {

    @FXML
    private Circle pegButton;

    private CodePeg peg;

    public CodePegController() {
        super();

        try {
            var url = getClass().getResource("/view/codePegView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // @FXML
    public void initialize() {
        pegButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // TODO: wylaczyc handler jak kliknie sie checkmark
            if (peg == null) return;

            peg.cycleColor();
        });
    }

    public void setModel(CodePeg peg) {
        this.peg = peg;
        pegButton.fillProperty().bind(peg.colorObjectProperty());
    }
}
