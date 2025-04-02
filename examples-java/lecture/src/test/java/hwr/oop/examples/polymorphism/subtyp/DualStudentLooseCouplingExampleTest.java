package hwr.oop.examples.polymorphism.subtyp;

import static org.assertj.core.api.Assertions.assertThat;

import hwr.oop.examples.polymorphism.subtyp.roles.Alcohol;
import hwr.oop.examples.polymorphism.subtyp.roles.Employer;
import hwr.oop.examples.polymorphism.subtyp.roles.DualStudent;
import hwr.oop.examples.polymorphism.subtyp.roles.Money;
import hwr.oop.examples.polymorphism.subtyp.roles.Party;
import hwr.oop.examples.polymorphism.subtyp.roles.PartyGuest;
import hwr.oop.examples.polymorphism.subtyp.roles.Student;
import hwr.oop.examples.polymorphism.subtyp.roles.Teacher;
import hwr.oop.examples.polymorphism.subtyp.roles.Worker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lose Kopplung: Duale Student*in")
class DualStudentLooseCouplingExampleTest {

  @Test
  void humanAsStudent() {
    // given
    Student studentA = new DualStudent();
    Student studentB = new DualStudent();
    Teacher teacher = new Teacher(studentA, studentB);
    // when
    teacher.teach();
    // then
    boolean isSmarterA = studentA.hasBecomeMoreSmart();
    boolean isSmarterB = studentB.hasBecomeMoreSmart();
    assertThat(isSmarterA).isTrue();
    assertThat(isSmarterB).isTrue();
  }

  @Test
  void humanAsWorker() {
    // given
    Worker workerA = new DualStudent();
    Worker workerB = new DualStudent();
    Employer employer = new Employer(workerA, workerB);
    // when
    employer.forceWorkersToWorkFor(Money.of(5));
    // then
    double earningsA = workerA.earnings().asDouble();
    double earningsB = workerB.earnings().asDouble();
    int workDone = employer.amountOfWorkDone();
    assertThat(earningsA).isEqualTo(2.5);
    assertThat(earningsB).isEqualTo(2.5);
    assertThat(workDone).isEqualTo(2);
  }

  @Test
  void humanAsPartyGuest() {
    // given
    PartyGuest partyGuestA = new DualStudent();
    PartyGuest partyGuestB = new DualStudent();
    Alcohol alcohol = Alcohol.aLot();
    Party party = new Party(alcohol, partyGuestA, partyGuestB);
    // when
    party.takePlace();
    // then
    int alcoholA = partyGuestA.bloodAlcohol().amount();
    int alcoholB = partyGuestB.bloodAlcohol().amount();
    assertThat(alcoholA).isGreaterThan(9000);
    assertThat(alcoholB).isGreaterThan(9000);
  }
}
