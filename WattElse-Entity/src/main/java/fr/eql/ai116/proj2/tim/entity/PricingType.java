package fr.eql.ai116.proj2.tim.entity;
// type tarification
public enum PricingType {
    PRICING_PER_HOUR("Tarif à l'heure"),
    PRICING_PER_KWH("Tarif au kWh"),
    PEAK_PRICE_AND_OFF_PEAK_PRICE("Tarif de pointe et tarif hors pointe");

    private final String label;

    PricingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
