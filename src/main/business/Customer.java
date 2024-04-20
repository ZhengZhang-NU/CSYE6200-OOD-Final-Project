package main.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Customer extends Person {
    private String customerId;
    private String address;
    private List<Reservation> reservations;


    public Customer(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.customerId = UUID.randomUUID().toString();
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
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
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
