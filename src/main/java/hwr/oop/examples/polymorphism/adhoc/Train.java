package hwr.oop.examples.polymorphism.adhoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Train {

    private List<Wagon> wagons = new ArrayList<>();
    private final double passengerFriendlyAcceleration;
    private final double maximumAcceleration;

    public Train() {
        this(10.0);
    }

    public Train(double maximumAcceleration) {
        this(maximumAcceleration, maximumAcceleration / 2);  // constructor chaining
    }

    Train(double maximumAcceleration, double passengerFriendlyAcceleration) {
        this.maximumAcceleration = maximumAcceleration;
        this.passengerFriendlyAcceleration = passengerFriendlyAcceleration;
    }

    public int getNumberOfRailcars() {
        return wagons.size();
    }

    // Naive approach
    public void attachCar(Wagon wagon) {
        this.wagons.add(wagon);
    }

    public void attachCar(Wagon wagon0, Wagon wagon1) {
        this.wagons.add(wagon0);
        this.wagons.add(wagon1);
    }

    public void attachCar(Wagon wagon0, Wagon wagon1, Wagon wagon2) {
        this.wagons.add(wagon0);
        this.wagons.add(wagon1);
        this.wagons.add(wagon2);
    }

    // Varargs approach
    public void attachCar(Wagon... Wagons) {
        List<Wagon> WagonList = Arrays.asList(Wagons);
        this.wagons.addAll(WagonList);
    }

    // equal to varargs (under the hood)
    public void attachCarAsArray(Wagon[] Wagons) {
        List<Wagon> WagonList = Arrays.asList(Wagons);
        this.wagons.addAll(WagonList);
    }
}
