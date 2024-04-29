package hwr.oop.huzur.domain.cards;


import java.util.Objects;

public record NormalCard(Color color, Sign sign) implements Card {

  public NormalCard {
    Objects.requireNonNull(color);
    Objects.requireNonNull(sign);
  }

  @Override
  public boolean hasColor(Color color) {
    return color == this.color;
  }

  @Override
  public boolean hasSign(Sign sign) {
    return sign == this.sign;
  }

  @Override
  public int strength() {
    return sign.strength();
  }

  @Override
  public boolean isAlwaysTrump() {
    return false;
  }

  @Override
  public boolean sameColorAs(Card other) {
    return other.hasColor(this.color);
  }

  @Override
  public boolean sameSignAs(Card other) {
    return other.hasSign(this.sign);
  }

  @Override
  public String shortHandle() {
    return "" + color.handle() + sign.handle();
  }

  @Override
  public String toString() {
    return "Card{" + sign + " of " + color + "}";
  }
}
