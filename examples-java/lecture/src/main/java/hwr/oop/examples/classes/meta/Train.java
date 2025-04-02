package hwr.oop.examples.classes.meta;

public class Train implements Identifiable {

  private final String id;

  public Train(String id) {
    this.id = id;
  }

  @Override
  public String identifier() {
    return id;
  }
}
