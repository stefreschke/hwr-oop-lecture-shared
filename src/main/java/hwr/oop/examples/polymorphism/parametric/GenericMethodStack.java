package hwr.oop.examples.polymorphism.parametric;

public class GenericMethodStack extends ObjectStack {

    public <T> T getElement() {
        Object o = get();
        // warning: unchecked cast is evil as it might fail at runtime!
        return (T) o;
    }

    // does not add any type safety, T might be different to T of getElement()
    public <T> void putElement(T element) {
        put(element);
    }
}
