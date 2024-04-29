package hwr.oop.huzur.tests.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.cli.Cli;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CliTest {

  @Test
  void debug_DisplaysHuzurMessage() {
    final var outputStream = new ByteArrayOutputStream();
    final SaveGamePort saveGameMock = mock();
    final LoadGamePort loadGameMock = mock();
    final var cli = new Cli(outputStream, saveGameMock, loadGameMock);
    final var options = List.of("--debug");
    cli.main(options);
    final var output = outputStream.toString();
    assertThat(output)
        .contains("Huzur")
        .contains(options);
  }

  @Test
  void newGame_CreatesNewGame() {
    final var outputStream = new ByteArrayOutputStream();
    final SaveGamePort saveGameSpy = spy();
    final LoadGamePort loadGameMock = mock();
    final var cli = new Cli(outputStream, saveGameSpy, loadGameMock);
    final var options = List.of("new_game", "HEARTS", "players", "alpha", "beta");
    cli.main(options);
    final var output = outputStream.toString();
    assertAll(
        () -> assertThat(output).contains("Created new game with id:"),
        () -> verify(saveGameSpy).save(Mockito.any())
    );
  }
}
