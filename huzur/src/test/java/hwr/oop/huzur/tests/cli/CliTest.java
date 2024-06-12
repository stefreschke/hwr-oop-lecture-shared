package hwr.oop.huzur.tests.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.GameStateQuery.GameStateDto;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import hwr.oop.huzur.application.ports.out.GameRepository;
import hwr.oop.huzur.application.ports.out.LoadGamePort.CouldNotLoadException;
import hwr.oop.huzur.cli.Cli;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

  @Mock
  Function<Path, GameRepository> gameRepositoryFunction;

  @Mock
  GameRepository gameRepository;

  private OutputStream outputStream;
  private OutputStream errorStream;
  private Cli sut;

  @BeforeEach
  void setUp() {
    this.outputStream = new ByteArrayOutputStream();
    this.errorStream = new ByteArrayOutputStream();
    lenient().when(gameRepositoryFunction.apply(any())).thenReturn(gameRepository);
    this.sut = new Cli(
        outputStream,
        errorStream,
        repo -> newGameUseCase,  // disregard repo, pass mocks
        repo -> playOnGameUseCase,
        repo -> pickupStackOnGameUseCase,
        repo -> gameStateQuery,
        gameRepositoryFunction
    );
  }

  @Test
  void newGame_CreatesNewGame() {
    sut.handle("new_game", "id", "1337", "trump", "HEARTS", "players", "alpha",
        "beta", "--file", "example.csv");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains("Created new game with id: 1337");
    verify(gameRepositoryFunction).apply(pathOf("example.csv"));
    verify(newGameUseCase).newGame("1337", "HEARTS", List.of("alpha", "beta"));
  }

  @Test
  void debug_DisplaysDebugInformation() {
    sut.handle("--debug");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains(
        "Welcome to Huzur, the funky mongolian card game!",
        "Arguments were: ", "--debug"
    );
  }

  @Test
  void help_DisplaysAllAvailableCommands() {
    sut.handle("help");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains(
        "help - display help message",
        "new_game id {} trump {} players {} - Creates a new game",
        "on game {} player {} play/lay/pick {} - Advance game by setting cards",
        "on game {} state - display game state"
    );
  }

  @Test
  void onGamePLayerLays_SingleCard_PlaySingleCardUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7", "--file", "example.csv");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains("alpha", "lays", "H7", "on game 1337");
    verify(gameRepositoryFunction).apply(pathOf("example.csv"));
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7"));
  }

  @Test
  void onGamePlayerLays_ThreeCardsCommaSeparated_PlayThreeCardsUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7,S7,H8", "--file",
        "example.csv");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains("alpha", "lays", "H7", "H8", "S7", "on game 1337");
    verify(gameRepositoryFunction).apply(pathOf("example.csv"));
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7", "S7", "H8"));
  }

  @Test
  void onGamePlayerLays_ThreeCardsSpaceSeparated_PlayThreeCardsUseCaseCalled() {
    sut.handle("on", "game", "1337", "player", "alpha", "lays", "H7", "S7", "H8", "--file",
        "example.csv");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains("alpha", "lays", "H7", "H8", "S7", "on game 1337");
    verify(gameRepositoryFunction).apply(pathOf("example.csv"));
    verify(playOnGameUseCase).play("1337", "alpha", List.of("H7", "S7", "H8"));
  }

  @Test
  void onGamePlayerPicksUp_PickupCalled() {
    sut.handle("on", "game", "1337", "player", "beta", "picks", "up", "--file", "example.csv");
    final var output = outputStream.toString();
    final var errors = errorStream.toString();
    assertThat(errors).isEmpty();
    assertThat(output).contains("beta", "picked up", "on game 1337");
    verify(gameRepositoryFunction).apply(pathOf("example.csv"));
    verify(pickupStackOnGameUseCase).pickup("1337", "beta");
  }

  @Test
  void gameState_QueryCalled() {
    final var gameStateDtoFixture = new GameStateDto(
        "alpha",
        List.of("AH", "AC", "AD"),
        List.of("2H", "2C", "2D"),
        List.of("2H", "2C", "2D"),
        false,
        Map.of(
            "alpha", 3,
            "beta", 4,
            "gamma", 5
        ),
        0,
        "HEARTS"
    );
    when(gameStateQuery.gameStateOf("1337")).thenReturn(gameStateDtoFixture);
    sut.handle("on", "game", "1337", "state", "--file", "example.csv");
    assertThat(outputStream.toString()).contains(
        "turn: alpha", "hand: AH,AC,AD", "layout: 2H,2C,2D",
        "cards to pickup from layout (3): 2H,2C,2D",
        "trump: HEARTS", "remaining Deck: empty",
        "beta has 4 cards remaining", "gamma has 5 cards remaining"
    );
    assertThat(errorStream.toString()).isEmpty();
    verify(gameStateQuery).gameStateOf("1337");
  }

  @Test
  @ErrorHandlingTag
  void gameState_QueryNonExistingGame_Exception() {
    when(gameStateQuery.gameStateOf("1337"))
        .thenThrow(new CouldNotLoadException("Could not find game with id 1337"));
    try {
      sut.handle("on", "game", "1337", "state", "--file", "example.csv");
      fail("MockedException was not thrown");
    } catch (CouldNotLoadException e) {
      // expected, nothing else to do
    }
    assertThat(outputStream.toString()).isEmpty();
    assertThat(errorStream.toString())
        .contains("Could not find game with id 1337");
    verify(gameStateQuery).gameStateOf("1337");
  }

  @Test
  @ErrorHandlingTag
  void dashDashFileWithoutActualFile_ExceptionPointingToWrongUsage() {
    try {
      sut.handle("help", "--file");
      fail("MockedException was not thrown");
    } catch (IllegalArgumentException e) {
      // expected, nothing else to do
    }
    assertThat(outputStream.toString()).isEmpty();
    assertThat(errorStream.toString())
        .contains("--file can not be the last argument");
  }

  @Test
  @ErrorHandlingTag
  void dashDashFileWithoutAFileProvided_ExceptionPointingToWrongUsage() {
    try {
      sut.handle("help", "--file", "example");
      fail("MockedException was not thrown");
    } catch (IllegalArgumentException e) {
      // expected, nothing else to do
    }
    assertThat(outputStream.toString()).isEmpty();
    assertThat(errorStream.toString())
        .contains("variable passed to --file must be file name", "but was: example");
  }

  @Test
  void file_ArgumentUsedByCsvAdapter() {
    final var csvFileString = "src/test/resources/loadExampleTest.csv";
    sut.handle("--file", csvFileString);
    verify(gameRepositoryFunction).apply(pathOf(csvFileString));
  }

  private Path pathOf(String filename) {
    return new File(filename).toPath();
  }
}
