package hwr.oop.examples.polymorphism.subtyp;

import hwr.oop.examples.polymorphism.subtyp.roles.Alcohol;
import hwr.oop.examples.polymorphism.subtyp.roles.Employer;
import hwr.oop.examples.polymorphism.subtyp.roles.Human;
import hwr.oop.examples.polymorphism.subtyp.roles.Money;
import hwr.oop.examples.polymorphism.subtyp.roles.Party;
import hwr.oop.examples.polymorphism.subtyp.roles.PartyGuest;
import hwr.oop.examples.polymorphism.subtyp.roles.Student;
import hwr.oop.examples.polymorphism.subtyp.roles.Teacher;
import hwr.oop.examples.polymorphism.subtyp.roles.Worker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceAsRolesTest {

    @Test
    void humanAsStudent() {
        // given
        Student studentA = new Human();
        Student studentB = new Human();
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
        Worker workerA = new Human();
        Worker workerB = new Human();
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
        PartyGuest humanA = new Human();
        PartyGuest humanB = new Human();
        Alcohol alcohol = Alcohol.aLot();
        Party party = new Party(alcohol, humanA, humanB);
        // when
        party.takePlace();
        // then
        int alcoholA = humanA.bloodAlcohol().amount();
        int alcoholB = humanB.bloodAlcohol().amount();
        assertThat(alcoholA).isGreaterThan(9000);
        assertThat(alcoholB).isGreaterThan(9000);
    }
}
