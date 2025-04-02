package hwr.oop.huzur.application.ports.in;

import java.util.List;

public interface PlayOnGameUseCase {

  void play(String gameId, String playerIdString, List<String> cardsString);
}
