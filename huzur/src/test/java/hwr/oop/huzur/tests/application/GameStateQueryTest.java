package hwr.oop.huzur.tests.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import hwr.oop.huzur.application.GameStateQueryHandler;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.domain.FixedGameBuilder;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameStateQueryTest {

  @Mock
  LoadGamePort loadGamePort;

  @InjectMocks
  GameStateQueryHandler gameStateQueryHandler;

  @Test
  void fixture_QueryGameState_ReturnsFixedGameState() {
    // given
    final var builder = fixture();
    when(loadGamePort.loadById("1337")).thenReturn(builder.build());
    // when
    final var gameStateDto = gameStateQueryHandler.gameStateOf("1337");
    // then
    assertThat(gameStateDto)
        .isNotNull()
        .matches(g -> g.turn().equals("beta"), "beta is next player")
        .matches(g -> g.cardsInHand().containsAll(
            List.of("H3", "S3", "D3", "HK", "SK", "DK", "J2")), "betas cards are correct")
        .matches(g -> g.layoutCards().containsAll(
            List.of("H7", "S7", "D9")), "correct layout cards")
        .matches(g -> g.layoutCards().size() == 3, "3 layout cards")
        .matches(g -> g.cardsToDrawOnPickup().containsAll(List.of("H7", "S7", "D9")),
            "pickup cards are layout cards")
        .matches(g -> g.cardsToDrawOnPickup().size() == 3, "3 cards to pickup")
        .matches(g -> !g.layoutFinishedIfAnswered(), "layout not finished on answer")
        .matches(g -> g.remainingCardsInHand().get("alpha") == 4, "4 cards in alphas hand")
        .matches(g -> g.remainingCardsInHand().get("beta") == 7, "7 cards in betas hand")
        .matches(g -> g.remainingCardsInHand().get("gamma") == 7, "7 cards in gammas hand")
        .matches(g -> g.remainingCardsInDeck() == 0, "no cards in Deck");
  }

  private static FixedGameBuilder fixture() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var converter = new CardConverter();
    final List<Card> layoutCards = converter.parseCards("H7,S7,D9");
    return Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .trump(Color.CLUBS)
        .turn(beta)
        .layout(Layout.initial(3, alpha, layoutCards))
        .emptyDeck();
  }
}
