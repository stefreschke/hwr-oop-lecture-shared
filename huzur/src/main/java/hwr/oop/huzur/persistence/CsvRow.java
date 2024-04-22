package hwr.oop.huzur.persistence;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record CsvRow(String id, String turnString, String trumpString, String playersAndCardsString,
                     String deckString, String layoutString) {

  public static CsvRow fromCsvFileLine(String row) {
    final var parts = Arrays.asList(row.split(";"));
    if (parts.size() == 5) {
      return new CsvRow(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4), "");
    } else {
      return new CsvRow(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4),
          parts.get(5));
    }
  }

  public Game asGame() {
    final var converter = new CardConverter();
    final var playerCardStrings = Arrays.stream(playersAndCardsString.split(":"));
    final var playerCardMap = playerCardStrings
        .map(s -> Arrays.asList(s.split("-")))
        .collect(Collectors.toMap(
            i -> Player.id(i.getFirst()),
            i -> Arrays.stream(i.getLast().split(","))
                .map(converter::convert)
                .toList()
        ));
    final var builder = Game.newBuilder();
    if (layoutString.isBlank()) {
      builder.noLayout();
    } else {
      final var layoutStrings = Arrays.asList(layoutString.split("_"));
      final var layout = parseFirstLayout(layoutStrings, converter, playerCardMap);
      
      builder.layout(layout);
    }
    playerCardMap.forEach((k, v) -> builder.player(k).hasCards(v));
    if (deckString.isBlank()) {
      builder.emptyDeck();
    } else {
      builder.deck(converter.parseCards(deckString));
    }
    return builder
        .trump(converter.convertColor(trumpString))
        .turn(Player.id(turnString))
        .build();
  }

  private Layout parseFirstLayout(
      List<String> layoutStrings,
      CardConverter converter,
      Map<Player, List<Card>> playerCardMap
  ) {
    final var parts = Arrays.asList(layoutStrings.getFirst().split("-"));
    final var layoutPlayer = Player.id(parts.getFirst());
    final var cardsInLayout = converter.parseCards(parts.getLast());
    return Layout.initial(
        playerCardMap.keySet().size(),
        layoutPlayer,
        cardsInLayout
    );
  }
}
