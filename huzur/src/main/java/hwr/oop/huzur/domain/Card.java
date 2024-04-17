package hwr.oop.huzur.domain;

/**
 * Cards are <i>immutable</i> and should be usable as Key in {@link java.util.Map}. Thus, any
 * implementation must implement {@link #equals(Object)} and {@link #hashCode()} accordingly.
 */
public interface Card {

  boolean hasColor(Color color);

  boolean hasSign(Sign sign);

  int strength();

  boolean isAlwaysTrump();

  boolean sameColorAs(Card other);

  boolean sameSignAs(Card other);
}
