package hwr.oop.examples;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    @Test
    void helloWorld_givesUsAHelloWorldString() {
        HelloWorld helloWorld = new HelloWorld("World");
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello World");
    }

    @Test
    void helloWorld_givesAProvidedName() {
        HelloWorld helloWorld = new HelloWorld("Tom");
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello Tom");
    }
}