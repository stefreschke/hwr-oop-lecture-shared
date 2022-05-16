package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullabilityTest {

    @Test
    void nullCheck_differentOrders_NullCheckIsOrderDependent() {
        String aNullString = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            if(aNullString != null && !aNullString.isEmpty()) {
                System.out.println("First null check" + aNullString);
            }
            if(!aNullString.isEmpty() && aNullString != null) {
                System.out.println("First isEmpty" + aNullString);
            }
            if(!aNullString.isEmpty()) {
                System.out.println("Only isEmpty" + aNullString);
            }
        });
    }
}
