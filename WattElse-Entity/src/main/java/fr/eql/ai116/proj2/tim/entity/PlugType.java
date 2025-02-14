package fr.eql.ai116.proj2.tim.entity;
// Type de prise
public enum PlugType {
    TYPE_1_SAE_J1772("Type 1 SAE J1772"),
    TYPE_2_IEC_62196_2("Type 2 IEC 62196-2"),
    TYPE_3_IEC_62196_3("Type 3 IEC 62196-3"),
    CCS_COMBINED_CHARGING_SYSTEM("CCS Combined Charging System"),
    TESLA_SUPERCHARGER_NORTH_AMERICA_AND_EUROPE("Tesla Supercharger North America and Europe"),
    TYPE_4_IEC_62196_3("Type 4 IEC 62196-3"),
    MENNEKES_PLUG("Mennekes Plug"),
    NEMA_5_15_STANDARD_WALL_OUTLET("NEMA 5-15 Standard Wall Outlet"),
    NEMA_14_50("NEMA 14-50");

    private  String label;

    PlugType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
