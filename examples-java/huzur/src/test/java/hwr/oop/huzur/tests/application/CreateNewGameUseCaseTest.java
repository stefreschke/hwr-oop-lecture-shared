package hwr.oop.huzur.tests.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import hwr.oop.huzur.application.NewGameService;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateNewGameUseCaseTest {

  @Mock
  SaveGamePort saveGamePort;

  @InjectMocks
  NewGameService newGameService;

  @Captor
  ArgumentCaptor<Game> gameArgumentCaptor;

  @Test
  void idProvided_CorrectGameSaved() {
    // when
    newGameService.newGame("1337", "hearts", List.of("alpha", "beta"));
    // then
    verify(saveGamePort).save(gameArgumentCaptor.capture());
    final var savedGame = gameArgumentCaptor.getValue();
    assertThat(savedGame)
        .isNotNull()
        .matches(g -> g.id().value().equals("1337"), "id is 1337");
  }

  @Test
  void trumpHearts_PlayersAlphaBeta_CorrectGameSaved() {
    // when
    newGameService.newGame("hearts", List.of("alpha", "beta"));
    // then
    verify(saveGamePort).save(gameArgumentCaptor.capture());
    final var savedGame = gameArgumentCaptor.getValue();
    final var expectedPlayers = List.of(Player.id("alpha"), Player.id("beta"));
    assertThat(savedGame)
        .isNotNull()
        .matches(g -> g.trump() == Color.HEARTS, "trump is HEARTS")
        .matches(g -> g.players().toList().size() == 2, "#players is 2")
        .matches(g -> g.players().toList().containsAll(expectedPlayers),
            "alpha and beta are players");
  }

  @Test
  void trumpClubs_PlayersAlphaBetaGammaDelta_CorrectGameSaved() {
    // when
    newGameService.newGame("clubs", List.of("alpha", "beta", "gamma", "delta"));
    // then
    verify(saveGamePort).save(gameArgumentCaptor.capture());
    final var savedGame = gameArgumentCaptor.getValue();
    final var expectedPlayers = List.of(Player.id("alpha"), Player.id("beta"), Player.id("gamma"),
        Player.id("delta"));
    assertThat(savedGame)
        .isNotNull()
        .matches(g -> g.trump() == Color.CLUBS, "trump is CLUBS")
        .matches(g -> g.players().toList().size() == 4, "#players is 4")
        .matches(g -> g.players().toList().containsAll(expectedPlayers),
            "alpha, beta, gamma, delta are players");
  }

}
