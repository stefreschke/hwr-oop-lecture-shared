package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TrainComparabilityTest : AnnotationSpec() {

  @Test
  fun `train accelerates to 100, other to 200, first compares lesser`() {
    // given
    val first = Train(numberOfWagons = 1)
    val second = Train(numberOfWagons = 1)
    // when
    first.accelerateTo(100)
    second.accelerateTo(200)
    val compareToResult = first.compareTo(second)
    // then
    assertThat(compareToResult).isNegative().isNotZero()
  }

  @Test
  fun `train accelerates to 200, other to 100, first compares bigger`() {
    // given
    val first = Train(numberOfWagons = 1)
    val second = Train(numberOfWagons = 1)
    // when
    first.accelerateTo(200)
    second.accelerateTo(100)
    val compareToResult = first.compareTo(second)
    // then
    assertThat(compareToResult).isPositive().isNotZero()
  }

  @Test
  fun `2 trains, same speed, compare equal`() {
    // given
    val first = Train(numberOfWagons = 1)
    val second = Train(numberOfWagons = 1)
    // when
    first.accelerateTo(420)
    second.accelerateTo(420)
    val compareToResult = first.compareTo(second)
    // then
    assertThat(compareToResult).isZero()
  }

  @Test
  fun `first train 2 wagons, other has 1, comparator compares first bigger`() {
    // given
    val comparator = ByWagonComparator()
    val first = Train(numberOfWagons = 2)
    val second = Train(numberOfWagons = 1)
    // when
    val compareResult = comparator.compare(first, second)
    // then
    assertThat(compareResult).isPositive().isNotZero()
  }

  @Test
  fun `first train 3 wagons, other has 4, comparator compares first lesser`() {
    // given
    val comparator = ByWagonComparator()
    val first = Train(numberOfWagons = 3)
    val second = Train(numberOfWagons = 4)
    // when
    val compareResult = comparator.compare(first, second)
    // then
    assertThat(compareResult).isNegative().isNotZero()
  }

  @Test
  fun `both trains with 7 wagons, comparator compares equal`() {
    // given
    val comparator = ByWagonComparator()
    val first = Train(numberOfWagons = 7)
    val second = Train(numberOfWagons = 7)
    // when
    val compareResult = comparator.compare(first, second)
    // then
    assertThat(compareResult).isZero()
  }

}