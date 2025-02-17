package fr.eql.ai116.proj2.tim.entity;
// model
public enum CarModel {
    MODEL_S("Model S", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    MODEL_3("Model 3", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    MODEL_X("Model X", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    MODEL_Y("Model Y", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    CYBERTRUCK("Cybertruck", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    SEMI("Semi", CarBrand.TESLA, PlugType.NACS, PlugType.TESLA_SUPERCHARGER),
    RIVIAN_R1T("R1T", CarBrand.RIVIAN, PlugType.CCS), // Verify
    RIVIAN_R1S("R1S", CarBrand.RIVIAN, PlugType.CCS), // Verify
    LUCID_AIR("Air", CarBrand.LUCID, PlugType.CCS),  // Verify
    BYTON_M_BYTE("M-Byte", CarBrand.BYTON, PlugType.GB_T), // Verify
    NIO_ES8("ES8", CarBrand.NIO, PlugType.CCS), // Verify
    NIO_ES6("ES6", CarBrand.NIO, PlugType.CCS), // Verify
    NIO_EC6("EC6", CarBrand.NIO, PlugType.CCS), // Verify
    NIO_ET7("ET7", CarBrand.NIO, PlugType.CCS), // Verify
    POLESTAR_1("1", CarBrand.POLESTAR, PlugType.CCS), // Verify
    POLESTAR_2("2", CarBrand.POLESTAR, PlugType.CCS), // Verify
    POLESTAR_3("3", CarBrand.POLESTAR, PlugType.CCS), // Verify
    FISKER_OCEAN("Ocean", CarBrand.FISKER, PlugType.CCS), // Verify
    FISKER_EMOTION("Emotion", CarBrand.FISKER, PlugType.CCS), // Verify
    BOLLINGER_B1("B1", CarBrand.BOLLINGER, PlugType.CCS), // Verify
    BOLLINGER_B2("B2", CarBrand.BOLLINGER, PlugType.CCS), // Verify
    LORDSTOWN_ENDURANCE("Endurance", CarBrand.LORDSTOWN, PlugType.CCS), // Verify
    FARADAY_FF_91("FF 91", CarBrand.FARADAY, PlugType.CCS), // Verify
    CANOO_EV("EV", CarBrand.CANOO, PlugType.CCS), // Verify
    AUDI_ETRON("e-tron", CarBrand.AUDI, PlugType.CCS),
    AUDI_ETRON_GT("e-tron GT", CarBrand.AUDI, PlugType.CCS),
    AUDI_Q4_ETRON("Q4 e-tron", CarBrand.AUDI, PlugType.CCS),
    BMW_I3("i3", CarBrand.BMW, PlugType.CCS),
    BMW_I4("i4", CarBrand.BMW, PlugType.CCS),
    BMW_IX3("iX3", CarBrand.BMW, PlugType.CCS),
    BMW_IX("iX", CarBrand.BMW, PlugType.CCS),
    VOLKSWAGEN_ID_3("ID.3", CarBrand.VOLKSWAGEN, PlugType.CCS),
    VOLKSWAGEN_ID_4("ID.4", CarBrand.VOLKSWAGEN, PlugType.CCS),
    VOLKSWAGEN_ID_BUZZ("ID.Buzz", CarBrand.VOLKSWAGEN, PlugType.CCS),
    MERCEDES_EQC("EQC", CarBrand.MERCEDES, PlugType.CCS),
    MERCEDES_EQS("EQS", CarBrand.MERCEDES, PlugType.CCS),
    MERCEDES_EQB("EQB", CarBrand.MERCEDES, PlugType.CCS),
    PORSCHE_TAYCAN("Taycan", CarBrand.PORSCHE, PlugType.CCS),
    PORSCHE_TAYCAN_CROSS_TURISMO("Taycan Cross Turismo", CarBrand.PORSCHE, PlugType.CCS),
    FORD_MUSTANG_MACH_E("Mustang Mach-E", CarBrand.FORD, PlugType.CCS),
    FORD_F_150_LIGHTNING("F-150 Lightning", CarBrand.FORD, PlugType.CCS),
    CHEVROLET_BOLT_EV("Bolt EV", CarBrand.CHEVROLET, PlugType.CCS),
    CHEVROLET_BOLT_EUV("Bolt EUV", CarBrand.CHEVROLET, PlugType.CCS),
    HYUNDAI_IONIQ_5("Ioniq 5", CarBrand.HYUNDAI, PlugType.CCS),
    HYUNDAI_KONA_ELECTRIC("Kona Electric", CarBrand.HYUNDAI, PlugType.CCS), // Verify
    KIA_SOUL_EV("Soul EV", CarBrand.KIA, PlugType.CCS), // Verify
    KIA_NIRO_EV("Niro EV", CarBrand.KIA, PlugType.CCS),  // Verify
    NISSAN_LEAF("Leaf", CarBrand.NISSAN, PlugType.CHADEMO, PlugType.TYPE_2), // Verify - Often CHAdeMO and Type 2 in Europe
    NISSAN_ARIYA("Ariya", CarBrand.NISSAN, PlugType.CCS), // Verify
    VOLVO_XC40_RECHARGE("XC40 Recharge", CarBrand.VOLVO, PlugType.CCS), // Verify
    VOLVO_C40_RECHARGE("C40 Recharge", CarBrand.VOLVO, PlugType.CCS), // Verify
    PEUGEOT_E_208("e-208", CarBrand.PEUGEOT, PlugType.CCS), // Verify
    PEUGEOT_E_2008("e-2008", CarBrand.PEUGEOT, PlugType.CCS), // Verify
    RENAULT_ZOE("Zoe", CarBrand.RENAULT, PlugType.TYPE_2), // Verify - Often Type 2
    RENAULT_MEGANE_E_TECH_ELECTRIC("Megane E-Tech Electric", CarBrand.RENAULT, PlugType.CCS), // Verify
    JAGUAR_I_PACE("I-Pace", CarBrand.JAGUAR, PlugType.CCS), // Verify
    LAND_ROVER_DEFENDER_EV("Defender EV", CarBrand.LAND_ROVER, PlugType.CCS), // Verify
    LAND_ROVER_RANGE_ROVER_EV("Range Rover EV", CarBrand.LAND_ROVER, PlugType.CCS), // Verify
    MINI_ELECTRIC("Electric", CarBrand.MINI, PlugType.CCS), // Verify
    MAZDA_MX_30("MX-30", CarBrand.MAZDA, PlugType.CCS), // Verify
    FIAT_500_ELECTRIC("500 Electric", CarBrand.FIAT, PlugType.CCS), // Verify
    HONDA_E("e", CarBrand.HONDA, PlugType.CCS), // Verify
    TOYOTA_BZ4X("bZ4X", CarBrand.TOYOTA, PlugType.CCS), // Verify
    SMART_EQ_FORTWO("EQ fortwo", CarBrand.SMART, PlugType.TYPE_2), // Verify - Often Type 2
    SUBARU_SOLTERRA("Solterra", CarBrand.SUBARU, PlugType.CCS), // Verify
    BYD_TANG_EV("Tang EV", CarBrand.BYD, PlugType.GB_T), // Verify - Often GB/T
    BYD_QIN_EV("Qin EV", CarBrand.BYD, PlugType.GB_T), // Verify - Often GB/T
    BYD_E6("e6", CarBrand.BYD, PlugType.GB_T), // Verify - Often GB/T
    XPENG_P7("P7", CarBrand.XPENG, PlugType.GB_T), // Verify - Often GB/T
    XPENG_G3("G3", CarBrand.XPENG, PlugType.GB_T), // Verify - Often GB/T
    GEELY_GEOMETRY_A("Geometry A", CarBrand.GEELY, PlugType.GB_T), // Verify - Often GB/T
    GEELY_GEOMETRY_C("Geometry C", CarBrand.GEELY, PlugType.GB_T), // Verify - Often GB/T
    SAIC_MOTOR_MG_ZS_EV("MG ZS EV", CarBrand.SAIC_MOTOR, PlugType.CCS), // Verify
    SAIC_MOTOR_MG5_EV("MG5 EV", CarBrand.SAIC_MOTOR, PlugType.CCS), // Verify
    GREATWALL_ORA_GOOD_CAT("Ora Good Cat", CarBrand.GREATWALL, PlugType.CCS), // Verify
    GREATWALL_ORA_FUNKY_CAT("Ora Funky Cat", CarBrand.GREATWALL, PlugType.CCS), // Verify
    LI_AUTO_LI_ONE("Li One", CarBrand.LI_AUTO, PlugType.GB_T), // Verify - Often GB/T (if PHEV, charging might be less of a focus)
    WELTMEISTER_EX5("EX5", CarBrand.WELTMEISTER, PlugType.GB_T), // Verify - Often GB/T
    WELTMEISTER_EX6("EX6", CarBrand.WELTMEISTER, PlugType.GB_T), // Verify - Often GB/T
    HONGQI_E_HS9("E-HS9", CarBrand.HONGQI, PlugType.GB_T),
    AUTRE1("Autre", CarBrand.AUTRE, PlugType.NACS),
    AUTRE2("Autre", CarBrand.AUTRE, PlugType.TYPE_2),
    AUTRE3("Autre", CarBrand.AUTRE, PlugType.TESLA_SUPERCHARGER),
    AUTRE4("Autre", CarBrand.AUTRE, PlugType.CCS),
    AUTRE5("Autre", CarBrand.AUTRE, PlugType.CHADEMO),
    AUTRE6("Autre", CarBrand.AUTRE, PlugType.GB_T),
    AUTRE7("Autre", CarBrand.AUTRE, PlugType.AUTRE);

    private final String label;
    private final CarBrand carBrand;
    private final PlugType plugType;
    private final PlugType secondaryPlugType; // for models with two plugs

    CarModel(String label, CarBrand carBrand, PlugType plugType) {
        this.label = label;
        this.carBrand = carBrand;
        this.plugType = plugType;
        this.secondaryPlugType = null;
    }

    CarModel(String label, CarBrand carBrand, PlugType plugType, PlugType secondaryPlugType) {
        this.label = label;
        this.carBrand = carBrand;
        this.plugType = plugType;
        this.secondaryPlugType = secondaryPlugType;
    }


    public String getLabel() {
        return label;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public PlugType getPlugType() {
        return plugType;
    }

    public PlugType getSecondaryPlugType() {
        return secondaryPlugType;
    }
}