package hwr.oop.examples.functional.programming.streams;

import java.util.UUID;

record WagonDto(
    String id,
    String wagonNumber,
    long weight,
    long wagonBodyLength,
    long distanceFrontClutch,
    long distanceAftClutch
) {

  public Wagon asWagon() {
    return new Wagon(
        UUID.fromString(id),
        WagonNumber.of(wagonNumber),
        weight,
        wagonBodyLength,
        distanceFrontClutch,
        distanceAftClutch
    );
  }

}
