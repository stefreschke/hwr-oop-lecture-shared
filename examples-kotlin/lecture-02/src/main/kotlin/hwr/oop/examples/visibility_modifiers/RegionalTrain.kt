package hwr.oop.examples.visibility_modifiers

class RegionalTrain : Train() {

  override fun accelerateTo(speed: Int) {
    if (speed > 100) {
      throw IllegalArgumentException("Regional train can't go faster than 100 km/h")
    }
    this.startEngine()
  }

}
