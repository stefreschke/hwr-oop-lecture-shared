package hwr.oop.examples.functional.programming.streams;

import java.util.List;
import java.util.UUID;

/**
 * Used to create some example data for tests in this package.
 */
final class Fixture {

  static List<WagonDto> wagons() {
    return List.of(
        new WagonDto(UUID.randomUUID().toString(), "421337", 17_000, 2_000, 15, 20),
        new WagonDto(UUID.randomUUID().toString(), "421338", 18_000, 2_000, 15, 20),
        new WagonDto(UUID.randomUUID().toString(), "421339", 19_000, 2_000, 15, 20),
        new WagonDto(UUID.randomUUID().toString(), "133742", 6_000, 1_500, 20, 20),
        new WagonDto(UUID.randomUUID().toString(), "133743", 7_000, 1_500, 20, 20),
        new WagonDto(UUID.randomUUID().toString(), "999881", 12_000, 3_000, 20, 20),
        new WagonDto(UUID.randomUUID().toString(), "999119", 12_000, 3_000, 20, 20),
        new WagonDto(UUID.randomUUID().toString(), "7253", 12_000, 3_000, 20, 20),
        new WagonDto(UUID.randomUUID().toString(), "204", 7_000, 500, 12, 12),
        new WagonDto(UUID.randomUUID().toString(), "500", 8_000, 500, 12, 12),
        new WagonDto(UUID.randomUUID().toString(), "501", 6_500, 500, 12, 12),
        new WagonDto(UUID.randomUUID().toString(), "17000", 12_000, 1_000, 42, 21),
        new WagonDto(UUID.randomUUID().toString(), "21000", 13_000, 1_000, 21, 42),
        new WagonDto(UUID.randomUUID().toString(), "18000", 14_000, 1_000, 42, 21)
    );
  }

  static List<TrainDto> trains() {
    return List.of(
        new TrainDto(UUID.randomUUID().toString(), "1337", List.of("421337", "421338", "421339")),
        new TrainDto(UUID.randomUUID().toString(), "42", List.of("133742", "133743")),
        new TrainDto(UUID.randomUUID().toString(), "0118", List.of("999881", "999119", "7253")),
        new TrainDto(UUID.randomUUID().toString(), "404", List.of("204", "500", "501")),
        new TrainDto(UUID.randomUUID().toString(), "9000", List.of()),
        new TrainDto(UUID.randomUUID().toString(), "9001", List.of("17000", "21000", "18000"))
    );
  }

}
