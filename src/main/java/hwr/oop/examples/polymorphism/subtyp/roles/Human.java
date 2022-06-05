package hwr.oop.examples.polymorphism.subtyp.roles;

public class Human implements Student, Worker, PartyGuest {
    private String lastThingStudied;
    private Money earnings;
    private Alcohol bloodAlcohol;

    public Human() {
        this.earnings = Money.of(0);
        this.bloodAlcohol = Alcohol.none();
    }

    @Override
    public boolean hasBecomeMoreSmart() {
        return lastThingStudied != null;
    }

    @Override
    public void study(String content) {
        this.lastThingStudied = content;
    }

    @Override
    public Money earnings() {
        return earnings;
    }

    @Override
    public void work(Money money) {
        earnings = earnings.plus(money);
    }

    @Override
    public Alcohol bloodAlcohol() {
        bloodAlcohol.ifOver9000(() -> System.out.println(
                "ITS OVER 9000!!!!1! (https://youtu.be/QsDDXSmGJZA?t=52)"));
        return bloodAlcohol;
    }

    @Override
    public void consume(Alcohol alcohol) {
        bloodAlcohol = alcohol;
    }
}
