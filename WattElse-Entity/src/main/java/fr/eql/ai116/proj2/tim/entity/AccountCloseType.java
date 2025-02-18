package fr.eql.ai116.proj2.tim.entity;
// Motif fermeture
public enum AccountCloseType {
    NO_LONGER_OWNING_AN_ELECTRIC_CAR("Je ne posséde plus de voiture électrique"),
    SWITCHING_TO_A_DIFFERENT_CHARGING_NETWORK("Je passe à un autre réseau de recharge"),
    DISSATISFACTION_WITH_SERVICE("Insatisfaction vis-à-vis du service"),
    HIGH_CHARGING_COSTS("Coût de recharge élevé"),
    TECHNICAL_ISSUES("Problèmes techniques"),
    MOVING_TO_A_NON_EV_CAR("Je passe à une voiture non électrique"),
    FINANCIAL_DIFFICULTIES("Difficultés financières"),
    AUTRE("Autre");

    private final String label;

    AccountCloseType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
