package hwr.oop.examples.imperative.programming.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Primitives: Type Conversions (Explicitly, Implicitly)")
class TypeConversionTest {

  @Test
  @DisplayName("int -> long (upcast): possible, implicitly")
  void upcast_FromIntToLong_PossibleImplicitly() {
    int intVar = 1;
    long longVar = intVar;
    assertThat(intVar)
        .isEqualTo(longVar)
        .isEqualTo(1);
  }

  @Test
  @DisplayName("long -> int (downcast): only explicitly possible")
  void downcast_FromLongToInt_PossibleOnlyExplicitly() {
    long longVar = 1L;
    int intVar = (int) longVar;
    assertThat(longVar)
        .isEqualTo(intVar)
        .isEqualTo(1L);
  }

}
