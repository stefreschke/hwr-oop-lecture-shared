package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Cli {

  private final PrintStream out;
  private final NewGameUseCase newGameUseCase;
  private final PlayOnGameUseCase playOnGameUseCase;
  private final PickupStackOnGameUseCase pickupStackOnGameUseCase;
  private final GameStateQuery gameStateQuery;
  private final ArgumentParser argumentParser;

  public Cli(OutputStream outputStream, NewGameUseCase newGameUseCase,
      PlayOnGameUseCase playOnGameUseCase, PickupStackOnGameUseCase pickupStackOnGameUseCase,
      GameStateQuery gameStateQuery) {
    this.out = new PrintStream(outputStream);

    this.newGameUseCase = newGameUseCase;
    this.playOnGameUseCase = playOnGameUseCase;
    this.pickupStackOnGameUseCase = pickupStackOnGameUseCase;
    this.gameStateQuery = gameStateQuery;
    this.argumentParser = new ArgumentParser(newGameUseCase, playOnGameUseCase,
        pickupStackOnGameUseCase, gameStateQuery);
  }

  public void handle(String... arguments) {
    handle(Arrays.asList(arguments));
  }

  public void handle(List<String> arguments) {
    final List<String> mutable = new ArrayList<>(arguments);
    if (mutable.contains("--debug")) {
      out.println("Welcome to Huzur, the funky mongolian card game!");
      out.println(" Arguments were: " + arguments);
      mutable.remove("--debug");
    }
    final var reduced = Collections.unmodifiableList(mutable);
    final var command = argumentParser.parse(reduced);
    command.invoke(out);
  }
}
