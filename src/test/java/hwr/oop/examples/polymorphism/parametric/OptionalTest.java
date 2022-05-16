package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class OptionalTest {

    @Test
    void isEmpty_ProvidedNull_OptionalIsEmpty() {
        Optional<Train> optional = Optional.ofNullable(null);
        boolean isEmpty = optional.isEmpty();
        assertThat(isEmpty).isTrue();
        assertThat(optional).isEmpty();  // assertj supports Optional
    }

    @Test
    void isEmpty_NonNullTrainProvided_OptionalIsNotEmpty() {
        Train train = new Train();
        Optional<Train> optional = Optional.ofNullable(train);
        boolean isEmpty = optional.isEmpty();
        assertThat(isEmpty).isFalse();
        assertThat(optional).isNotEmpty();  // assertj supports Optional
    }

    @Test
    void isPresent_ProvidedNull_OptionalIsNotPresent() {
        Optional<Train> optional = Optional.ofNullable(null);
        boolean isPresent = optional.isPresent();
        assertThat(isPresent).isFalse();
        assertThat(optional).isNotPresent();  // assertj supports Optional
    }

    @Test
    void isPresent_NonNullTrainProvided_OptionalIsPresent() {
        Train train = new Train();
        Optional<Train> optional = Optional.ofNullable(train);
        boolean isPresent = optional.isPresent();
        assertThat(isPresent).isTrue();
        assertThat(optional).isPresent();  // assertj supports Optional
    }

    @Test
    void get_NonNullTrainProvided_ProvidesTrainAgain() {
        Train train = new Train();
        Optional<Train> optional = Optional.ofNullable(train);
        Train retrieved = optional.get();
        assertThat(retrieved).isSameAs(train);
    }

    @Test
    void get_NullProvided_ThrowsException() {
        Optional<Train> optional = Optional.ofNullable(null);
        try {
            Train something = optional.get();
            fail("should not be able to retrieve " + something);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    void orElseThrow_NonNullTrainProvided_ProvidesTrainAgain() {
        Train train = new Train();
        Optional<Train> optional = Optional.ofNullable(train);
        Train retrieved = optional.orElseThrow();
        assertThat(retrieved).isSameAs(train);
    }

    @Test
    void orElseThrow_NullProvided_ThrowsException() {
        Optional<Train> optional = Optional.ofNullable(null);
        try {
            Train something = optional.orElseThrow();
            fail("should not be able to retrieve " + something);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    void orElseThrow_NullProvidedExpectIllegalStateIfEmpty_ThrowsExpectedException() {
        Optional<Train> optional = Optional.ofNullable(null);
        try {
            Train something = optional.orElseThrow(IllegalStateException::new);
            fail("should not be able to retrieve " + something);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
