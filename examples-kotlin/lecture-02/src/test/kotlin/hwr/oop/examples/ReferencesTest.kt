package hwr.oop.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReferencesTest {
  @Test
  fun `Train with wagons, has wagons`() {
    val first = Wagon()
    val second = Wagon()
    val train = TrainWithWagons(listOf(first, second))
    val retrievedWagons = train.wagons()
    assertThat(retrievedWagons).containsExactly(first, second)
  }
}