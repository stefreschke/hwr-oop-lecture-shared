package hwr.oop.examples.imperative.programming.structures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Foundations: Fallauswahl: switch case")
class SwitchCaseTest {

  @Test
  @DisplayName("switch statement: Define what happens on cases")
  void switchStatement_SpecificCases_NoFailOnTwoAndThree() {
    int number = 2;
    switch (number) {
      case 2, 3 -> {
        // no fail
      }
      default -> fail();
    }
  }

  @Test
  @DisplayName("switch expression: yield to return value")
  void switchExpression_Yield_DefinesSwitchReturnedValue() {
    int a = 2;
    String result = switch (a) {
      case 1 -> "A";
      case 2 -> {
        String stuff = "X";
        yield stuff + "Y";
      }
      default -> "?";
    };
    assertThat(result).isEqualTo("XY");
  }

  @Nested
  @DisplayName("Avoid: Old Switch Case Statements (pre Java 17)")
  class OldSwitchCaseTest {

    @Test
    void switchCase_SpecificCase_OnlySingleCaseExecuted() {
      int number = 2;
      switch (number) {
        case 1:
          fail();
          break;
        case 2:
          // no fail
          break;
        default:
          fail();
      }
    }

    @Test
    void switchCase_DefaultCase_OnlyDefaultExecuted() {
      int number = 3;
      switch (number) {
        case 1:
          fail();
          break;
        case 2:
          fail();
          break;
        default:
          // no fail
      }
    }

    @Test
    void switchCase_Confusing_FallThrough() {
      char grade = 'C';
      switch (grade) {
        case 'A':
          System.out.println("Excellent!");
          break;
        case 'B':
        case 'C':
          System.out.println("Well done");
          break;
        case 'D':
          System.out.println("You passed");
        case 'F':
          System.out.println("Better try again");
          break;
        default:
          System.out.println("Invalid grade");
      }
      System.out.println("Your grade is " + grade);
    }
  }
}
