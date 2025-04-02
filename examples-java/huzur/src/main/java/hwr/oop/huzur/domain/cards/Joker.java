package hwr.oop.huzur.domain.cards;

public record Joker(int strength) implements Card {


  public static Card first() {
    return new Joker(13337);
  }

  public static Card second() {
    return new Joker(1337);
  }


  @Override
  public boolean hasColor(Color trump) {
    return false;
  }

  @Override
  public boolean isAlwaysTrump() {
    return true;
  }

  @Override
  public boolean sameColorAs(Card other) {
    return false;
  }

  @Override
  public boolean sameSignAs(Card other) {
    return false;
  }

  @Override
  public String shortHandle() {
    return strength <= 1337 ? "J2" : "J1";
  }

  @Override
  public boolean hasSign(Sign sign) {
    return false;
  }

  @Override
  public String toString() {
    return strength <= 1337 ? "Second Joker" : "First Joker";
  }
}
