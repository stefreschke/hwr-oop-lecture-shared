package hwr.oop.examples.functional.programming.streams;

import java.util.List;
import java.util.UUID;

final class Train {

  private final UUID id;
  private final TrainNumber trainNumber;
  private final List<Wagon> wagons;

  Train(
      UUID id,
      TrainNumber trainNumber,
      List<Wagon> wagons
  ) {
    this.id = id;
    this.trainNumber = trainNumber;
    this.wagons = wagons;
  }

  public UUID id() {
    return id;
  }

  public TrainNumber trainNumber() {
    return trainNumber;
  }

  public long length() {
    return wagons.stream()
        .map(Wagon::length)      // only use length of wagons
        .reduce(0L, Long::sum);  // sum up lengths
  }

  public boolean hasTrainNumberOver9000() {
    return trainNumber.isOver9000();
  }

  public long weight() {
    return wagons.stream()
        .map(Wagon::weight)  // only keep wagon's weight
        .reduce(0L, Long::sum);  // sum up weights
  }
}
