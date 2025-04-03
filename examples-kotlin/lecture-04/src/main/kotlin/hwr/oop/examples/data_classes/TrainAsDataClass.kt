package hwr.oop.examples.data_classes

data class TrainAsDataClass(
  private val name: String = "ICE1337",
  private val start: String = "Berlin",
  private val destination: String = "Hamburg",
  private val speed: Int = 0,
  private val numberOfWagons: Int = 1,
)
