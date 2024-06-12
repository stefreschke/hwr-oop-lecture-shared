package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.in.GameStateQuery.GameStateDto;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Supplier;

public final class GameQueryCommand implements MutableCommand {

  private final Supplier<GameStateQuery> gameStateQuery;
  private String gameId;

  public GameQueryCommand(Supplier<GameStateQuery> gameStateQuery) {
    this.gameStateQuery = gameStateQuery;
  }

  @Override
  public String description() {
    return "on game {} state - display game state";
  }

  @Override
  public void parse(List<String> arguments) {
    this.gameId = arguments.get(2);
  }

  @Override
  public boolean isApplicable(List<String> arguments) {
    return arguments.size() >= 4
        && arguments.get(0).equals("on")
        && arguments.get(1).equals("game")
        && arguments.get(3).equals("state");
  }

  @Override
  public void invoke(PrintStream out) {
    final GameStateDto state = gameStateQuery.get().gameStateOf(gameId);

    out.println("turn: " + state.turn());
    out.println("trump: " + state.trump().toUpperCase());
    out.println("hand: " + joinStringsByComma(state.cardsInHand()));
    out.println("layout: " + joinStringsByComma(state.layoutCards()));
    out.println("cards to pickup from layout (" + state.cardsToDrawOnPickup().size() + "): "
        + joinStringsByComma(state.cardsToDrawOnPickup()));
    out.println(
        "remaining Deck: " + (state.remainingCardsInDeck() > 0 ? state.remainingCardsInDeck()
            : "empty"));
    state.remainingCardsInHand()
        .forEach((k, v) -> out.println(k + " has " + v + " cards remaining"));
  }

  public String joinStringsByComma(List<String> cards) {
    return cards.stream().reduce((a, b) -> a.trim() + "," + b.trim()).orElse("");
  }
}
