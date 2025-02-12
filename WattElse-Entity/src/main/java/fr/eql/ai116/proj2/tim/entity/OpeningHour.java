package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Horaire Ouverture
public class OpeningHour {

    private long idOpeningHour;
    private final LocalDate startHour;
    private final LocalDate endHour;
    private final LocalDate startValidityDateOpeningHour;
    private final LocalDate endValidityDateOpeningHour;

    /// Constructor///

    public OpeningHour(LocalDate startHour, LocalDate endHour, LocalDate startValidityDateOpeningHour, LocalDate endValidityDateOpeningHour, long idOpeningHour) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.startValidityDateOpeningHour = startValidityDateOpeningHour;
        this.endValidityDateOpeningHour = endValidityDateOpeningHour;
        this.idOpeningHour = idOpeningHour;
    }

    /// Getter///
    public long getIdOpeningHour() {
        return idOpeningHour;
    }

    public LocalDate getStartHour() {
        return startHour;
    }

    public LocalDate getEndHour() {
        return endHour;
    }

    public LocalDate getStartValidityDateOpeningHour() {
        return startValidityDateOpeningHour;
    }

    public LocalDate getEndValidityDateOpeningHour() {
        return endValidityDateOpeningHour;
    }

    /// Setter///

    public void setIdOpeningHour(long idOpeningHour) {
        this.idOpeningHour = idOpeningHour;
    }
}
