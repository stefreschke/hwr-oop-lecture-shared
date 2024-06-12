package hwr.oop.huzur.application.ports.out;

import hwr.oop.huzur.domain.Game;

public interface SaveGamePort {

  void save(Game game) throws CouldNotSaveException;

  class CouldNotSaveException extends RuntimeException {

    public CouldNotSaveException(Throwable cause) {
      super(cause);
    }
  }
}
