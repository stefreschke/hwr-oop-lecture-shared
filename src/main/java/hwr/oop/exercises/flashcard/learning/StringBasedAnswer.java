package hwr.oop.exercises.flashcard.learning;

import java.util.Objects;

class StringBasedAnswer implements Answer {
    private final String optionText;

    public StringBasedAnswer(String optionText) {
        this.optionText = optionText;
    }

    @Override
    public boolean isSolutionTo(FlashCard card) {
        return card.isCorrectAnswer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Option)) {
            return false;
        }
        return Objects.equals(optionText, o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionText);
    }

    @Override
    public String toString() {
        return optionText;
    }
}
