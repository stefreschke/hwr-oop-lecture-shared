package hwr.oop.huzur.domain.cards;

import java.util.Arrays;
import java.util.List;

/**
 * Cards are <i>immutable</i> and should be usable as Key in {@link java.util.Map}. Thus, any
 * implementation must implement {@link Object#equals(Object)} and {@link Object#hashCode()}
 * accordingly.
 */
public interface Card {

  boolean hasColor(Color color);

  boolean hasSign(Sign sign);

  int strength();

  boolean isAlwaysTrump();

  boolean sameColorAs(Card other);

  boolean sameSignAs(Card other);

  enum Sign {
    THREE(7), ACE(9), SEVEN(0), EIGHT(1), NINE(2), TEN(3), JACK(4), QUEEN(5), KING(6), TWO(8);

    private final int strength;

    Sign(int strength) {

      this.strength = strength;
    }

    public static List<Sign> all() {
      return Arrays.asList(Sign.values());
    }

    public int strength() {
      return strength;
    }
  }

  enum Color {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    public static List<Color> all() {
      return Arrays.asList(Color.values());
    }

  }
}
