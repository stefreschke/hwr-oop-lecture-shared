package hwr.oop.huzur.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public interface Layout {

  static Layout of(Player player, List<Card> cards) {
    return new InitialLayout(player, cards);
  }

  static Layout on(Layout layout, Comparator<Card> comparator, Player player, List<Card> cards) {
    return new AnswerLayout(layout, comparator, player, cards);
  }

  Player startingPlayer();

  Stream<Card> cards();

  Stream<Card> hiddenCards();

  default Stream<Card> allCards() {
    return Stream.concat(cards(), hiddenCards());
  }
}
