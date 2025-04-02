package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.PickupStackOnGameUseCase;
import hwr.oop.huzur.application.ports.in.PlayOnGameUseCase;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class PlayOnGameCommand implements MutableCommand {

  private final Supplier<PlayOnGameUseCase> playOnGameUseCase;
  private final Supplier<PickupStackOnGameUseCase> pickupStackOnGameUseCase;
  private String gameId;
  private String playerId;
  private List<String> cards;

  public PlayOnGameCommand(Supplier<PlayOnGameUseCase> playOnGameUseCase,
      Supplier<PickupStackOnGameUseCase> pickupStackOnGameUseCase) {
    this.playOnGameUseCase = playOnGameUseCase;
    this.pickupStackOnGameUseCase = pickupStackOnGameUseCase;
  }

  @Override
  public String description() {
    return "on game {} player {} play/lay/pick {} - Advance game by setting cards";
  }

  @Override
  public void parse(List<String> arguments) {
    this.gameId = arguments.get(2);
    this.playerId = arguments.get(4);
    final String playType = arguments.get(5);
    if (playType.startsWith("play") || playType.startsWith("lay")) {
      this.cards = parseCardStrings(arguments);
    } else {
      this.cards = Collections.emptyList();
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
        .equals("game") && arguments.get(3).equals("player") && (arguments.get(5).equals("lays")
        || arguments.get(5).equals("plays") || arguments.get(5).equals("picks"));
  }

  @Override
  public void invoke(PrintStream out) {
    final boolean pickup = cards.isEmpty();
    if (pickup) {
      pickupStackOnGameUseCase.get().pickup(gameId, playerId);
      out.printf("player %s picked up stack on game %s%n", playerId, gameId);
    } else {
      playOnGameUseCase.get().play(gameId, playerId, cards);
      out.printf("player %s lays %s on game %s%n", playerId, cards, gameId);
    }
  }
}
