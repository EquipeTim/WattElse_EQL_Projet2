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
    private Long idPayment;
    private long idUser;
    private long idOwner;
    private LocalDateTime startDateCharging;
    private LocalDateTime endDateCharging;
    private LocalDateTime reservationDate;
    private LocalDateTime reservationCancelDate;
    private Float consumeQuantity;
    private String priceType;
    private Float price;
    private Float monetaryAmount;
    private String status;
    private Long statusId;
    private LocalTime chargeDurationMin;
    private Long idPaymentRefuseReason;
    private LocalDateTime paymentDate;
    private String paymentRefuseReason;
    private String address;



    /// Constructor///

    public Transaction(Long statusId, String status, Long idTransaction){
        this.statusId = statusId;
        this.status = status;
        this.idTransaction = idTransaction;
    }

    public Transaction(Long idPayment, long idTransaction, Long idUser, Long idOwner, LocalDateTime reservationDate,
                       LocalDateTime reservationCancelDate, LocalDateTime startDateCharging,
                       LocalDateTime endDateCharging, Float consumeQuantity,
                       String priceType, Float price, Float amountToPay,
                       Long idPaymentRefuseReason, LocalDateTime paymentDate, String paymentRefuseReason, String address) {
        this.idPayment = idPayment;
        this.idTransaction = idTransaction;
        this.idUser = idUser;
        this.idOwner = idOwner;
        this.startDateCharging = startDateCharging;
        this.reservationDate = reservationDate;
        this.reservationCancelDate = reservationCancelDate;
        this.endDateCharging = endDateCharging;
        this.consumeQuantity = consumeQuantity;
        this.priceType = priceType;
        this.price = price;
        this.monetaryAmount = amountToPay;
        this.idPaymentRefuseReason = idPaymentRefuseReason;
        this.paymentDate = paymentDate;
        this.paymentRefuseReason = paymentRefuseReason;
        this.address = address;
        if (startDateCharging != null && endDateCharging != null) {
            chargeDurationMin = calculateDuration(endDateCharging, startDateCharging);
        }
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

    public String getAddress() {
        return address;
    }

    public LocalDateTime getEndDateCharging() {
        return endDateCharging;
    }

    public Long getIdPayment() {
        return idPayment;
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

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public LocalTime getChargeDurationMin() {
        return chargeDurationMin;
    }

    public LocalDateTime getReservationCancelDate() {
        return reservationCancelDate;
    }

    public void setChargeDurationMin(LocalTime chargeDurationMin) {
        this.chargeDurationMin = chargeDurationMin;
    }

    public Long getIdPaymentRefuseReason() {
        return idPaymentRefuseReason;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentRefuseReason() {
        return paymentRefuseReason;
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
