package hwr.oop.examples.imperative.programming.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class SwitchCaseTest {
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
    void fallThrough() {
        char grade = 'C';
        switch(grade) {
            case 'A' :
                System.out.println("Excellent!");
                break;
            case 'B' :
            case 'C' :
                System.out.println("Well done");
                break;
            case 'D' :
                System.out.println("You passed");
            case 'F' :
                System.out.println("Better try again");
                break;
            default :
                System.out.println("Invalid grade");
        }
        System.out.println("Your grade is " + grade);
    }
}
