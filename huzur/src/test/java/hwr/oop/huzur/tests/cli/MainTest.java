package hwr.oop.huzur.tests.cli;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import hwr.oop.huzur.cli.Main;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void main_CanBeCalled() {
    assertDoesNotThrow(() -> Main.main(new String[]{}));
  }
}
