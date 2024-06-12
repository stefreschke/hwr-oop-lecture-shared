package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import hwr.oop.huzur.application.ports.out.GameRepository;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Cli {

  private final PrintStream out;
  private final PrintStream err;
  private final Function<Path, GameRepository> gameRepositoryFactory;
  private final ArgumentParser argumentParser;
  private GameRepository gameRepository;

  public Cli(
      OutputStream outputStream,
      OutputStream errorStream,
      Function<GameRepository, NewGameUseCase> newGameUseCaseFactory,
      Function<GameRepository, PlayOnGameUseCase> playOnGameUseCaseFactory,
      Function<GameRepository, PickupStackOnGameUseCase> pickupStackOnGameUseCaseFactory,
      Function<GameRepository, GameStateQuery> gameStateQueryFactory,
      Function<Path, GameRepository> gameRepositoryFactory
  ) {
    this.out = new PrintStream(outputStream);
    this.err = new PrintStream(errorStream);
    this.gameRepositoryFactory = gameRepositoryFactory;
    this.argumentParser = new ArgumentParser(
        () -> safelyCreateUseCaseUsing(gameRepository, newGameUseCaseFactory),
        () -> safelyCreateUseCaseUsing(gameRepository, playOnGameUseCaseFactory),
        () -> safelyCreateUseCaseUsing(gameRepository, pickupStackOnGameUseCaseFactory),
        () -> safelyCreateUseCaseUsing(gameRepository, gameStateQueryFactory)
    );
  }

  private <T> T safelyCreateUseCaseUsing(
      GameRepository gameRepository,
      Function<GameRepository, T> factory
  ) {
    Objects.requireNonNull(gameRepository);
    return factory.apply(gameRepository);
  }

  public void handle(String... arguments) {
    handleCatchingExceptions(Arrays.asList(arguments));
  }

  public void handle(List<String> arguments) {
    handleCatchingExceptions(arguments);
  }

  private void handleCatchingExceptions(List<String> arguments) {
    try {
      handleAllowingExceptions(arguments);
    } catch (RuntimeException e) {
      err.println(e.getMessage());
      throw e;
    }
  }

  private void handleAllowingExceptions(List<String> arguments) {
    final List<String> mutable = new ArrayList<>(arguments);
    if (mutable.contains("--debug")) {
      out.println("Welcome to Huzur, the funky mongolian card game!");
      out.println(" Arguments were: " + arguments);
      mutable.remove("--debug");
    }
    if (mutable.contains("--file")) {
      final var positionOfFile = mutable.indexOf("--file");
      assertIsValidIndexInList(positionOfFile, mutable);
      final var afterFile = mutable.get(positionOfFile + 1);
      assertIsValidFileName(afterFile);
      final var path = parsePath(afterFile);
      updateUseCasesToUseNewPersistenceAdapter(path);
      mutable.remove(positionOfFile + 1);
      mutable.remove(positionOfFile);
    }
    final var reduced = Collections.unmodifiableList(mutable);
    final var command = argumentParser.parse(reduced);
    command.invoke(out);
  }

  private void updateUseCasesToUseNewPersistenceAdapter(Path path) {
    this.gameRepository = gameRepositoryFactory.apply(path);
  }

  private void assertIsValidIndexInList(int positionOfFile, List<String> mutable) {
    if (positionOfFile + 1 == mutable.size()) {
      throw new IllegalArgumentException("--file can not be the last argument, but was");
    }
  }

  private void assertIsValidFileName(String fileNameCandidate) {
    if (fileNameCandidate.contains(" ") || !fileNameCandidate.contains(".")) {
      throw new IllegalArgumentException(
          "variable passed to --file must be file name, but was: " + fileNameCandidate);
    }
  }

  private Path parsePath(String afterFile) {
    return new File(afterFile).toPath();
  }
}
