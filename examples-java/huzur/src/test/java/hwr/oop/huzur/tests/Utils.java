package hwr.oop.huzur.tests;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Card.Sign;
import hwr.oop.huzur.domain.cards.NormalCard;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Utils {

  public static Path resourceAsPath(String name) {
    final var loader = Utils.class.getClassLoader();
    final var stuff = loader.getResource(name);
    assert stuff != null;
    try {
      final URI uri = stuff.toURI();
      return Path.of(uri);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Card> allCardsOfColor(Color color) {
    return Arrays.stream(Sign.values()).map(sign -> (Card) new NormalCard(color, sign)).toList();
  }

  public static List<Card> allCardsOfSign(Sign sign) {
    return Arrays.stream(Color.values()).map(color -> (Card) new NormalCard(color, sign)).toList();
  }
}
