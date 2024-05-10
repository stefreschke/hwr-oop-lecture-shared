package hwr.oop.examples.functional.programming.streams;

import java.util.Objects;
import java.util.UUID;

final class Wagon {

  private final UUID id;
  private final WagonNumber wagonNumber;
  private final long weight;
  private final long wagonBodyLength;
  private final long distanceFrontClutch;
  private final long distanceAftClutch;

  Wagon(
      UUID id,
      WagonNumber wagonNumber,
      long weight,
      long wagonBodyLength,
      long distanceFrontClutch,
      long distanceAftClutch
  ) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(wagonNumber);
    this.id = id;
    this.wagonNumber = wagonNumber;
    this.weight = weight;
    this.wagonBodyLength = wagonBodyLength;
    this.distanceFrontClutch = distanceFrontClutch;
    this.distanceAftClutch = distanceAftClutch;
  }

  public UUID id() {
    return id;
  }

  public WagonNumber wagonNumber() {
    return wagonNumber;
  }

  public long weight() {
    return weight;
  }

  public long length() {
    return wagonBodyLength + distanceFrontClutch + distanceAftClutch;
  }

}
