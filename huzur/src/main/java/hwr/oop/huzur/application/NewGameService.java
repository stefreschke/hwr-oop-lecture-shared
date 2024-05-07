package hwr.oop.huzur.application;

import hwr.oop.huzur.application.ports.in.NewGameUseCase;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.List;

public final class NewGameService implements NewGameUseCase {

  private final SaveGamePort saveGamePort;

  public NewGameService(SaveGamePort saveGamePort) {
    this.saveGamePort = saveGamePort;
  }

  @Override
  public void newGame(String trump, List<String> players) {
    final var convertedPlayers = players.stream().map(Player::id).toList();
    final var convertedTrump = Color.valueOf(trump.toUpperCase());
    final var newGame = Game.fresh(convertedTrump).playedBy(convertedPlayers);
    saveGamePort.save(newGame);
  }

  @Override
  public void newGame(String id, String trump, List<String> players) {
    final var convertedPlayers = players.stream().map(Player::id).toList();
    final var convertedTrump = Color.valueOf(trump.toUpperCase());
    final var newGame = Game.fresh(id, convertedTrump).playedBy(convertedPlayers);
    saveGamePort.save(newGame);
  }
}
