package hwr.oop.huzur.cli;

import java.util.List;

final class ArgumentParser {

  public Command parse(List<String> arguments) {
    if (arguments.isEmpty()) {
      return new HelpCommand();
    } else if (arguments.getFirst().equals("new_game")) {
      if (arguments.size() >= 5 && arguments.get(2).equals("players")) {
        final var gameId = arguments.get(1);
        final var playerIds = arguments.subList(3, arguments.size());
        return new NewGameCommand(gameId, playerIds);
      } else {
        throw new IllegalArgumentException("Incomplete NewGameCommand: " + arguments);
      }
    } else {
      throw new IllegalArgumentException("Cannot parse commands: " + arguments);
    }
  }
}
