package hwr.oop.examples;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloNameTest {

    @Test
    void helloName_canGreetMatthias() {
        HelloName helloName = new HelloName("Matthias");
        String name = helloName.getOutputString();
        Assertions.assertThat(name).isEqualTo("Matthias");
    }

    @Test
    void helloName_canGreetNiklas() {
        HelloName helloName = new HelloName("Niklas");
        String name = helloName.getOutputString();
        Assertions.assertThat(name).isEqualTo("Niklas");
    }
}
