package hwr.oop.exercise

import hwr.oop.exercise.HelloPerson
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class HelloPersonTest : AnnotationSpec() {
    @Test
    fun `A person says hello and tells its name`() {
        // given
        val name = "Hans"
        val person = HelloPerson(name)
        // when
        val result = person.sayHello()
        // then
        assertThat(result).isEqualTo("Hallo, mein Name ist Hans")
        assertThat(result).contains(name)
    }
}
