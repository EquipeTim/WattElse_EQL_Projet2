package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Indisponibilite
public class Unavailability {

    private long idUnavailability;
    private final LocalDate startDateUnavailability;
    private final LocalDate endDateUnavailability;

    //Constructor///
    public Unavailability(LocalDate startDateUnavailability, LocalDate endDateUnavailability, long idUnavailability) {
        this.startDateUnavailability = startDateUnavailability;
        this.endDateUnavailability = endDateUnavailability;
        this.idUnavailability = idUnavailability;
    }
}
