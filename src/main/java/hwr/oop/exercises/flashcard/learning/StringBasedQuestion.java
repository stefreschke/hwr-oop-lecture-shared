package hwr.oop.exercises.flashcard.learning;

import java.util.Objects;

class StringBasedQuestion implements Question {
    private final String questionText;

    public StringBasedQuestion(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringBasedQuestion that = (StringBasedQuestion) o;
        return questionText.equals(that.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText);
    }

    @Override
    public String toString() {
        return questionText;
    }
}
