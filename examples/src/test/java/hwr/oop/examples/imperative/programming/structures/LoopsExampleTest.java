package hwr.oop.examples.imperative.programming.structures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Foundations: Loops")
class LoopsExampleTest {

  @Test
  void forEachLoop() {
    final List<Integer> numbers = List.of(1, 2, 3, 4);
    final List<Integer> iterated = new ArrayList<>();
    for (Integer number : numbers) {
      iterated.add(number);
    }
    assertThat(iterated)
        .containsExactlyElementsOf(numbers)
        .containsSequence(numbers);
  }

  @Test
  void streamAPILoop() {
    final List<Integer> numbers = List.of(1, 2, 3, 4);
    final List<Integer> iterated = new ArrayList<>();
    numbers.forEach(number -> iterated.add(number));
    assertThat(iterated)
        .containsExactlyElementsOf(numbers)
        .containsSequence(numbers);
  }

  @Test
  void iteratorLoop() {
    final List<Integer> numbers = List.of(1, 2, 3, 4);
    final List<Integer> iterated = new ArrayList<>();
    final Iterator<Integer> iterator = numbers.iterator();
    while (iterator.hasNext()) {
      final Integer number = iterator.next();
      iterated.add(number);
    }
    assertThat(iterated)
        .containsExactlyElementsOf(numbers)
        .containsSequence(numbers);
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
