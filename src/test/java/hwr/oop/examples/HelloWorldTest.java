package hwr.oop.examples;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    @Test
    void helloWorld() {
        System.out.println("Hello World");
    }

    @Test
    void helloWorld_givesUsAHelloWorldString() {
        HelloWorld helloWorld = new HelloWorld();
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello World");
    }

}
