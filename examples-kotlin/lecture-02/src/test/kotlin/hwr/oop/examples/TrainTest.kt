package hwr.oop.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TrainTest {
	
	@Test
	fun `Train class can be used to create object of type Train`() {
		val train = Train()  // classes can be used to create objects
		assertThat(train).isNotNull
	}
	
	@Test
	fun `Train object can be accessed`() {
		val trainObject = TrainObject
		// TrainObject() ...does not work, because objects are no classes!
		assertThat(trainObject).isNotNull
	}
	
}