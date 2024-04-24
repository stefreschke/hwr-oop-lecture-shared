package hwr.oop.huzur.application;

import hwr.oop.huzur.domain.Game;

public interface LoadGamePort {

  Game loadById(String id);
}
