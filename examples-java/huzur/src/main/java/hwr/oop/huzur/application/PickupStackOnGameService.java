package hwr.oop.huzur.application;

import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.out.GameRepository;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Player;

public final class PickupStackOnGameService implements PickupStackOnGameUseCase {

  private final LoadGamePort loadGamePort;
  private final SaveGamePort saveGamePort;

  public PickupStackOnGameService(GameRepository gameRepository) {
    this(gameRepository, gameRepository);
  }

  public PickupStackOnGameService(LoadGamePort loadGamePort, SaveGamePort saveGamePort) {
    this.loadGamePort = loadGamePort;
    this.saveGamePort = saveGamePort;
  }

  @Override
  public void pickup(String gameId, String playerIdString) {
    final var player = Player.id(playerIdString);
    final var loadedGame = loadGamePort.loadById(gameId);
    final var updatedGame = loadedGame.pickup(player);
    saveGamePort.save(updatedGame);
  }
}
