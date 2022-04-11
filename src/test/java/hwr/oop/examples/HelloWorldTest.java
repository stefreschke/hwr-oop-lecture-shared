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
        HelloWorld helloWorld = new HelloWorld("World");
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello World");
    }

    @Test
    void helloWorld_givesUsAProvidedName_toGreetLuca() {
        HelloWorld helloWorld = new HelloWorld("Luca");
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello Luca");
    }

    @Test
    void helloWorld_givesUsAProvidedName_toGreetNico() {
        HelloWorld helloWorld = new HelloWorld("Nico");
        String output = helloWorld.getOutputString();
        Assertions.assertThat(output).isEqualTo("Hello Nico");
    }
}
