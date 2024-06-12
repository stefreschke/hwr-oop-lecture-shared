package hwr.oop.huzur.application.ports.out;

import hwr.oop.huzur.domain.Game;

public interface LoadGamePort {

  Game loadById(String id) throws CouldNotLoadException;

  class CouldNotLoadException extends RuntimeException {

    public CouldNotLoadException(String message) {
      super(message);
    }

    public CouldNotLoadException(Throwable cause) {
      super(cause);
    }
  }
}
