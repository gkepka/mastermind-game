package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.HintPeg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HintPegController extends VBox {
    private final List<Circle> hintPegView = new ArrayList<>(HintPeg.PEGS_COUNT);

    public HintPegController() {
        try {
            var url = getClass().getResource("/view/hintPegView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.setClassLoader(getClass().getClassLoader());
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void initialize() { // temp
        for (Node child : this.getChildren()) {
            if (!(child instanceof HBox)) throw new IllegalStateException("Dupa");
            HBox hBox = (HBox) child;
            for (Node peg : hBox.getChildren()) {
                if (!(peg instanceof Circle)) throw new IllegalStateException("dupa2");
                Circle temp = (Circle) peg;
                hintPegView.add(temp);
            }
        }
    }

    public void setModel(List<HintPeg> hintPegs) {
        for(int i = 0; i<HintPeg.PEGS_COUNT; i++) {
            hintPegView.get(i).fillProperty().bind(hintPegs.get(i).colorObjectProperty());
        }
    }
}
