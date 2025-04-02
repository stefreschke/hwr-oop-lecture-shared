package hwr.oop.huzur.tests.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import hwr.oop.huzur.cli.Main;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MainTest {

  @AfterEach
  void tearDown() {
    final var file = new File("./example.csv");
    file.delete();
  }

  @Test
  void main_CanBeCalled() {
    assertDoesNotThrow(() -> Main.varargMain());
  }

  @Test
  void main_CreateNewGame_CanBeLoaded() {
    Main.varargMain(
        "new_game", "id", "1337",
        "trump", "HEARTS",
        "players", "alpha", "beta",
        "--file", "example.csv"
    );
    final var file = new File("./example.csv");
    assertThat(file).exists();
  }

}
