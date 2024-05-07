package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlayOnGameCommand implements MutableCommand {

  private final PlayOnGameUseCase playOnGameUseCase;
  private final PickupStackOnGameUseCase pickupStackOnGameUseCase;
  private String gameId;
  private String playerId;
  private List<String> cards;

  public PlayOnGameCommand(PlayOnGameUseCase playOnGameUseCase,
      PickupStackOnGameUseCase pickupStackOnGameUseCase) {
    this.playOnGameUseCase = playOnGameUseCase;
    this.pickupStackOnGameUseCase = pickupStackOnGameUseCase;
  }

  @Override
  public void parse(List<String> arguments) {
    final var cardsStrings = parseCardStrings(arguments);
    this.gameId = arguments.get(2);
    this.playerId = arguments.get(4);
    if (cardsStrings.getFirst().equalsIgnoreCase("pickup")) {
      this.cards = Collections.emptyList();
    } else {
      this.cards = cardsStrings;
    }
  }

  private static List<String> parseCardStrings(List<String> arguments) {
    return arguments.subList(6, arguments.size()).stream()
        .map(c -> Arrays.stream(c.split(",")).toList())
        .flatMap(Collection::stream)
        .toList();
  }

  @Override
  public boolean isApplicable(List<String> arguments) {
    return arguments.size() >= 7 && arguments.getFirst().equals("on") && arguments.get(1)
        .equals("game");
  }

  @Override
  public void invoke(PrintStream out) {
    final boolean pickup = cards.isEmpty();
    if (pickup) {
      pickupStackOnGameUseCase.pickup(gameId, playerId);
      out.printf("player %s picked up stack on game %s%n", playerId, gameId);
    } else {
      playOnGameUseCase.play(gameId, playerId, cards);
      out.printf("player %s lays %s on game %s%n", playerId, cards, gameId);
    }
  }
}
