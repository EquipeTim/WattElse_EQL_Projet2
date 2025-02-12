package fr.eql.ai116.proj2.tim.entity;
// raison de cloture (borne)
public enum StationClosingType {
    MAINTENANCE("Maintenance"),
    TECHNICAL_ISSUES("Problèmes techniques"),
    POWER_OUTAGE("Panne de courant"),
    END_OF_OPERATING_HOURS("Fin des heures d'ouverture"),
    EMERGENCY_SHUTDOWN("Arrêt d'urgence"),
    WEATHER_CONDITIONS("Conditions_météorologiques");

    private final String label;

    StationClosingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
