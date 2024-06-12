package hwr.oop.huzur.tests.application;

import hwr.oop.huzur.application.ports.out.GameRepository;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;

final class TestDoubleRepository implements GameRepository {

  private final SaveGamePort saveGamePort;
  private final LoadGamePort loadGamePort;

  public TestDoubleRepository(SaveGamePort saveGamePort, LoadGamePort loadGamePort) {
    this.saveGamePort = saveGamePort;
    this.loadGamePort = loadGamePort;
  }

  @Override
  public Game loadById(String id) throws CouldNotLoadException {
    return loadGamePort.loadById(id);
  }

  @Override
  public void save(Game game) throws CouldNotSaveException {
    saveGamePort.save(game);
  }
}
