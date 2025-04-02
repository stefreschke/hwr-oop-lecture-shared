package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat

class HelloWorldNormalSpecTest : AnnotationSpec() {
  @Test
  fun `Hello World`() {
    println("Hello World!")
  }
}

class TestibleHelloWorldTest : AnnotationSpec() {
  @Test
  fun `outputString is hello world, assertion with AssertJ`() {
    val output = HelloWorld.outputString()
    assertThat(output).isEqualTo("Hello World!")
  }

  @Test
  fun `outputString is hello world, assertion with Kotest`() {
    val output = HelloWorld.outputString()
    output shouldBe "Hello World!"
  }
}