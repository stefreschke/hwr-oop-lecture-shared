package hwr.oop.huzur.persistence;

import hwr.oop.huzur.domain.FixedHandGame;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.CardConverter;
import java.util.Arrays;
import java.util.stream.Collectors;

public record CsvRow(String id, String turnString, String trumpString, String playersAndCardsString,
                     String deckString) {

  public static CsvRow fromCsvFileLine(String row) {
    final var parts = Arrays.asList(row.split(";"));
    return new CsvRow(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4));
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
    final var builder = FixedHandGame.newBuilder();
    playerCardMap.forEach((k, v) -> builder.player(k).hasCards(v));
    return builder
        .deck(converter.parseCards(deckString))
        .trump(converter.convertColor(trumpString))
        .turn(Player.id(turnString))
        .build();
  }
}
