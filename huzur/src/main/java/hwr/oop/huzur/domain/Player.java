package hwr.oop.huzur.domain;

public record Player(String id) {

  public static Player id(String id) {
    return new Player(id);
  }
}
