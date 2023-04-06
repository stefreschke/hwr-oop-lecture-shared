package hwr.oop.examples.imperative.programming.structures;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoopsExampleTest {

    @Test
    void forEachLoop() {
        final List<Integer> numbers = List.of(1, 2, 3, 4);
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }

    @Test
    void streamAPILoop() {
        final List<Integer> numbers = List.of(1, 2, 3, 4);
        numbers.forEach(number -> System.out.println(number));
    }

    @Test
    void iteratorLoop() {
        final List<Integer> numbers = List.of(1, 2, 3, 4);
        final Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            final Integer number = iterator.next();
            System.out.println(number);
        }
    }

    @Test
    void comparingJunitAndAssertjAssertions() {
        int speed = 420;

        // assertj
        assertThat(speed)
                .isEqualTo(420)
                .isGreaterThan(42)
                .isLessThan(1337);

        // junit 5
        assertEquals(420, speed);
        assertTrue(speed < 1337);
        assertTrue(speed > 42);
    }
}
