package hwr.oop.huzur.domain.cards;

import java.util.Arrays;
import java.util.List;

public final class CardConverter {

  public List<Card> parseCards(String cardsString) {
    return Arrays.stream(cardsString.split(","))
        .map(this::convert).toList();
  }

  public Card convert(String string) {
    if (string.length() != 2) {
      throw new InvalidCardStringFormatException(string);
    }
    return switch (string) {
      case "J1" -> Joker.first();
      case "J2" -> Joker.second();
      default -> convertNonJokerCard(string);
    };
  }

  private Card convertNonJokerCard(String string) {
    final var first = string.substring(0, 1);
    final var second = string.substring(1, 2);
    return new NormalCard(convertColor(first), convertSign(second));
  }

  public Card.Color convertColor(String string) {
    return switch (string) {
      case "C" -> Card.Color.CLUBS;
      case "S" -> Card.Color.SPADES;
      case "H" -> Card.Color.HEARTS;
      case "D" -> Card.Color.DIAMONDS;
      default -> throw new UnknownColorException(string);
    };
  }

  private Card.Sign convertSign(String string) {
    return switch (string) {
      case "7" -> Card.Sign.SEVEN;
      case "8" -> Card.Sign.EIGHT;
      case "9" -> Card.Sign.NINE;
      case "T" -> Card.Sign.TEN;
      case "J" -> Card.Sign.JACK;
      case "Q" -> Card.Sign.QUEEN;
      case "K" -> Card.Sign.KING;
      case "3" -> Card.Sign.THREE;
      case "2" -> Card.Sign.TWO;
      case "A" -> Card.Sign.ACE;
      default -> throw new UnknownSignException(string);
    };
  }

  private static class InvalidCardStringFormatException extends RuntimeException {

    public InvalidCardStringFormatException(String cardString) {
      super("Cannot convert String to Card, expected two char string, got " + cardString);
    }
  }

  private static class UnknownColorException extends RuntimeException {

    public UnknownColorException(String colorString) {
      super("Cannot convert Color, got: " + colorString);
    }
  }

  private static class UnknownSignException extends RuntimeException {

    public UnknownSignException(String signString) {
      super("Cannot convert Sign, got: " + signString);
    }
  }
}
