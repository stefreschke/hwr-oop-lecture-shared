package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

final class NewGameCommand implements MutableCommand {

  private final NewGameUseCase newGameUseCase;
  private String trump;
  private String gameId;
  private List<String> players;

  public NewGameCommand(NewGameUseCase newGameUseCase) {
    this.newGameUseCase = newGameUseCase;
  }

  @Override
  public void parse(List<String> arguments) {
    this.gameId = arguments.get(2);
    this.trump = arguments.get(4);
    this.players = arguments.subList(6, arguments.size());
  }

  @Override
  public boolean isApplicable(List<String> arguments) {
    return arguments.size() >= 8
        && arguments.get(1).equals("id")
        && arguments.get(3).equals("trump")
        && arguments.get(5).equals("players");
  }

  @Override
  public void invoke(PrintStream out) {
    Objects.requireNonNull(gameId);
    Objects.requireNonNull(trump);
    Objects.requireNonNull(players);

    newGameUseCase.newGame(gameId, trump, players);
    out.println("Created new game with id: " + gameId);
  }
}
