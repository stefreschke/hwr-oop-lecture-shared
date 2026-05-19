import org.junit.jupiter.api.Test

class Stuff {
	
	@Test
	fun `train of wagons`() {
		val train = Train<Wagon>()
		val otherTrain: Train<Wagon> = Train<Wagon>()
	}
	
	@Test
	fun `dumb train`() {
		val train = DumbTrain()
		train.attach(Wagon("1"))
		val detachLast: Locomotive = train.detachLast()
	}
	
}

class Locomotive : Couplable {
	// nothing else required
}

interface Couplable {
	// nothing else required
}

class Wagon(val id: String) : Couplable, Comparable<Wagon> {
	override fun compareTo(other: Wagon): Int {
		return id.compareTo(other.id)
	}
}

class Train<T> where T : Couplable,
                     T : Comparable<T> {
	fun attach(attachable: T) {
		TODO()
	}
	
	fun detachLast(): T {
		TODO()
	}
}

class DumbTrain {
	fun <E> attach(attachable: E) {
		TODO()
	}
	
	fun <E> detachLast(): E {
		TODO()
	}
}