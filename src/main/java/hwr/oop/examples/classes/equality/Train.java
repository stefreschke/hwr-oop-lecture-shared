package hwr.oop.examples.classes.equality;

import java.util.Objects;

class Train {
    private String id;
    private String type;
    private int numberOfCars;

    public Train(String id) {
        this.id = id;
    }

    public Train(String id, String type, int numberOfCars) {
        this.id = id;
        this.type = type;
        this.numberOfCars = numberOfCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Train train = (Train) o;
        return numberOfCars == train.numberOfCars && Objects.equals(id, train.id) &&
                Objects.equals(type, train.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, numberOfCars);
    }
}
