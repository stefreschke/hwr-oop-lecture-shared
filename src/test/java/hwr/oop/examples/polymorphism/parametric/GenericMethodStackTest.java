package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@DisplayName("Semi-Cool Stack, with Parameterization on Method while hiding cast")
class GenericMethodStackTest {

    @Test
    void get_PutTrainAndWaggon_ProvidesTypes() {
        // given
        GenericMethodStack stack = new GenericMethodStack();
        stack.putElement(new Train());
        stack.putElement(new Wagon());
        // when
        Wagon wagon = stack.getElement();
        Train train = stack.getElement();
        // then
        assertThat(wagon).isNotNull().isInstanceOf(Wagon.class);
        assertThat(train).isNotNull().isInstanceOf(Train.class);
    }

    @Test
    void get_PutTrainAndGetWaggon_throwsClassCastException() {
        // given
        GenericMethodStack stack = new GenericMethodStack();
        stack.putElement(new Train());
        // when
        assertThatExceptionOfType(ClassCastException.class)
                .isThrownBy(() -> { Wagon wagon = stack.getElement(); });
    }

    @Test
    void get_PutTrainAndWaggon_FailsIfWrongOrder() {
        // given
        GenericMethodStack stack = new GenericMethodStack();
        stack.putElement(new Train());
        stack.putElement(new Wagon());
        try {
            // when
            Train train = stack.getElement();
            // then
            fail("lifo requires first get to be waggon, but was " + train);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
