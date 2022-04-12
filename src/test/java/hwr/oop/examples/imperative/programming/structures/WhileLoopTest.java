package hwr.oop.examples.imperative.programming.structures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@DisplayName("While Loop")
class WhileLoopTest {

    @Test
    @DisplayName("Java supports classic while-loops")
    void while_Condition_ExecutesUntilConditionIsFalse() {
        int count = 0;
        while (count < 5) {
            count++;
            System.out.println(count);
        }
        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("break exits while-loop")
    void break_encountered_loopExited() {
        int count = 0;
        while (true) {
            count++;
            System.out.println(count);
            if (count == 5) {
                break;
            }
        }
        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("continue used to jump to next cycle")
    void continue_Encountered_LoopContinues() {
        int count = 0;
        while (count < 5) {
            count++;
            boolean countEven = count % 2 == 0;
            if (countEven) {
                continue;
            }
            if (countEven) {
                fail("should not be reachable");
            }
        }
    }

    @Test
    @DisplayName("Instead of 'while (true)' + break, use 'do {} while ()'")
    void doWhile_canReplaceWhileTrueWithBreak() {
        int count = 0;
        do {
            count++;
            System.out.println(count);
        } while (count != 5);

        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("Don't use 'for(;' instead of 'while (true)'")
    void for_canReplaceWhile_butThatIsUgly() {
        int count = 0;
        for (; count != 5; count++) {
            // just think of for (;;)! o.O
        }
        assertThat(count).isEqualTo(5);
    }
}
