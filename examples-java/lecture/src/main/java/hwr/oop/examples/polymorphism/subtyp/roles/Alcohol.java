package hwr.oop.examples.polymorphism.subtyp.roles;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Alcohol {

  private final int amount;

  public Alcohol(int amount) {
    this.amount = amount;
  }

  public static Alcohol aLot() {
    return new Alcohol(133337);
  }

  public static Alcohol none() {
    return Alcohol.of(0);
  }

  private static Alcohol of(int amount) {
    return new Alcohol(amount);
  }

  public void ifOver9000(Runnable runnable) {
    if (amount > 9000) {
      runnable.run();
    }
  }

  public <E> Map<E, Alcohol> splitFor(Collection<E> consumers) {
    Alcohol alcPerPartyGuest = Alcohol.of(amount / consumers.size());
    return consumers.stream()
        .collect(Collectors.toMap(w -> w, w -> alcPerPartyGuest));
  }

  public int amount() {
    return amount;
  }
}
