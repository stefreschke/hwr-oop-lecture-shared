package hwr.oop.exercises.flashcard.learning;

public interface Solution extends Option {
    static Solution create(String solutionText) {
        return new StringBasedSolution(solutionText);
    }
}
