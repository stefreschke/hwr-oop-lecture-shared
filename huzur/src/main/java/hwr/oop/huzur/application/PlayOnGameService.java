package hwr.oop.huzur.application;

import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.CardConverter;
import java.util.List;

public final class PlayOnGameService implements PlayOnGameUseCase {

  private final LoadGamePort loadGamePort;
  private final SaveGamePort saveGamePort;
  private final CardConverter converter;

  public PlayOnGameService(LoadGamePort loadGamePort, SaveGamePort saveGamePort) {
    this.loadGamePort = loadGamePort;
    this.saveGamePort = saveGamePort;
    this.converter = new CardConverter();
  }

  @Override
  public void play(String gameId, String playerIdString, List<String> cardsString) {
    final var player = Player.id(playerIdString);
    final var layoutCards = cardsString.stream().map(converter::convert).toList();
    final var loadedGame = loadGamePort.loadById(gameId);
    final var updatedGame = loadedGame.play(player, layoutCards);
    saveGamePort.save(updatedGame);
  }

  @Override
  public void pickup(String gameId, String playerIdString) {
    final var player = Player.id(playerIdString);
    final var loadedGame = loadGamePort.loadById(gameId);
    final var updatedGame = loadedGame.pickup(player);
    saveGamePort.save(updatedGame);
  }

}
