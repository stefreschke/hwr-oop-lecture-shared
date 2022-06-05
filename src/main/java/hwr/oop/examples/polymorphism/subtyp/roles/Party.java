package hwr.oop.examples.polymorphism.subtyp.roles;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Party {
    private final Alcohol alcohol;
    private final Collection<PartyGuest> guests;

    public Party(Alcohol alcohol, PartyGuest... guests) {
        this.alcohol = alcohol;
        this.guests = Arrays.asList(guests);
    }

    public void takePlace() {
        Map<PartyGuest, Alcohol> alcPerPartyGuest = alcohol.splitFor(guests);
        alcPerPartyGuest.forEach((g, a) -> g.consume(a));


    }
}
