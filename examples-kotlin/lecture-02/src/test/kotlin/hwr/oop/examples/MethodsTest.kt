package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MethodsTest : AnnotationSpec() {

  @Test
  fun `NonMovingTrain's speed is 0`() {
    val nonMovingTrain = NonMovingTrain()
    val speed = nonMovingTrain.speed()  // send message
    // save answer in variable
    assertThat(speed).isZero()
  }

  @Test
  fun `TrainWithEngine's accelerates to 420, speed is 420`() {
    val train = TrainWithEngine()
    train.accelerateTo(420)
    val speed = train.speed()
    assertThat(speed).isEqualTo(420)
  }

  @Test
  fun `TrainWithEngine's accelerates to 420, engine is started`() {
    val train = TrainWithEngine()
    train.accelerateTo(420)
    val isEngineStarted = train.isEngineStarted()
    assertThat(isEngineStarted).isTrue()
  }

  @Test
  fun `TrainWithEngine's does not accelerate, engine is not started`() {
    val train = TrainWithEngine()
    val isEngineStarted = train.isEngineStarted()
    assertThat(isEngineStarted).isFalse()
  }

  @Test
  fun `TrainWithEngine's accelerates to 420, then brakes to 0, engine is not started`() {
    val train = TrainWithEngine()
    train.accelerateTo(420)
    train.accelerateTo(0)
    val isEngineStarted = train.isEngineStarted()
    assertThat(isEngineStarted).isFalse()
  }
}