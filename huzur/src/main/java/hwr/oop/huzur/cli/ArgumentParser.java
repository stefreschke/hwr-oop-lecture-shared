package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import java.util.List;

final class ArgumentParser {

  private final NewGameUseCase newGameUseCase;
  private final PlayOnGameUseCase playOnGameUseCase;
  private final PickupStackOnGameUseCase pickupStackOnGameUseCase;
  private final GameStateQuery gameStateQuery;
  private final List<MutableCommand> commands;

  public ArgumentParser(NewGameUseCase newGameUseCase, PlayOnGameUseCase playOnGameUseCase,
      PickupStackOnGameUseCase pickupStackOnGameUseCase, GameStateQuery gameStateQuery) {

    this.newGameUseCase = newGameUseCase;
    this.playOnGameUseCase = playOnGameUseCase;
    this.pickupStackOnGameUseCase = pickupStackOnGameUseCase;
    this.gameStateQuery = gameStateQuery;
    this.commands = List.of(
        new HelpCommand(),
        new NewGameCommand(newGameUseCase),
        new PlayOnGameCommand(playOnGameUseCase, pickupStackOnGameUseCase)
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
