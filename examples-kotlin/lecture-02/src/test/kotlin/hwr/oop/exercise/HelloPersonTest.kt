package hwr.oop.exercise

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloPersonTest {
	@Test
	fun `A Hans says hello and tells its name`() {
		// given
		val name = "Hans"
		val person = HelloPerson
		// when
		val result = person.sayHello()
		// then
		assertThat(result).isEqualTo("Hallo, mein Name ist Hans")
		assertThat(result).contains(name)
	}
}
