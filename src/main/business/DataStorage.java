package main.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataStorage {
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Reservation> reservations = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public static List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public static List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public static List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public static int generateOrderId() {
        return orders.size() + 1;
    }

    public static Reservation getReservationById(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null; // Return null if no reservation matches the given ID
    }

    public static List<Order> findOrdersByNameAndPhone(String name, String phone) {
        return orders.stream()
                .filter(order -> order.getCustomer().getName().equalsIgnoreCase(name) && order.getCustomer().getPhoneNumber().equals(phone))
                .collect(Collectors.toList());
    }

    public static List<Reservation> findReservationsByNameAndPhone(String name, String phone) {
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().getName().equalsIgnoreCase(name) && reservation.getCustomer().getPhoneNumber().equals(phone))
                .collect(Collectors.toList());
    }
}
