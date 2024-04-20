package main.business;

import java.util.ArrayList;
import java.util.List;

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
}
