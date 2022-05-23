package hwr.oop.exercises.flashcard.learning;

import java.util.Collection;
import java.util.Optional;

public interface FlashCard {
    static FlashCard create(Question question, Collection<Answer> answers, Solution solution) {
        return new FancyFlashCard(question, answers, solution);
    }

    Solution getSolution();

    Question getQuestion();

    Optional<Collection<Answer>> getOptions();

    boolean isCorrectAnswer(Answer answer);

}
