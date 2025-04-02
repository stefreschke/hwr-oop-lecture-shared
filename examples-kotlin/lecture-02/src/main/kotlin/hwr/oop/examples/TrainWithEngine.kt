package hwr.oop.examples

class TrainWithEngine {
  private var speed = 0
  private val engine = Engine()

  fun accelerateTo(targetSpeed: Int) {
    if (speed == 0 && targetSpeed > 0) {
      startEngine()
    } else if (targetSpeed == 0) {
      stopEngine()
    }
    this.speed = targetSpeed
  }

  fun speed(): Int {
    return speed
  }

  private fun startEngine() {
    engine.start()
  }

  private fun stopEngine() {
    engine.stop()
  }

  fun isEngineStarted(): Boolean {
    return engine.isRunning()
  }
}

