package hwr.oop.examples.polymorphism.subtyp.roles;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Money {

  private final double euros;

  public Money(double euros) {
    this.euros = euros;
  }

  public static Money of(double euros) {
    return new Money(euros);
  }

  public <E> Map<E, Money> splitFor(Collection<E> stuff) {
    Money moneyPerWorker = Money.of(euros / stuff.size());
    return stuff.stream()
        .collect(Collectors.toMap(w -> w, w -> moneyPerWorker));
  }

  public Money plus(Money money) {
    return Money.of(money.euros + euros);
  }

  public double asDouble() {
    return euros;
  }
}
