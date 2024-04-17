package hwr.oop.huzur.domain;

import java.util.Arrays;
import java.util.List;

public enum Sign {
  THREE(7), ACE(9), SEVEN(0), EIGHT(1), NINE(2), TEN(3), JACK(4), QUEEN(5), KING(6), TWO(8);

  private final int strength;

  Sign(int strength) {

    this.strength = strength;
  }

  public static List<Sign> all() {
    return Arrays.asList(Sign.values());
  }

  public int strength() {
    return strength;
  }
}
