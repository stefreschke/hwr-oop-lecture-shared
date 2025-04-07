package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MutableTrainTest : AnnotationSpec() {
    @Test
    fun `public attributes can be changed from outside`() {
        val train = MutableTrain()
        train.speed = 100
        val retrievedSpeed = train.speed
        assertThat(retrievedSpeed).isEqualTo(100)
    }
}
