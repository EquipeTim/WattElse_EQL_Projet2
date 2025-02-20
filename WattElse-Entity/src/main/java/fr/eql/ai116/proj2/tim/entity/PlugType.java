package fr.eql.ai116.proj2.tim.entity;

// Type de prise
public enum PlugType {
    NACS("NACS (North America)"),
    TYPE_2("Type 2 (Mennekes)"),
    TESLA_SUPERCHARGER("Tesla Supercharger"),
    CCS("CCS"),
    CHADEMO("CHAdeMO"),
    GB_T("GB/T (China)"),
    AUTRE("Autre");

    private final String displayName;

    PlugType(String displayName) {
        this.displayName = displayName;
    }

    public String getLabel() {
        return displayName;
    }
}
