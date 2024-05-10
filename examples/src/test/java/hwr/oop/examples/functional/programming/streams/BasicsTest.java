package hwr.oop.examples.functional.programming.streams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Function Programming: Streams: Basics")
class BasicsTest {

  @Test
  @DisplayName("#stream, #toList: Used to convert Collections to Streams and reversed")
  void stream_ToList_UsedToCreateStreamFromCollectionAndReversed() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final Stream<Integer> streamOfNumbers = listOfNumbers.stream();  // create Stream from List
    final List<Integer> collectedList = streamOfNumbers.toList();    // Stream #toList
    // then
    assertThat(collectedList).containsAll(listOfNumbers);
    assertThatThrownBy(streamOfNumbers::toList)  // we can only collect a Stream once!
        .hasMessageContainingAll("stream", "already", "operated upon");
  }

  @Test
  @DisplayName("#map, multiply each element in Stream with 2")
  void map_MultiplyBy2() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<Integer> doubledList = listOfNumbers.stream()
        .map(i -> i * 2)  // transform by multiplying with 2
        .toList();        // create List from Stream
    // then
    assertThat(doubledList).containsExactly(2, 2, 8, 10, 12, 18, 18, 18);
  }

  @Test
  @DisplayName("#map, convert each element in Stream with #toString")
  void map_ConvertToString() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<String> stringList = listOfNumbers.stream()
        .map(Objects::toString)  // same as: i -> Objects.toString(i)
        .toList();               // create List from Stream
    // then
    assertThat(stringList).containsExactly("1", "1", "4", "5", "6", "9", "9", "9");
  }

  @Test
  @DisplayName("#filter, only keep numbers greater than 4")
  void filter_OnlyKeepNumbersGreaterThan4() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<Integer> filteredList = listOfNumbers.stream()
        .filter(i -> i > 4)  // only keep numbers > 4
        .toList();           // create List from Stream
    // then
    assertThat(filteredList).containsExactly(5, 6, 9, 9, 9);
  }

  @Test
  @DisplayName("#filter, combined with #map, halved numbers who are at least 3")
  void filterAndMap_HalvedNumbersAtLeastThree() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<Integer> halvedAndFiltered = listOfNumbers.stream()
        .map(i -> i / 2)      // transform using integer division (1 / 2 = 0)
        .filter(i -> i >= 3)  // only keep numbers >= 3
        .toList();            // create List from Stream
    // then
    assertThat(halvedAndFiltered).containsExactly(3, 4, 4, 4);
  }

  @Test
  @DisplayName("#distinct, removes duplicates from Stream")
  void distinct_UsedToRemoveDuplicates() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<Integer> deduplicated = listOfNumbers.stream()
        .distinct()  // only keep unique values
        .toList();   // create List from Stream
    // then
    assertThat(deduplicated)
        .hasSize(5)
        .containsExactly(1, 4, 5, 6, 9);
  }

  @Test
  @DisplayName("#sorted, sorts stream by provided Comparator")
  void sorted_SortsStreamIfProvidedSomeComparator() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    final Comparator<Integer> comparator = (a, b) -> Integer.compare(b, a);  // lambda as comparator
    // when
    final List<Integer> sorted = listOfNumbers.stream()
        .sorted(comparator)  // sort using comparator
        .toList();           // create List from Stream
    // then
    assertThat(sorted)
        .containsExactly(9, 9, 9, 6, 5, 4, 1, 1);
  }

  @Test
  @DisplayName(".collect(Collectors.toMap()), can be used to create Maps from Streams")
  void collect_CanBeUsedToCreateMoreComplexDataStructures() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final Map<Integer, String> countMap = listOfNumbers.stream()
        .collect(Collectors.toMap(
            Function.identity(),  // key provider, same as: i -> i
            Objects::toString     // value provider, same as: i -> Objects.toString(i)
        ));
    // then
    assertThat(countMap)
        .containsKeys(1, 2, 5, 6, 8, 9)
        .containsValues("1", "2", "5", "6", "8", "9");
  }
}
