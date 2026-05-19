package hwr.oop.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloWorldTest {
	@Test
	fun `Hello World`() {
		println("Hello World!")
	}
	
	@Test
	fun `outputString is hello world, assertion with AssertJ`() {
		val output = HelloWorld.outputString()
		assertThat(output).isEqualTo("Hello World!")
	}
}