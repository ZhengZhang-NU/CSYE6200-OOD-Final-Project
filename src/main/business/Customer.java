package main.business;

import java.util.ArrayList;
import java.util.List;


public class Customer extends Person {
    private String customerId;
    private List<Reservation> reservations;


    public Customer(String name, String phoneNumber, String customerId) {
        super(name, phoneNumber);
        this.customerId = customerId;
        this.reservations = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
