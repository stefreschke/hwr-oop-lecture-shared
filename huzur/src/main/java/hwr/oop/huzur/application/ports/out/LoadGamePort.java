package hwr.oop.huzur.application.ports.out;

import hwr.oop.huzur.domain.Game;

public interface LoadGamePort {

  Game loadById(String id);
}
