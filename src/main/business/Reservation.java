package main.business;

import java.time.LocalDateTime;

public class Reservation {
    private Customer customer;
    private LocalDateTime reservationTime;
    private int partySize;
    private Table table;

    public Reservation(Customer customer, LocalDateTime reservationTime, int partySize, Table table) {
        this.customer = customer;
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.table = table;
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
}