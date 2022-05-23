package hwr.oop.exercises.flashcard.learning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class FancyFlashCard implements FlashCard {
    private final Question question;
    private final Collection<Answer> answers;
    private final Solution solution;

    public FancyFlashCard(Question question, Collection<Answer> answers, Solution solution) {
        assertValidFlashCard(question, solution, answers);
        this.question = question;
        this.answers = answers;
        this.solution = solution;
    }

    public FancyFlashCard(Question question, Solution solution) {
        this(question, null, solution);
    }

    @Override
    public Solution getSolution() {
        return solution;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public Optional<Collection<Answer>> getOptions() {
        if (answers == null) {
            return Optional.empty();
        }
        List<Answer> copy = new ArrayList<>(answers);
        Collections.shuffle(copy);
        return Optional.of(copy);
    }

    @Override
    public boolean isCorrectAnswer(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("null is never the right answer!");
        }
        return solution.equals(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FancyFlashCard that = (FancyFlashCard) o;
        return question.equals(that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }

    private void assertValidFlashCard(Question question, Solution solution,
                                      Collection<Answer> answers) {
        if (question == null || solution == null) {
            throw new IllegalArgumentException(
                    "Cannot create FlashCard without Question or Answer");
        }
        if (answers == null || answers.contains(solution)) {
            throw new IllegalArgumentException(
                    "Answers, if provided, need to contain the solution");
        }
    }
}
