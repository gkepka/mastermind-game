package controller.game;

import events.game.PegClickedEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.game.CodePeg;

import java.io.IOException;

public class CodePegController extends VBox {

    @FXML
    private Circle pegButton;

    private CodePeg peg;
    private boolean clicked = false;

    private final EventHandler<MouseEvent> cycleColor = event -> {
            if (peg == null) return;

            switch (event.getButton()) {
                case PRIMARY -> peg.nextColor();
                case SECONDARY -> peg.prevColor();
            }

            if (!clicked) {
                this.fireEvent(new PegClickedEvent());
                clicked = true;
            }
    };

    public CodePegController() {
        super();

        try {
            var url = getClass().getResource("/view/game/codePegView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setModel(CodePeg peg) {
        this.peg = peg;
        pegButton.fillProperty().bind(peg.getColorProperty());
    }

    public void deactivate() {
        pegButton.removeEventHandler(MouseEvent.MOUSE_CLICKED, cycleColor);

    }

    public void activate() {
        pegButton.addEventHandler(MouseEvent.MOUSE_CLICKED, cycleColor);
    }
}
