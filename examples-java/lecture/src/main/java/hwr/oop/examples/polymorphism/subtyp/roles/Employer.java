package hwr.oop.examples.polymorphism.subtyp.roles;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Employer {

  private final Collection<Worker> workers;
  private int workDone;

  public Employer(Worker... workers) {
    this.workers = Arrays.asList(workers);
    workDone = 0;
  }

  public void forceWorkersToWorkFor(Money of) {
    Map<Worker, Money> moneyForWorkers = of.splitFor(workers);
    moneyForWorkers.forEach((w, m) -> {
      w.work(m);
      workDone++;
    });
  }

  public int amountOfWorkDone() {
    return workDone;
  }
}
