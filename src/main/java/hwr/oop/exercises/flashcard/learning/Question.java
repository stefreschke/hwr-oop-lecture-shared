package hwr.oop.exercises.flashcard.learning;

public interface Question {
    static Question create(String questionString) {
        return new StringBasedQuestion(questionString);
    }
    String toString();
}
