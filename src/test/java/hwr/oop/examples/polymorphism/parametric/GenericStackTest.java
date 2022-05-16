package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@DisplayName("Cool Stack, with Parameterization on Class")
class GenericStackTest {
    @Test
    void isEmpty_NewStack_IsEmpty() {
        GenericStack<Train> stack = new GenericStack<>();
        boolean isEmpty = stack.isEmpty();
        assertThat(isEmpty).isTrue();
    }

    @Test
    void isEmpty_PutOneTrain_StackNotEmpty() {
        GenericStack<Train> stackOfTrains = new GenericStack<>();
        stackOfTrains.put(new Train());
        // stackOfTrains.put(new Waggon());  // not allowed by compiler!
        boolean isEmpty = stackOfTrains.isEmpty();
        assertThat(isEmpty).isFalse();
    }

    @Test
    void get_PutTwiceGetOnce_LastTrainPutIsRetrievedFirst() {
        // given
        GenericStack<Train> stackOfTrains = new GenericStack<>();
        Train firstTrain = new Train();
        Train secondTrain = new Train();
        stackOfTrains.put(firstTrain);
        stackOfTrains.put(secondTrain);
        // when
        Train retrievedTrain = stackOfTrains.get();
        // then
        assertThat(retrievedTrain)
                .isSameAs(secondTrain)
                .isNotSameAs(firstTrain);
    }

    @Test
    void get_EmptyStack_ThrowsException() {
        // given
        GenericStack<Train> stackOfTrains = new GenericStack<>();
        try {
            // when
            Train someTrain = stackOfTrains.get();
            // then
            fail("Stack empty, but still got: " + someTrain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
