package hwr.oop.examples.polymorphism.parametric;

public class ObjectStack {
    private final Class allowedClass;
    private Object[] store = new Object[]{};

    public ObjectStack(Class allowedClass) {
        this.allowedClass = allowedClass;
    }

    public ObjectStack() {
        this(Object.class);
    }

    public boolean isEmpty() {
        return store.length < 1;
    }

    public void put(Object element) {
        if (!(allowedClass.isAssignableFrom(element.getClass()))) {
            throw new IllegalArgumentException("Invalid object");
        }
        int length = store.length + 1;
        Object[] copy = new Object[length];
        System.arraycopy(store, 0, copy, 0, length - 1);
        copy[length - 1] = element;
        this.store = copy;
    }

    public Object get() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int length = store.length - 1;
        Object last = store[length];
        Object[] copy = new Object[length];
        System.arraycopy(store, 0, copy, 0, length);
        this.store = copy;
        return last;
    }

}
