package fr.eql.ai116.proj2.tim.entity;
// raison annulation
public enum ReservationCancelType {
    FOUND_ANOTHER_CHARGING_LOCATION("Trouvé un autre emplacement de recharge"),
    NO_LONGER_NEED_TO_CHARGE("Plus besoin de recharger"),
    DELAY_OR_LATE_ARRIVAL("Retard ou arrivée tardive"),
    TECHNICAL_ISSUES_WITH_THE_VEHICLE("Problèmes techniques avec le véhicule"),
    CHANGE_IN_BUDGET("Changement de budget"),
    RESERVATION_MISTAKE("Erreur de réservation");

    private final String label;

    ReservationCancelType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
