package hwr.oop.huzur.application.ports.in;

import java.util.List;
import java.util.Map;

public interface GameStateQuery {

  GameStateDto gameStateOf(String gameId);

  record GameStateDto(String turn,
                      List<String> cardsInHand,
                      List<String> layoutCards,
                      List<String> cardsToDrawOnPickup,
                      boolean layoutFinishedIfAnswered,
                      Map<String, Integer> remainingCardsInHand,
                      int remainingCardsInDeck,
                      String trump
  ) {
    // nothing else to do here
  }
}
