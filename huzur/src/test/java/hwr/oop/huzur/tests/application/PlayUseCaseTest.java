package hwr.oop.huzur.tests.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hwr.oop.huzur.application.PlayOnGameService;
import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayUseCaseTest {

  @Mock
  SaveGamePort saveGamePort;

  @Mock
  LoadGamePort loadGamePort;

  @InjectMocks
  PlayOnGameService playOnGameService;

  @Captor
  ArgumentCaptor<Game> gameArgumentCaptor;

  @Test
  void playThreeCardLayoutWithValidCards_GameLoadedById_NewGameStateSaved() {
    // given
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var converter = new CardConverter();
    when(loadGamePort.loadById("1337")).thenReturn(Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .trump(Color.CLUBS)
        .turn(alpha)
        .noLayout()
        .emptyDeck()
        .build());
    // when
    playOnGameService.play("1337", "alpha", List.of("H7", "S7", "D9"));
    // then
    verify(loadGamePort).loadById("1337");
    verify(saveGamePort).save(gameArgumentCaptor.capture());
    final var savedGame = gameArgumentCaptor.getValue();
    assertThat(savedGame)
        .isNotNull()
        .matches(g -> g.turn().id().equals("beta"), "beta is player")
        .matches(g -> g.currentLayout().orElseThrow().hiddenCards().findAny().isEmpty(),
            "no hidden cards in layout")
        .matches(g -> g.currentLayout().orElseThrow().numberOfCards() == 3,
            "three cards in layout");
  }

  @Test
  void secondConstructor_DoesNotThrowException() {
    assertDoesNotThrow(() -> new PlayOnGameService(
        new TestDoubleRepository(saveGamePort, loadGamePort)
    ));
  }
}
