package hwr.oop.examples.imperative.programming.structures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("For Loop")
class ForLoopsTest {

    @Test
    @DisplayName("fori: Just as in C")
    void forILoop_WorksJustAsInC() {
        for (int i = 1; i <= 4; i++) {
            System.out.println(i);
            assertThat(i).isBetween(1, 4);
        }
    }

    @Test
    @DisplayName("'for (A; B; C) { D }' is same as 'a; while(b) {D; C;}'")
    void while_CanReplaceForLoop_ButShouldNot() {
        // five instead of four lines
        int number = 1;
        while (number <= 4) {
            assertThat(number).isBetween(1, 4);
            number++;
        }
    }

    @Test
    void forLoop_ColonMagic() {
        int[] integers = {1, 2, 3, 4};
        for (int number : integers) {
            System.out.println(number);
            assertThat(number).isBetween(1, 4);
        }
    }

    @Test
    void forEachLoop_EquivalentToForLoop_ButOnlyOnCollections() {
        List<Integer> integers = List.of(1, 2, 3, 4);  // Collections, we'll do this later
        integers.forEach((number) -> {
            System.out.println(number);
            assertThat(number).isBetween(1, 4);
        });
    }
}
