package hwr.oop.examples;

public class HelloWorld {
    String name;
    public HelloWorld(String name) {
        this.name = name;
    }

    public String getOutputString() {
        return "Hello " + name;
    }
}
