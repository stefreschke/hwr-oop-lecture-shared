package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class ReferencesTest : AnnotationSpec() {
  @Test
  fun `Train with wagons, has wagons`() {
    val first = Wagon()
    val second = Wagon()
    val train = TrainWithWagons(listOf(first, second))
    val retrievedWagons = train.wagons()
    assertThat(retrievedWagons).containsExactly(first, second)
  }
}