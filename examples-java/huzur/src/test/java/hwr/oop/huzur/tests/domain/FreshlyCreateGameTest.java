package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Game.CannotPickupException;
import hwr.oop.huzur.domain.Game.WrongPlayerException;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FreshlyCreateGameTest {

  private Player first;
  private Player second;
  private Game randomGame;

  @BeforeEach
  void setUp() {
    first = Player.id("first");
    second = Player.id("second");
    randomGame = Game
        .fresh("1337", Color.CLUBS)
        .playedBy(first, second);
  }

  @Test
  void isGameOver_IsFalse() {
    final var gameIsOver = randomGame.gameIsOver();
    assertThat(gameIsOver).isFalse();
  }

  @Test
  void noWinner() {
    final var winnerOptional = randomGame.winner();
    assertThat(winnerOptional).isEmpty();
  }

  @Test
  void turn_FirstPlayersTurn() {
    final var turn = randomGame.turn();
    assertThat(turn).isEqualTo(first);
  }

  @Test
  @ErrorHandlingTag
  void firstPicksUp_ThrowsException_BecauseNoLayout() {
    assertThatThrownBy(() -> randomGame.pickup(first))
        .isInstanceOf(CannotPickupException.class)
        .hasMessageContainingAll("Cannot pickup", "no layout");
  }

  @Test
  @ErrorHandlingTag
  void secondPicksUp_ThrowsException_BecauseNoLayout() {
    assertThatThrownBy(() -> randomGame.pickup(second))
        .isInstanceOf(WrongPlayerException.class)
        .hasMessageContainingAll(
            "Not player ", "second", "turn",
            "player ", "first", "is the next player"
        );
  }
}
