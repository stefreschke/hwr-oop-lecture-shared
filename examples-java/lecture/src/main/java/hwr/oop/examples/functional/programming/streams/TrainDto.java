package hwr.oop.examples.functional.programming.streams;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

record TrainDto(
    String id,
    String trainNumber,
    List<String> wagonNumbers
) {
  public Train asTrain(Collection<Wagon> wagons) {
    return new Train(
        UUID.fromString(id),
        TrainNumber.of(trainNumber),
        pickWagonsByWagonNumberFrom(wagons)
    );
  }

  private List<Wagon> pickWagonsByWagonNumberFrom(Collection<Wagon> wagons) {
    return wagonNumbers.stream()
        .map(wagonNumberString -> findWagonByNumberString(wagons, wagonNumberString))
        .toList();
  }

  private Wagon findWagonByNumberString(Collection<Wagon> collection, String wagonNumberString) {
    return collection.stream()
        .filter(wagon -> wagon.wagonNumber().value().equals(wagonNumberString))  // filter with equals
        .findFirst()     // pick first from stream
        .orElseThrow();  // resolve optional
  }
}
