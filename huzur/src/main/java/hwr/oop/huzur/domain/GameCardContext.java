package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.Comparator;

public interface GameCardContext {


  Color trump();

  default boolean isTrump(Card card) {
    return card.isAlwaysTrump() || card.hasColor(trump());
  }

  default Comparator<Card> strenghtComparator() {
    return new GameCardComparator(this);
  }

  record GameCardComparator(GameCardContext gameCardContext) implements Comparator<Card> {

    @Override
    public int compare(Card first, Card second) {
      if (gameCardContext.isTrump(first) && gameCardContext.isTrump(second)) {
        return Integer.compare(first.strength(), second.strength());
      } else {
        if (gameCardContext.isTrump(first) && !gameCardContext.isTrump(second)) {
          return 1;
        } else if (!gameCardContext.isTrump(first) && gameCardContext.isTrump(second)) {
          return -1;
        } else {
          if (first.sameColorAs(second)) {
            return Integer.compare(first.strength(), second.strength());
          } else {
            return 0;  // not weaker, not stronger, thus equal
          }
        }
      }
    }
  }
}
