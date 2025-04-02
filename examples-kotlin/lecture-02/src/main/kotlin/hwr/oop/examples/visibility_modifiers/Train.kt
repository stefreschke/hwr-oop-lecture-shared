package hwr.oop.examples.visibility_modifiers

open class Train {
  private var speed: Int = 0
  private var engineStarted: Boolean = false

  fun isEngineStarted(): Boolean {
    return engineStarted
  }

  internal fun startEngine() {
    engineStarted = true
    println("Engine started")
  }

  open fun accelerateTo(speed: Int) {
    if (!engineStarted) {
      startEngine()
    }
    this.speed = speed
  }
}