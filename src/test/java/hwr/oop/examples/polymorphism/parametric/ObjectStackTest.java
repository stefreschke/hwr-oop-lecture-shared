package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@DisplayName("Object based Stack, without Parameterization on Class")
class ObjectStackTest {
    @Test
    void isEmpty_EmptyStack_StackIsEmpty() {
        ObjectStack stack = new ObjectStack();
        boolean isEmpty = stack.isEmpty();
        assertThat(isEmpty).isTrue();
    }

    @Test
    void isEmpty_PutOneElement_StackIsNotEmpty() {
        // given
        ObjectStack stack = new ObjectStack();
        stack.put(new Train());
        // when
        boolean isEmpty = stack.isEmpty();
        // then
        assertThat(isEmpty).isFalse();
    }

    @Test
    void get_PutTrainAndWagon_getsEachObjectButKeepsType() {
        // given
        ObjectStack stack = new ObjectStack();
        stack.put(new Train());
        stack.put(new Wagon());
        // when
        Object wagon = stack.get();
        Object train = stack.get();
        // then
        assertThat(wagon).isExactlyInstanceOf(Wagon.class);
        assertThat(train).isExactlyInstanceOf(Train.class);
    }

    @Test
    void get_TrainStack_PutOneTrain_getsObjectButIsOFTypeTrain() {
        // given
        ObjectStack stack = new ObjectStack(Train.class);
        stack.put(new Train());
        // when
        Object object = stack.get();
        // then
        assertThat(object)
                .isNotNull()
                .isInstanceOf(Object.class)
                .isExactlyInstanceOf(Train.class);
    }

    @Test
    void get_TrainStack_PutTrainAndWagon_throwsExceptionWhenPutWagon() {
        // given
        ObjectStack stack = new ObjectStack(Train.class);
        stack.put(new Train());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        // when
                        stack.put(new Wagon())
                );
    }

}
