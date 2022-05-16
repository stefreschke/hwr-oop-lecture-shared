package hwr.oop.examples.polymorphism.adhoc;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WrappedTrainTest {

    @Test
    void attachCar_Add1SingleWaggon_TrainHas1RailCars() {
        Train train = new Train();
        train.attachCar(new Wagon());
        int numberOfRailCars = train.getNumberOfRailcars();
        assertThat(numberOfRailCars).isEqualTo(1);
    }

    @Test
    void attachCar_Add2SingleWaggons_TrainHas2RailCars() {
        Train train = new Train();
        train.attachCar(new Wagon(), new Wagon());
        int numberOfRailCars = train.getNumberOfRailcars();
        assertThat(numberOfRailCars).isEqualTo(2);
    }

    @Test
    void attachCar_Add3SingleWaggons_TrainHas3RailCars() {
        Train train = new Train();
        train.attachCar(new Wagon(), new Wagon(), new Wagon());
        int numberOfRailCars = train.getNumberOfRailcars();
        assertThat(numberOfRailCars).isEqualTo(3);
    }

    @Test
    void attachCar_Add4SingleWaggons_TrainHas4RailCars() {
        Train train = new Train();
        train.attachCar(new Wagon(), new Wagon(), new Wagon(), new Wagon());
        int numberOfRailCars = train.getNumberOfRailcars();
        assertThat(numberOfRailCars).isEqualTo(4);
    }

    @Test
    void attachCarAsArray_Add4SingleWaggons_TrainHas4RailCars() {
        Train train = new Train();
        Wagon[] wagons = Arrays.array(new Wagon(), new Wagon(), new Wagon(), new Wagon());
        train.attachCarAsArray(wagons);
        int numberOfRailCars = train.getNumberOfRailcars();
        assertThat(numberOfRailCars).isEqualTo(4);
    }

}
