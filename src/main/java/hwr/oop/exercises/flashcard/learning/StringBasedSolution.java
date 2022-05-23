package hwr.oop.exercises.flashcard.learning;

import java.util.Objects;

class StringBasedSolution implements Solution {
    private final String solutionText;

    public StringBasedSolution(String solutionText) {
        this.solutionText = solutionText;
    }

    @Override
    public boolean isSolutionTo(FlashCard card) {
        return this.equals(card.getSolution());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Option)) {
            return false;
        }
        return Objects.equals(solutionText, o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionText);
    }

    @Override
    public String toString() {
        return solutionText;
    }
}
