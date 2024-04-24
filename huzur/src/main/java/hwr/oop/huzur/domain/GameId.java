package hwr.oop.huzur.domain;

public record GameId(String value) {

  public static GameId of(String value) {
    return new GameId(value);
  }
}
