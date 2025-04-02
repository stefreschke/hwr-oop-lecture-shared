package hwr.oop.huzur.tests.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hwr.oop.huzur.application.PickupStackOnGameService;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.layouts.Layout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PickupUseCaseTest {

  @Mock
  SaveGamePort saveGamePort;

  @Mock
  LoadGamePort loadGamePort;

  @InjectMocks
  PickupStackOnGameService pickupStackOnGameService;

  @Captor
  ArgumentCaptor<Game> gameArgumentCaptor;

  @Test
  void pickupOnLayout_GameLoadedById_NewGameStateSaved() {
    // given
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var converter = new CardConverter();
    when(loadGamePort.loadById("1337")).thenReturn(Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .trump(Color.CLUBS)
        .layout(Layout.initial(3, alpha, converter.parseCards("H7,S7,D9")))
        .turn(beta)
        .emptyDeck()
        .build());
    // when
    pickupStackOnGameService.pickup("1337", "beta");
    // then
    verify(loadGamePort).loadById("1337");
    verify(saveGamePort).save(gameArgumentCaptor.capture());
    final var savedGame = gameArgumentCaptor.getValue();
    assertThat(savedGame)
        .isNotNull()
        .matches(g -> g.turn().id().equals("gamma"), "gamma is player")
        .matches(g -> g.currentLayout().isEmpty(), "no layout")
        .matches(g -> g.handOf(beta).numberOfCards() == 10, "beta has 10 cards");
  }

  @Test
  void secondConstructor_DoesNotThrowException() {
    assertDoesNotThrow(() -> new PickupStackOnGameService(
        new TestDoubleRepository(saveGamePort, loadGamePort)
    ));
  }

}
