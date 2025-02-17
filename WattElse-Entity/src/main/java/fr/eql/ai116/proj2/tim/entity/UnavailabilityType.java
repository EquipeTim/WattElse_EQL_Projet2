package fr.eql.ai116.proj2.tim.entity;
// choix indisponibilite
public enum UnavailabilityType {

    HOLIDAYS ("Vacances"),
    NETWORK_OR_CONNECTION_ISSUE ("Problème de réseau ou de connexion"),
    CHARGING_STATION_OCCUPIED ("Borne occupée"),
    OVERLOAD_OR_SECURITY ("Surcharge ou sécurité"),
    PERSONAL_REASONS("Raisons personnelles");

    private final String label;

    UnavailabilityType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
