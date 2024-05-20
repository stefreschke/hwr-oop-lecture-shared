package hwr.oop.huzur.domain.layouts;

import hwr.oop.huzur.domain.GameCardContext;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Layouts are <i>immutable</i>.
 */
public interface Layout {

  static Layout initial(int numberOfPlayers, Player player, List<Card> cards) {
    return new InitialLayout(numberOfPlayers, player, cards);
  }

  static AnswerBuilder answering(Layout previous) {
    return new AnswerBuilder(previous);
  }

  boolean isFinished();

  int numberOfPlayers();

  Player player();

  Player startingPlayer();

  Stream<Card> cards();

  Stream<Card> hiddenCards();

  Optional<Layout> previous();

  default Stream<Card> allCards() {
    return Stream.concat(cards(), hiddenCards());
  }

  int numberOfCards();

  boolean finishedOnPlay();

  class AnswerBuilder {

    private final Layout previous;
    private Player player;
    private List<Card> cards;

    private AnswerBuilder(Layout previous) {
      this.previous = previous;
    }

    public AnswerBuilder with(Player player, List<Card> cards) {
      this.player = player;
      this.cards = cards;
      return this;
    }

    public Layout given(GameCardContext context) {
      return new AnswerLayout(previous, context, player, cards);
    }
  }

}
