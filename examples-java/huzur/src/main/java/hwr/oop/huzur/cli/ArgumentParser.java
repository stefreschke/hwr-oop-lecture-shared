package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import java.util.List;
import java.util.function.Supplier;

final class ArgumentParser {

  private final List<MutableCommand> commands;

  public ArgumentParser(
      Supplier<NewGameUseCase> newGameUseCase,
      Supplier<PlayOnGameUseCase> playOnGameUseCase,
      Supplier<PickupStackOnGameUseCase> pickupStackOnGameUseCase,
      Supplier<GameStateQuery> gameStateQuery
  ) {
    this.commands = List.of(
        new HelpCommand(),
        new NewGameCommand(newGameUseCase),
        new PlayOnGameCommand(playOnGameUseCase, pickupStackOnGameUseCase),
        new GameQueryCommand(gameStateQuery)
    );
  }

  public Command parse(List<String> arguments) {
    if (arguments.isEmpty()) {
      return new HelpCommand();
    }
    final var candidate = commands.stream()
        .filter(c -> c.isApplicable(arguments)).findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown commmand: " + arguments));
    candidate.parse(arguments);
    return candidate;
  }
}
