package hwr.oop.huzur.persistence;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CsvRow {

  private final String id;
  private final String turnString;
  private final String trumpString;
  private final String playersAndCardsString;
  private final String deckString;
  private final String layoutString;
  private final CardConverter converter;

  public CsvRow(String idString, String turnString, String trumpString,
      String playersAndCardsString,
      String deckString, String layoutString) {
    this.id = idString;
    this.turnString = turnString;
    this.trumpString = trumpString;
    this.playersAndCardsString = playersAndCardsString;
    this.deckString = deckString;
    this.layoutString = layoutString;
    this.converter = new CardConverter();
  }

  public static CsvRow fromCsvFileLine(String row) {
    final var parts = Arrays.asList(row.split(";"));
    final var mutable = new ArrayList<>(parts);
    while (mutable.size() <= 6) {
      mutable.add("");
    }
    return new CsvRow(mutable.get(0), mutable.get(1), mutable.get(2), mutable.get(3),
        mutable.get(4), mutable.get(5));
  }

  public static CsvRow fromGame(Game game) {
    final var remainingDeckString = parseRemainingDeckStrings(game);
    final var playerAndCardString = parsePlayersAndCardsString(game);
    final String layoutString = parseLayoutString(game);
    return new CsvRow(
        game.id().value(),
        game.turn().id(),
        game.trump().toString().substring(0, 1),
        playerAndCardString,
        remainingDeckString,
        layoutString
    );
  }

  private static String parseRemainingDeckStrings(Game game) {
    return game.remainingDeck()
        .map(Card::shortHandle)
        .reduce(SeparatedBy.comma())
        .orElse("");
  }

  private static String parsePlayersAndCardsString(Game game) {
    final var playersAndCards = game.players().collect(Collectors.toMap(
        Function.identity(),
        p -> game.handOf(p).cards()
            .map(Card::shortHandle)
            .reduce(SeparatedBy.comma())
            .orElseThrow()
    ));
    return playersAndCards.entrySet().stream()
        .map(e -> e.getKey().id() + "-" + e.getValue())
        .reduce(SeparatedBy.colon())
        .orElseThrow();
  }

  private static String parseLayoutString(Game game) {
    final var optional = game.currentLayout();
    if (optional.isPresent()) {
      final var layout = optional.get();
      final List<String> list = parseLayoutLists(layout);
      return list.stream().reduce(SeparatedBy.underscore()).orElseThrow();
    } else {
      return "";
    }
  }

  private static List<String> parseLayoutLists(Layout layout) {
    final List<String> list = new LinkedList<>();
    list.addFirst(parseLayoutToString(layout));
    Layout l = layout;
    while (l.previous().isPresent()) {
      final var previousL = l.previous().orElseThrow();
      final var layoutString = parseLayoutToString(previousL);
      list.addFirst(layoutString);
      l = previousL;
    }
    return list;
  }

  private static String parseLayoutToString(Layout previousL) {
    final var cardsString = previousL.cards()
        .map(Card::shortHandle)
        .reduce(SeparatedBy.comma())
        .orElseThrow();
    final var player = previousL.player();
    final var playerString = player.id();
    return playerString + "-" + cardsString;
  }

  public Game asGame() {
    final var trump = converter.convertColor(trumpString);
    final var playerCardMap = parsePlayersAndCards();
    final var playerCount = playerCardMap.keySet().size();
    final var builder = Game.newBuilder().id(id);
    playerCardMap.forEach((k, v) -> builder.player(k).hasCards(v));
    if (layoutString.isBlank()) {
      builder.noLayout();
    } else {
      final var parsedLayouts = parseLayouts(playerCount, trump);
      builder.layout(parsedLayouts);
    }
    if (deckString.isBlank()) {
      builder.emptyDeck();
    } else {
      builder.deck(converter.parseCards(deckString));
    }
    return builder
        .trump(trump)
        .turn(Player.id(turnString))
        .build();
  }

  private Map<Player, List<Card>> parsePlayersAndCards() {
    final var playerCardStrings = Arrays.stream(playersAndCardsString.split(":"));
    return playerCardStrings
        .map(s -> Arrays.asList(s.split("-")))
        .collect(Collectors.toMap(
            i -> Player.id(i.getFirst()),
            i -> Arrays.stream(i.getLast().split(","))
                .map(converter::convert)
                .toList()
        ));
  }

  private Layout parseLayouts(int playerCount, Color trump) {
    final var layoutStrings = layoutString.split("_");
    Layout layout = null;
    for (var layoutPart : layoutStrings) {
      if (layout == null) {
        layout = parseFirstLayout(layoutPart, playerCount);
      } else {
        layout = parseSubsequentLayout(layout, layoutPart, trump);
      }
    }
    return layout;
  }

  private Layout parseFirstLayout(String layoutStrings, int numberOfPlayers) {
    final var parts = Arrays.asList(layoutStrings.split("-"));
    final var player = Player.id(parts.getFirst());
    final var cards = converter.parseCards(parts.getLast());
    return Layout.initial(numberOfPlayers, player, cards);
  }

  private Layout parseSubsequentLayout(Layout previous, String layoutString, Color trump) {
    final var parts = Arrays.asList(layoutString.split("-"));
    final var player = Player.id(parts.getFirst());
    final var cards = converter.parseCards(parts.getLast());
    return Layout
        .answering(previous)
        .with(player, cards)
        .given(() -> trump);
  }

  @Override
  public String toString() {
    return id + ";" + turnString + ";" + trumpString + ";" +
        playersAndCardsString + ";" + deckString + ";" + layoutString;
  }
}
