import hwr.oop.examples.association.aggregation.AggregateTrain
import hwr.oop.examples.association.aggregation.AggregatedWagon
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class AggregationTest : AnnotationSpec() {

  @Test
  fun `parts of an aggregate have to be created first`() {
    // given
    val wagons = listOf(
      AggregatedWagon(),
      AggregatedWagon(),
      AggregatedWagon(),
    )
    val train = AggregateTrain(wagons)
    // then
    assertThat(train.numberOfWagons()).isEqualTo(3)
  }

}