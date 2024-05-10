package hwr.oop.examples.imperative.programming.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Primitives: Autoboxing (e.g. between int and Integer)")
class AutoboxingTest {

  @Test
  void autoboxing_ImplicitUpCastingFive_BoxedAndUnboxedEqual() {
    int unboxed = 5;
    Integer boxed = unboxed;
    assertThat(unboxed).isEqualTo(boxed);
  }

  @Test
  void autoboxing_ImplicitDownCastingFive_BoxedAndUnboxedEqual() {
    Integer boxed = Integer.valueOf(5);
    int unboxed = boxed;
    assertThat(boxed).isEqualTo(unboxed);
  }

  @Test
  void wrapperTypeCast_ExplicitUpCastingFive_BoxedAndUnboxedEqual() {
    int unboxed = 5;
    Integer boxed = (Integer) unboxed;
    assertThat(unboxed).isEqualTo(boxed);
  }

  @Test
  void wrapperTypeCast_ExplicitDownCastingFive_BoxedAndUnboxedEqual() {
    Integer boxed = Integer.valueOf(5);
    int unboxed = (int) boxed;
    assertThat(boxed).isEqualTo(unboxed);
  }
}
