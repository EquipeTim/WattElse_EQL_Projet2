package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

// Horaire Ouverture
public class OpeningHour implements Serializable {

    private final String day;
    private final LocalTime startHour;
    private final LocalTime endHour;
    private final LocalDate startValidityDateOpeningHour;
    private final LocalDate endValidityDateOpeningHour;

    /// Constructor///

    public OpeningHour(String day, LocalTime startHour, LocalTime endHour
            , LocalDate startValidityDateOpeningHour, LocalDate endValidityDateOpeningHour) {
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startValidityDateOpeningHour = startValidityDateOpeningHour;
        this.endValidityDateOpeningHour = endValidityDateOpeningHour;
    }

    /// Getter///
    public String getDay() {
        return day;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public LocalDate getStartValidityDateOpeningHour() {
        return startValidityDateOpeningHour;
    }

    public LocalDate getEndValidityDateOpeningHour() {
        return endValidityDateOpeningHour;
    }

    @Override
    public String toString() {
        return "OpeningHour{" +
                "day='" + day + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", startValidityDateOpeningHour=" + startValidityDateOpeningHour +
                ", endValidityDateOpeningHour=" + endValidityDateOpeningHour +
                '}';
    }
}
