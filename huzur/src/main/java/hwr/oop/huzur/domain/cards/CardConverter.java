package hwr.oop.huzur.domain.cards;

import hwr.oop.huzur.domain.Card;
import hwr.oop.huzur.domain.Color;
import hwr.oop.huzur.domain.Sign;
import java.util.Arrays;
import java.util.List;

public class CardConverter {

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

  public Color convertColor(String string) {
    return switch (string) {
      case "C" -> Color.CLUBS;
      case "S" -> Color.SPADES;
      case "H" -> Color.HEARTS;
      case "D" -> Color.DIAMONDS;
      default -> throw new UnknownColorException(string);
    };
  }

  private Sign convertSign(String string) {
    return switch (string) {
      case "7" -> Sign.SEVEN;
      case "8" -> Sign.EIGHT;
      case "9" -> Sign.NINE;
      case "T" -> Sign.TEN;
      case "J" -> Sign.JACK;
      case "Q" -> Sign.QUEEN;
      case "K" -> Sign.KING;
      case "3" -> Sign.THREE;
      case "2" -> Sign.TWO;
      case "A" -> Sign.ACE;
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
