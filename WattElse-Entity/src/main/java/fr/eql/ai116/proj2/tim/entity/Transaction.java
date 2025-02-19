package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static jdk.nashorn.internal.objects.NativeDate.getTime;

// Transaction
public class Transaction implements Serializable {
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    private long idTransaction;
    private long idUser;
    private long idOwner;
    private LocalDateTime startDateCharging;
    private LocalDateTime endDateCharging;
    private Float consumeQuantity;
    private String priceType;
    private Float price;
    private Float monetaryAmount;
    private String status;
    private Long statusId;
    private LocalTime chargeDurationMin;


    /// Constructor///

    public Transaction(Long statusId, String status, Long idTransaction){
        this.statusId = statusId;
        this.status = status;
        this.idTransaction = idTransaction;
    }

    public Transaction(long idTransaction, Long idUser, Long idOwner, LocalDateTime startDateCharging,
                       LocalDateTime endDateCharging, Float consumeQuantity,
                       String priceType, Float price) {
        this.idTransaction = idTransaction;
        this.idUser = idUser;
        this.idOwner = idOwner;
        this.startDateCharging = startDateCharging;
        this.endDateCharging = endDateCharging;
        this.consumeQuantity = consumeQuantity;
        this.priceType = priceType;
        this.price = price;
        this.monetaryAmount = price * consumeQuantity;
        chargeDurationMin = calculateDuration(endDateCharging, startDateCharging);
    }

    private LocalTime calculateDuration(LocalDateTime end, LocalDateTime start){
        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();
        int hours = (int) seconds / SECONDS_PER_HOUR;
        int minutes = (int) ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        int secs = (int) (seconds % SECONDS_PER_MINUTE);
        return LocalTime.of(hours, minutes, secs);

    }

    /// Setter ///
    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /// Getter///

    public long getIdTransaction() {
        return idTransaction;
    }


    public LocalDateTime getStartDateCharging() {
        return startDateCharging;
    }

    public LocalDateTime getEndDateCharging() {
        return endDateCharging;
    }


    public Float getConsumeQuantity() {
        return consumeQuantity;
    }

    public String getPriceType() {
        return priceType;
    }

    public Float getPrice() {
        return price;
    }

    public Float getMonetaryAmount() {
        return monetaryAmount;
    }

    public String getStatus() {
        return status;
    }

    public Long getStatusId() {
        return statusId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", startDateCharging=" + startDateCharging +
                ", endDateCharging=" + endDateCharging +
                ", consumeQuantity=" + consumeQuantity +
                ", priceType='" + priceType + '\'' +
                ", price=" + price +
                ", monetaryAmount=" + monetaryAmount +
                ", status='" + status + '\'' +
                ", statusId=" + statusId +
                ", chargeDurationMin=" + chargeDurationMin +
                '}';
    }
}
