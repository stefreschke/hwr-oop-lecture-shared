package hwr.oop.examples.imperative.programming.operations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Primitives: Equality Operators (==, !=)")
class EqualityOperatorsTest {

  @Test
  @DisplayName("1 == 2 is true")
  void equal_OneEqualTwo_IsFalse() {
    boolean eqResult = 1 == 2;
    assertThat(eqResult).isFalse();
  }

  @Test
  @DisplayName("1 != 2 is true")
  void notEqual_OneNotEqualTwo_IsFalse() {
    boolean eqResult = 1 != 2;
    assertThat(eqResult).isTrue();
  }

  @Test
  void equal_TwoObjects_IsFalse() {
    Object a = new Object();
    Object b = new Object();
    boolean eqResult = a == b;
    assertThat(eqResult).isFalse();
  }
}
