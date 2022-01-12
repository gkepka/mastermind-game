package model.game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class Code {
    public static final int PEGS_COUNT = 4;

    private final List<CodePeg> codePegs = new ArrayList<>(PEGS_COUNT);
    private final BooleanProperty finalised = new SimpleBooleanProperty(false);

    public Code(boolean isGuess) {
        for (int i = 0; i < PEGS_COUNT; i++) {
            codePegs.add(isGuess
                    ? new CodePeg()
                    : new CodePeg(CodePegColor.getRandomColor())
            );
        }
    }

    public CodePeg getCodePeg(int index) {
        return codePegs.get(index);
    }

    public void finaliseCode() {
        finalised.setValue(true);
    }

    public boolean isFinalised() {
        return finalised.get();
    }

    public BooleanProperty finalisedProperty() {
        return finalised;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (var peg : codePegs) {
            result.append(peg.toString()).append(" ");
        }
        return result.toString();
    }

    public List<CodePeg> getCodePegs() {
        return codePegs;
    }
}
