package hwr.oop.examples.functional.programming.streams;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This test shows that Stream API operations operating on DTO classes (dumb objects, that only hold
 * data) are bad, because they leak object interna and rely on the calling code to be able to make
 * sense of an object in its entirety.
 * <p>
 * Instead, it is best to send messages to the queried objects to reduce complexity of streaming API
 * operations, revealing their usefulness in productive code bases (by replacing all kinds of
 * loops).
 * <p>
 * SUT consists of dumb objects {@link TrainDto} and {@link WagonDto}, which can be converted to
 * smart objects {@link Train} and {@link Wagon} using {@link WagonDto#asWagon()} and
 * {@link TrainDto#asTrain(Collection)}.
 * <p>
 * The -Dto objects are examples for 'anemic' object-oriented code, a common mistake students make.
 * The non-Dto objects are better solutions solving the mistakes of the -Dto objects.
 */
@DisplayName("Function Programming: Streams: Advanced and Practices")
class StreamingAPIAdvancedPracticesTest {

  private List<TrainDto> trainDtos;
  private List<WagonDto> wagonDtos;

  @BeforeEach
  void setUp() {
    trainDtos = Fixture.trains();
    wagonDtos = Fixture.wagons();
  }

  @Test
  void buildFullTrains_DoesNotThrowException() {

    final var wagons = wagonDtos.stream()
        .map(WagonDto::asWagon)
        .toList();
    final var trains = trainDtos.stream()
        .map(t -> t.asTrain(wagons))
        .toList();

    assertSoftly(softly -> {
      softly.assertThat(wagons).hasSize(14);
      softly.assertThat(trains).hasSize(6);
    });
  }

  /**
   * It is bad to write big streams querying object interna.
   */
  @Test
  void bad_AllTrainsWithTrainNumberOver9000() {

    // when
    final List<String> trainNumbersOver9000 = trainDtos.stream()
        .map(TrainDto::trainNumber)                              // only use trainNumber
        .map(Long::parseLong)                                    // parse trainNumber to long
        .filter(trainNumberAsLong -> trainNumberAsLong > 9000L)  // keep numbers > 9000
        .map(Object::toString)                                   // parse longs to String
        .toList();
    final List<TrainDto> trainsWithTrainNumberOver9000 = trainDtos.stream()
        .filter(t -> trainNumbersOver9000.contains(t.trainNumber()))
        .toList();

    // then
    assertThat(trainsWithTrainNumberOver9000).hasSize(1);
  }

  /**
   * Instead, <b>reduce streams</b> by <b>sending messages</b> between objects!
   * <p>
   * In this case, ask a {@link Train} if its trainNumber is over 9000 through a message implemented
   * for exactly this purpose. The message itself will be piped through to the value object
   * {@link TrainNumber}.
   *
   * @see Train#hasTrainNumberOver9000()
   * @see TrainNumber#isOver9000()
   */
  @Test
  void good_AllTrainsWithTrainNumberOver9000_TrainsKnowTheirTrainNumber() {

    // given
    final var wagons = wagonDtos.stream()
        .map(WagonDto::asWagon)
        .toList();
    final var trains = trainDtos.stream()
        .map(t -> t.asTrain(wagons))
        .toList();

    // when
    final var trainsWithTrainNumberOver9000 = trains.stream()
        .filter(Train::hasTrainNumberOver9000)  // keep trains where #hasTrainNumberOver9000 is true
        .toList();

    // then
    assertThat(trainsWithTrainNumberOver9000).hasSize(1);
  }

  /**
   * It is bad to write big repeated actions (something like SQL joins in this case).
   */
  @Test
  void bad_SortingTrainsByLength() {

    // when
    final List<TrainDto> sortedByLength = trainDtos.stream()
        .sorted((a, b) -> {
          final Long lengthOfA = a.wagonNumbers().stream()
              .map(wagonNumber -> wagonDtos.stream()
                  .filter(wagonDto -> wagonDto.wagonNumber().equals(wagonNumber)).findFirst()
                  .orElseThrow())
              .map(wagon -> wagon.wagonBodyLength() + wagon.distanceAftClutch() + wagon.distanceFrontClutch())
              .reduce(0L, Long::sum);
          final Long lengthOfB = b.wagonNumbers().stream()  // code duplication...
              .map(wagonNumber -> wagonDtos.stream()
                  .filter(wagonDto -> wagonDto.wagonNumber().equals(wagonNumber)).findFirst()
                  .orElseThrow())
              .map(wagon -> wagon.wagonBodyLength() + wagon.distanceAftClutch() + wagon.distanceFrontClutch())
              .reduce(0L, Long::sum);
          return Long.compare(lengthOfA, lengthOfB);
        })
        .toList();

    // then
    assertThat(sortedByLength)
        .hasSize(6)
        .matches(trains -> trains.getFirst().trainNumber().equals("9000"), "9000 is shortest")
        .matches(trains -> trains.getLast().trainNumber().equals("0118"), "0118 is longest");
  }

  /**
   * Instead, <b>reduce streams</b> by <b>sending messages</b> between objects!
   * <p>
   * Trains know their length (send a message to a train which returns a long). Comparator of longs
   * can be created using method references using the standard library.
   *
   * @see Train#length()
   * @see Comparator#comparingLong(ToLongFunction)
   */
  @Test
  void good_SortingTrainsByLength_TrainsBestKnowHowLongTheyAre() {

    // given
    final var wagons = wagonDtos.stream()
        .map(WagonDto::asWagon)
        .toList();
    final var convertedTrains = trainDtos.stream()
        .map(t -> t.asTrain(wagons))
        .toList();

    // when
    final List<Train> sortedByLength = convertedTrains.stream()
        .sorted(Comparator.comparingLong(Train::length))  // sort by answer to sent #length message
        .toList();

    // then
    assertThat(sortedByLength)
        .hasSize(6)
        .matches(trains -> trains.getFirst().trainNumber().value().equals("9000"),
            "9000 is shortest")
        .matches(trains -> trains.getLast().trainNumber().value().equals("0118"),
            "0118 is longest");
  }

  /**
   * It is bad to write big streams querying object interna.
   */
  @Test
  void bad_LengthOfAllTrainsCombined() {

    // when
    final Long totalLengthOfAllTrains = trainDtos.stream()
        .map(TrainDto::wagonNumbers)  // keep wagon numbers (list); we now have Stream<List<String>>
        .flatMap(Collection::stream)  // we combine all Lists in Stream; we now have Stream<String>
        .map(wagonNumber -> wagonDtos.stream()
            .filter(dto -> dto.wagonNumber().equals(wagonNumber))
            .findFirst().orElseThrow())   // replace wagon numbers with matching WagonDtos
        .map(wagonDto -> wagonDto.wagonBodyLength() + wagonDto.distanceAftClutch()
            + wagonDto.distanceFrontClutch())   // keep their lengths (combined with clutch distances)
        .reduce(0L, Long::sum);  // sum up lengths

    // then
    assertThat(totalLengthOfAllTrains).isEqualTo(23066);
  }

  /**
   * Instead, rely on the already implemented message asking a train for its length.
   *
   * @see Train#length()
   */
  @Test
  void good_lengthOfAllTrains_TrainsKnowTheirLength() {

    // given
    final var wagons = wagonDtos.stream()
        .map(WagonDto::asWagon)
        .toList();
    final var trains = trainDtos.stream()
        .map(t -> t.asTrain(wagons))
        .toList();

    // when
    final Long totalLengthOfAllTrains = trains.stream()
        .map(Train::length)      // only keep length of trains
        .reduce(0L, Long::sum);  // sum them up

    // then
    assertThat(totalLengthOfAllTrains).isEqualTo(23066);
  }

  /**
   * Do not query data structures to use result as value/key in {@link Map} in calling code.
   */
  @Test
  void bad_totalWeightOfTrain() {

    final Map<String, Long> trainNumberWeightMap = trainDtos.stream()
        .collect(Collectors.toMap(
            TrainDto::trainNumber,  // becomes the Key of the map
            trainDto -> wagonDtos.stream()
                .filter(waggonDto -> trainDto.wagonNumbers().contains(waggonDto.wagonNumber()))
                .map(WagonDto::weight)
                .reduce(0L, Long::sum)  // becomes Value of the map
        ));

    assertThat(trainNumberWeightMap)
        .hasSize(6)
        .containsKey("9000").matches(m -> m.get("9000").equals(0L))
        .containsKey("42").matches(m -> m.get("42").equals(13000L));
  }

  @Test
  void good_totalWeightOfTrain_AskingATrainItsWeight() {

    // given
    final var wagons = wagonDtos.stream()
        .map(WagonDto::asWagon)
        .toList();
    final var trains = trainDtos.stream()
        .map(t -> t.asTrain(wagons))
        .toList();

    // when
    final Map<TrainNumber, Long> trainNumberWeightMap = trains.stream()
        .collect(Collectors.toMap(
            Train::trainNumber,  // trainNumber as Key
            Train::weight        // weight as Value
        ));

    // then
    final var first = TrainNumber.of("9000");
    final var second = TrainNumber.of("42");
    assertThat(trainNumberWeightMap)
        .hasSize(6)
        .containsKey(first).matches(m -> m.get(first).equals(0L))
        .containsKey(second).matches(m -> m.get(second).equals(13000L));
  }

}