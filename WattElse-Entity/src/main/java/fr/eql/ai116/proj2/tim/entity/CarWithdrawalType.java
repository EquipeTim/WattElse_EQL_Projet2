package fr.eql.ai116.proj2.tim.entity;
// Motif retraction vehicule
public enum CarWithdrawalType {
    CAR_AVAILABILITY_ISSUES("Problèmes de disponibilité du véhicule"),
    USER_DISSATISFACTION("Insatisfaction de l'utilisateur"),
    CAR_TRANSFER("Transfert de véhicule"),
    TECHNICAL_OR_SYSTEM_ISSUES("Problèmes techniques ou système"),
    CAR_DAMAGE_OR_ACCIDENT("Dégâts ou accident du véhicule"),
    EXPIRED_REGISTRATION_OR_INSURANCE("Inscription ou assurance expirée"),
    CAR_REPLACEMENT_OR_UPGRADING("Remplacement ou mise à niveau du véhicule"),
    OTHER("Autre");

    private final String label;

    CarWithdrawalType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
