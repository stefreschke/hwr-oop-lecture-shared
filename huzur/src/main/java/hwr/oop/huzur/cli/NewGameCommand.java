package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import java.io.PrintStream;
import java.util.List;

final class NewGameCommand implements Command {

  private final List<Player> players;
  private final Color trump;

  public NewGameCommand(String trump, List<String> players) {
    this.players = players.stream().map(Player::id).toList();
    this.trump = Color.valueOf(trump.toUpperCase());
  }

  @Override
  public void invoke(PrintStream out, SaveGamePort saveGamePort, LoadGamePort loadGamePort) {
    final var newGame = Game.fresh(trump).playedBy(players);
    saveGamePort.save(newGame);
    out.println("Created new game with id: " + newGame.id().value());
  }
}
