package fr.eql.ai116.proj2.tim.entity;
// Marque
public enum CarBrand {
    TESLA("Tesla"),
    RIVIAN("Rivian"),
    LUCID("Lucid Motors"),
    BYTON("Byton"),
    NIO("NIO"),
    POLESTAR("Polestar"),
    FISKER("Fisker"),
    BOLLINGER("Bollinger Motors"),
    LORDSTOWN("Lordstown Motors"),
    FARADAY("Faraday Future"),
    CANOO("Canoo"),
    AUDI("Audi"),
    BMW("BMW"),
    VOLKSWAGEN("Volkswagen"),
    MERCEDES("Mercedes-Benz"),
    PORSCHE("Porsche"),
    FORD("Ford"),
    CHEVROLET("Chevrolet"),
    HYUNDAI("Hyundai"),
    KIA("Kia"),
    NISSAN("Nissan"),
    VOLVO("Volvo"),
    PEUGEOT("Peugeot"),
    RENAULT("Renault"),
    JAGUAR("Jaguar"),
    LAND_ROVER("Land Rover"),
    MINI("Mini"),
    MAZDA("Mazda"),
    FIAT("Fiat"),
    HONDA("Honda"),
    TOYOTA("Toyota"),
    LEXUS("Lexus"),
    GMC("GMC"),
    MITSUBISHI("Mitsubishi"),
    SMART("Smart"),
    SUBARU("Subaru"),
    BYD("BYD"),
    XPENG("XPeng"),
    GEELY("Geely"),
    SAIC_MOTOR("SAIC Motor"),
    GREATWALL("Great Wall"),
    LI_AUTO("Li Auto"),
    WELTMEISTER("Weltmeister"),
    HONGQI("Hongqi"),
    AUTRE("Autre");

    private final String label;

    CarBrand(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
