package hwr.oop.huzur.tests.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import hwr.oop.huzur.cli.Cli;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CliTest {

  @Mock
  NewGameUseCase newGameUseCase;

  @Mock
  PlayOnGameUseCase playOnGameUseCase;

  @Mock
  PickupStackOnGameUseCase pickupStackOnGameUseCase;

  @Mock
  GameStateQuery gameStateQuery;

  private OutputStream outputStream;
  private Cli sut;

  @BeforeEach
  void setUp() {
    this.outputStream = new ByteArrayOutputStream();
    this.sut = new Cli(outputStream, newGameUseCase, playOnGameUseCase,
        pickupStackOnGameUseCase, gameStateQuery);
  }

  @Test
  void debug_DisplaysHuzurMessage() {
    sut.handle("--debug");
    final var output = outputStream.toString();
    assertThat(output)
        .contains("Huzur")
        .contains(List.of("--debug"));
  }

  @Test
  void newGame_CreatesNewGame() {
    sut.handle("new_game", "id", "1337", "trump", "HEARTS", "players", "alpha",
        "beta");
    final var output = outputStream.toString();
    assertThat(output).contains("Created new game with id: 1337");
    verify(newGameUseCase).newGame("1337", "HEARTS", List.of("alpha", "beta"));
  }

  @Test
  void onGamePLayerLays_SingleCard_PlaySingleCardUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7");
    final var output = outputStream.toString();
    assertThat(output).contains("alpha", "lays", "H7");
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7"));
  }

  @Test
  void onGamePlayerLays_ThreeCardsCommaSeparated_PlayThreeCardsUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7,S7,H8");
    final var output = outputStream.toString();
    assertThat(output).contains("alpha", "lays", "H7", "H8", "S7");
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7", "S7", "H8"));
  }

  @Test
  void onGamePlayerLays_ThreeCardsSpaceSeparated_PlayThreeCardsUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7", "S7", "H8");
    final var output = outputStream.toString();
    assertThat(output).contains("alpha", "lays", "H7", "H8", "S7");
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7", "S7", "H8"));
  }
}
