package main.business;


import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final List<Reservation> reservations = new ArrayList<>();

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public static List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}
