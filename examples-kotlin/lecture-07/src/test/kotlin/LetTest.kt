import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LetTest {
	
	@Test
	fun `1? + 2? = 3?`() {
		val first: Int? = 1
		val second: Int? = 2
		val sum = sut_sum(first, second)
		assertThat(sum).isEqualTo(3)
		assertThat(sum).isNotNull()
	}
}

private fun sut_sum(first: Int?, second: Int?): Int? {
	return second?.let { first?.plus(it) }
}