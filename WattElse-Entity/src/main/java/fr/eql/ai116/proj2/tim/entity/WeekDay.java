package fr.eql.ai116.proj2.tim.entity;
// jour-semaine
public enum WeekDay {


    MONDAY("Lundi"),
    TUESDAY("Mardi"),
    WEDNESDAY("Mercredi"),
    THURSDAY("Jeudi"),
    FRIDAY("Vendredi"),
    SATURDAY("Samedi"),
    SUNDAY("Dimanche");


    private final String label;

    WeekDay(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
