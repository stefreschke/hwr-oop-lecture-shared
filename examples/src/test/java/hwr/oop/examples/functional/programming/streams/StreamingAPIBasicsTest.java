package hwr.oop.examples.functional.programming.streams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Function Programming: Streams: Basics")
class StreamingAPIBasicsTest {

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
        .toList();
    // then
    assertThat(doubledList).containsExactly(2, 2, 8, 10, 12, 18, 18, 18);
  }

  @Test
  @DisplayName("#map, keep initials from a list of names")
  void map_KeepInitialsOfNames() {
    // given
    final List<String> listOfNames = List.of("Stefan", "Daniel", "Matthias");
    // when
    final List<String> listOfInitials = listOfNames.stream()
        .map(name -> name.substring(0, 1))
        .toList();
    // then
    assertThat(listOfInitials).containsExactly("S", "D", "M");
  }

  @Test
  @DisplayName("#map, convert each element in Stream with #toString")
  void map_ConvertToString() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 1, 4, 5, 6, 9, 9, 9);
    // when
    final List<String> stringList = listOfNumbers.stream()
        .map(Objects::toString)  // same as: i -> Objects.toString(i)
        .toList();
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
        .toList();
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
        .toList();
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
        .toList();
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
        .toList();
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

  @Test
  @DisplayName("#reduce, sum up integers in Stream (returning an integer)")
  void reduce_SumOfIntegersWithStartingValue() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final Integer sum = listOfNumbers.stream()
        .reduce(0, Integer::sum);  // add 0 as first element to Stream
    // reduce using (a, b) -> a + b
    // then
    assertThat(sum).isEqualTo(31);
  }

  @Test
  @DisplayName("#reduce, sum up integers in Stream (returning an optional)")
  void reduce_SumOfIntegersWithOptional() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final Optional<Integer> sumOptional = listOfNumbers.stream()
        .reduce(Integer::sum);  // reduce using (a, b) -> a + b, if Stream empty, Optional is empty
    // then
    assertThat(sumOptional)
        .isPresent().get()
        .isEqualTo(31);
  }

  @Test
  @DisplayName("#findAny, returns some element from Stream")
  void findAny_ReturnsAnyElementFromStream() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final Optional<Integer> optional = listOfNumbers.stream()
        .findAny();
    // then
    assertThat(optional)
        .isPresent().get()
        .matches(listOfNumbers::contains);
  }

  @Test
  @DisplayName("#findFirst, returns first element from Stream")
  void findFirst_ReturnsFirstElementFromStream() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final Optional<Integer> optional = listOfNumbers.stream()
        .findFirst();
    // then
    assertThat(optional)
        .isPresent().get()
        .isEqualTo(1);
  }

  @Test
  @DisplayName("#anyMatch, returns true if at least one element matches")
  void anyMatch_ReturnsTrueIfAtLeastOneElementFulfillsPredicate() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final boolean someElementsGreaterThan5 = listOfNumbers.stream()
        .anyMatch(i -> i > 5);
    // then
    assertThat(someElementsGreaterThan5).isTrue();
  }

  @Test
  @DisplayName("#allMatch, returns true if all elements match")
  void allMatch_ReturnsTrueIfAtLeastOneElementFulfillsPredicate() {
    // given
    final List<Integer> listOfNumbers = List.of(1, 2, 5, 6, 8, 9);
    // when
    final boolean allElementsGreaterThan5 = listOfNumbers.stream()
        .allMatch(i -> i > 5);
    // then
    assertThat(allElementsGreaterThan5).isFalse();
  }

  @Test
  @DisplayName("#flatMap, can be used to flatten nested collections")
  void flatMap_FromStreamOfLists_ToJustAStream() {
    // given
    final List<List<Integer>> nestedList = List.of(
        List.of(1, 2), List.of(3, 4), List.of(5), List.of(6)
    );
    // when
    final List<Integer> allInts = nestedList.stream()
        .flatMap(List::stream)
        .toList();
    // then
    assertThat(allInts).containsExactly(1, 2, 3, 4, 5, 6);
  }
}
