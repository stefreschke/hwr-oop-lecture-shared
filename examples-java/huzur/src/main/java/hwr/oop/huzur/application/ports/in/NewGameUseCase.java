package hwr.oop.huzur.application.ports.in;

import java.util.List;

public interface NewGameUseCase {

  void newGame(String trump, List<String> players);

  void newGame(String id, String trump, List<String> players);
}
