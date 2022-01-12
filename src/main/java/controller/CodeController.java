package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import model.Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeController extends HBox {

    private final List<CodePegController> codePegs = new ArrayList<>(Code.PEGS_COUNT);
    private Code code;

    public CodeController() {
        try {
            var url = getClass().getResource("/view/codeView.fxml");
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
    public void initialize() {
        for (Node child : this.getChildren()) {
            if (child instanceof CodePegController) {
                codePegs.add((CodePegController) child);
            }
        }
    }

    public void setModel(Code code) {
        this.code = code;

        for (int i = 0; i < Code.PEGS_COUNT; i++) {
            codePegs.get(i).setModel(code.getCodePeg(i));
        }
    }

    public Code getModel() {
        return this.code;
    }

    public void deactivate() {
        for (var codePeg : codePegs) {
            codePeg.deactivate();
        }
    }

    public void activate() {
        for (var codePeg : codePegs) {
            codePeg.activate();
        }
    }
}
