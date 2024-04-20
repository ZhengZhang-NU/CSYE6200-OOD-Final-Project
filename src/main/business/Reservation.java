package main.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private LocalDateTime reservationTime;
    private int partySize;
    private Table table;
    private ReservationStatus status;

    public Reservation(Customer customer, LocalDateTime reservationTime, int partySize, Table table) {
        this.reservationId = UUID.randomUUID().toString();
        this.customer = customer;
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.table = table;
        this.status = ReservationStatus.PENDING;
    }

    // Getters and Setters
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Reservation ID: " + reservationId +
                ", Customer: " + customer.getName() +
                ", Phone: " + customer.getPhoneNumber() +
                ", Table: " + table.getTableNumber() +
                ", Time: " + reservationTime.format(formatter) +
                ", Party Size: " + partySize +
                ", Status: " + status;
    }
}
