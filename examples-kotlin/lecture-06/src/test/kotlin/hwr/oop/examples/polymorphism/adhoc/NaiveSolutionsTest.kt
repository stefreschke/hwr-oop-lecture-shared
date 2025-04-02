package hwr.oop.examples.polymorphism.adhoc

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class NaiveSolutionsTest : AnnotationSpec() {

  @Test
  fun `train with no wagons, has no wagons`() {
    // when
    val train = NaiveTrain()
    // then
    val numberOfWagons = train.numberOfWagons()
    assertThat(numberOfWagons).isZero()
  }

  @Test
  fun `train with 1 wagon, has 1 wagon`() {
    // when
    val train = NaiveTrain(Wagon())
    // then
    val numberOfWagons = train.numberOfWagons()
    assertThat(numberOfWagons).isEqualTo(1)
  }

  @Test
  fun `train with 2 wagons, has 2 wagons`() {
    // when
    val train = NaiveTrain(Wagon(), Wagon())
    // then
    val numberOfWagons = train.numberOfWagons()
    assertThat(numberOfWagons).isEqualTo(2)
  }


  @Test
  fun `train with 3 wagons, has 3 wagons`() {
    // when
    val train = NaiveTrain(Wagon(), Wagon(), Wagon())
    // then
    val numberOfWagons = train.numberOfWagons()
    assertThat(numberOfWagons).isEqualTo(3)
  }

  @Test
  fun `train provided wagons in list, has same number of wagons`() {
    // given
    val wagons = listOf(Wagon(), Wagon(), Wagon(), Wagon())
    // when
    val train = NaiveTrain(wagons)
    // then
    val numberOfWagons = train.numberOfWagons()
    assertThat(numberOfWagons).isEqualTo(wagons.size)
  }

}
