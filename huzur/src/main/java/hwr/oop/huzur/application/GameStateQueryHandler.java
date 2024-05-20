package hwr.oop.huzur.application;

import hwr.oop.huzur.application.ports.in.GameStateQuery;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.Collections;
import java.util.stream.Collectors;

public final class GameStateQueryHandler implements GameStateQuery {

  private final LoadGamePort loadGamePort;

  public GameStateQueryHandler(LoadGamePort loadGamePort) {
    this.loadGamePort = loadGamePort;
  }

  @Override
  public GameStateDto gameStateOf(String gameId) {
    final var game = loadGamePort.loadById(gameId);
    final var layoutOptional = game.currentLayout();
    final var turn = game.turn();
    return new GameStateDto(
        turn.id(),
        game.handOf(turn).cards().map(Card::shortHandle).toList(),
        layoutOptional.map(layout -> layout.cards().map(Card::shortHandle).toList())
            .orElse(Collections.emptyList()),
        layoutOptional.map(layout -> layout.allCards().map(Card::shortHandle).toList())
            .orElse(Collections.emptyList()),
        layoutOptional.map(Layout::finishedOnPlay).orElse(false),
        game.players().collect(Collectors.toMap(
            Player::id,
            p -> game.handOf(p).numberOfCards()
        )),
        game.numberOfRemainingCards(),
        game.trump().toString()
    );
  }

}
