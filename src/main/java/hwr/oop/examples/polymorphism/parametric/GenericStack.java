package hwr.oop.examples.polymorphism.parametric;

public class GenericStack<T> {
    private Object[] store = new Object[]{};

    public boolean isEmpty() {
        return store.length < 1;
    }

    public void put(T element) {
        int length = store.length + 1;
        Object[] copy = new Object[length];
        System.arraycopy(store, 0, copy, 0, length - 1);
        copy[length - 1] = element;
        this.store = copy;
    }

    public T get() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int length = store.length - 1;
        Object last = store[length];
        Object[] copy = new Object[length];
        System.arraycopy(store, 0, copy, 0, length);
        this.store = copy;
        return (T) last;
    }
}
