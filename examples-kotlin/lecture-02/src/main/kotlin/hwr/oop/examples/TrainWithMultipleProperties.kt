package hwr.oop.examples

class TrainWithMultipleProperties(
  private val trainNumber: Int,
  private val isElectrified: Boolean,
) {
  private var speed: Int = 0
  private val engine = Engine()

  fun trainNumber(): Int {
    return trainNumber
  }

  fun speed(): Int {
    return speed
  }

  fun isElectrified(): Boolean {
    return isElectrified
  }

  fun isEngineStarted(): Boolean {
    return engine.isRunning()
  }

  fun accelerateTo(targetSpeed: Int) {
    if (speed == 0 && targetSpeed > 0) {
      startEngine()
    } else if (targetSpeed == 0) {
      stopEngine()
    }
    this.speed = targetSpeed
  }

  private fun startEngine() {
    engine.start()
  }

  private fun stopEngine() {
    engine.stop()
  }
}