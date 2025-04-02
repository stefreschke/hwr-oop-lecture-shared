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

  String shortHandle();

  enum Sign {
    THREE(7, '3'), ACE(9, 'A'), SEVEN(0, '7'), EIGHT(1, '8'), NINE(2, '9'), TEN(3, 'T'),
    JACK(4, 'J'), QUEEN(5, 'Q'), KING(6, 'K'), TWO(8, '2');

    private final int strength;
    private final char handle;

    Sign(int strength, char handle) {
      this.strength = strength;
      this.handle = handle;
    }

    public static List<Sign> all() {
      return Arrays.asList(Sign.values());
    }

    public int strength() {
      return strength;
    }

    public char handle() {
      return handle;
    }
  }

  enum Color {
    SPADES('S'), HEARTS('H'), DIAMONDS('D'), CLUBS('C');

    private final char handle;

    public static List<Color> all() {
      return Arrays.asList(Color.values());
    }

    Color(char handle) {
      this.handle = handle;
    }

    public char handle() {
      return handle;
    }
  }
}
