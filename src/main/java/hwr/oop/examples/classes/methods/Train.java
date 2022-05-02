package hwr.oop.examples.classes.methods;

class Train {
    private final int numberOfRailCars;
    private int speed;

    public Train(int numberOfRailCars) {
        this.numberOfRailCars = numberOfRailCars;
    }

    public void accelerateTo(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Train{" +
                "numberOfRailCars=" + numberOfRailCars +
                ", speed=" + speed +
                '}';
    }
}
