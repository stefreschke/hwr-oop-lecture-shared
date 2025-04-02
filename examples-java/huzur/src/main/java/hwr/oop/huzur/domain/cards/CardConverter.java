package hwr.oop.huzur.domain.cards;

import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CardConverter {

  private final Map<String, Card> handleMap;
  private final Map<Character, Color> colorHandleMap;

  public CardConverter() {
    this.handleMap = createMap();
    this.colorHandleMap = createColorHandleMap();
  }

  private Map<Character, Color> createColorHandleMap() {
    return Color.all().stream().collect(Collectors.toMap(
        Color::handle,
        Function.identity()
    ));
  }

  private Map<String, Card> createMap() {
    final var cardFactory = new CardFactory();
    return cardFactory.createAllCards().collect(Collectors.toMap(
        Card::shortHandle,
        Function.identity()
    ));
  }

  public List<Card> parseCards(String cardsString) {
    return Arrays.stream(cardsString.split(","))
        .map(this::convert).toList();
  }

  public Card convert(String string) {
    if (string.length() != 2) {
      throw new InvalidCardStringFormatException(string);
    }
    if (handleMap.containsKey(string)) {
      return handleMap.get(string);
    } else {
      throw new UnknownCardException(string);
    }
  }

  public Color convertColor(String string) {
    final char asChar = string.charAt(0);
    if (colorHandleMap.containsKey(asChar)) {
      return colorHandleMap.get(asChar);
    } else {
      throw new UnknownColorException(string);
    }
  }

  private static class InvalidCardStringFormatException extends RuntimeException {

    public InvalidCardStringFormatException(String cardString) {
      super("Cannot convert String to Card, expected two char string, got " + cardString);
    }
  }

  private static class UnknownCardException extends RuntimeException {

    public UnknownCardException(String colorString) {
      super("Cannot convert Card, got: " + colorString);
    }
  }

  private static class UnknownColorException extends RuntimeException {

    public UnknownColorException(String colorString) {
      super("Cannot convert String to Color, expected [CDHS], got " + colorString);
    }
  }
}
