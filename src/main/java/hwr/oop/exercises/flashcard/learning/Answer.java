package hwr.oop.exercises.flashcard.learning;

public interface Answer extends Option {
    static Answer create(String optionText) {
        return new StringBasedAnswer(optionText);
    }
}
