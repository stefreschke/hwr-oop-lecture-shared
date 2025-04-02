package hwr.oop.examples.classes.comparability;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Comparability: Comparators and Comparable")
class ComparabilityTest {

  private Train first;
  private Train second;

  @BeforeEach
  void setUp() {
    first = new Train(12);
    second = new Train(13);
  }

  @Test
  void compareTo_FirstDrivesFasterThanSecond_FirstIsGreaterThanSecond() {
    first.accelerateTo(100);
    second.accelerateTo(90);
    int compareToResult = first.compareTo(second);
    assertThat(compareToResult).isPositive().isNotZero();
  }

  @Test
  void compareTo_FirstDrivesFasterThanSecond_SecondIsLessThanFirst() {
    first.accelerateTo(100);
    second.accelerateTo(90);
    int compareToResult = second.compareTo(first);
    assertThat(compareToResult).isNegative().isNotZero();
  }

  @Test
  void railCarComparator_compare_FirstTrainShorter_FirstLessThanSecond() {
    Comparator<Train> comparator = new RailCarComparator();
    int result = comparator.compare(first, second);
    assertThat(result).isNegative().isNotZero();
  }

  @Test
  void railCarComparator_compare_FirstTrainShorter_SecondTrainGreaterThanFirst() {
    Comparator<Train> comparator = new RailCarComparator();
    int result = comparator.compare(second, first);
    assertThat(result).isPositive().isNotZero();
  }
}
