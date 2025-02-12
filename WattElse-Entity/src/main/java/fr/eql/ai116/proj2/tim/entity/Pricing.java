package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Tarif
public class Pricing {

    private long idPricing;
    private final int price;
    private final LocalDate startDatePricing;
    private final LocalDate endDatePricing;

    /// Constructor///

    public Pricing(int price, LocalDate startDatePricing, LocalDate endDatePricing, long idPricing) {
        this.price = price;
        this.startDatePricing = startDatePricing;
        this.endDatePricing = endDatePricing;
        this.idPricing = idPricing;
    }

    /// Getter ///

    public long getIdPricing() {
        return idPricing;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getStartDatePricing() {
        return startDatePricing;
    }

    public LocalDate getEndDatePricing() {
        return endDatePricing;
    }

    //Setter///


    public void setIdPricing(long idPricing) {
        this.idPricing = idPricing;
    }
}
