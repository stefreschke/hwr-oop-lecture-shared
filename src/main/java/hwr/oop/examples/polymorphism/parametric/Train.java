package hwr.oop.examples.polymorphism.parametric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

class Train implements Comparable<Train> {
    private final Collection<Wagon> wagons;
    private final String id;

    public Train() {
        this("some train");
    }

    public Train(String id) {
        this.wagons = new ArrayList<>();
        this.id = id;
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
        return Objects.equals(wagons, train.wagons) &&
                Objects.equals(id, train.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wagons, id);
    }

    @Override
    public int compareTo(Train o) {
        return this.id.compareTo(o.id);
    }
}
