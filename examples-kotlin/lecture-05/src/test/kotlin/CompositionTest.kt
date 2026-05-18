import hwr.oop.examples.association.composition.CompositionTrain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CompositionTest {

  @Test
  fun `composition creates parts by itself`() {
    // given
    val train = CompositionTrain(3)
    // when
    val wagons = train.wagons
    // then
    assertThat(wagons)
      .hasSize(3)
      .hasSize(train.numberOfWagons())
      .doesNotContainNull()
  }

}