package hwr.oop.examples

class Train(
  private val name: String = "ICE1337",
  private val start: String = "Berlin",
  private val destination: String = "Hamburg",
  val numberOfWagons: Int = 1,
) : Comparable<Train> {

  private var speed: Int = 0

  fun accelerateTo(speed: Int) {
    this.speed = speed
  }

  override fun equals(other: Any?): Boolean {
    // ignores numberOfWagons and speed
    if (this === other) return true
    if (other == null) return false
    if (this::class != other::class) return false

    other as Train

    if (name != other.name) return false
    if (start != other.start) return false
    if (destination != other.destination) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + start.hashCode()
    result = 31 * result + destination.hashCode()
    return result
  }

  override fun compareTo(other: Train): Int {
    return speed.compareTo(other.speed)
  }

}