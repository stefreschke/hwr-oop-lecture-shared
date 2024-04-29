package hwr.oop.examples.cli;

import java.util.List;

public class Main {

  @SuppressWarnings("java:S106")
  public static void main(String[] args) {
    final var cli = new OutputOnlyCli(System.out);
    cli.handle(List.of(args));
  }

}
