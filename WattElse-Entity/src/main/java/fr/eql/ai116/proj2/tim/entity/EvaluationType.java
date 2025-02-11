package fr.eql.ai116.proj2.tim.entity;
// Type Avis
public enum EvaluationType {

    CHARGING_SPEED_AND_EFFICIENCY("Vitesse de charge et efficacité"),
    CONSISTENT_CHARGING("Charge constante"),
    UPTIME_AND_AVAILABILITY("Disponibilité et temps de fonctionnement"),
    EASE_OF_USE("Facilité d'utilisation"),
    ACCESSIBILITY("Accessibilité"),
    FAIR_PRICING("Tarification équitable"),
    VEHICLE_COMPATIBILITY("Compatibilité des véhicules"),
    CONNECTOR_AVAILABILITY("Disponibilité des connecteurs"),
    PHYSICAL_ACCESSIBILITY("Accessibilité physique"),
    ELECTRICAL_SAFETY("Sécurité électrique"),
    ENERGY_EFFICIENCY("Efficacité énergétique");

    private final String label;

    EvaluationType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
