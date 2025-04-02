package hwr.oop.examples

class Engine {
  private var running: Boolean = false

  fun start() {
    running = true
  }

  fun stop() {
    running = false
  }

  fun isRunning(): Boolean {
    return running
  }
}