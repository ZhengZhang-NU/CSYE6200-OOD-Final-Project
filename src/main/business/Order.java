package main.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private int orderId;
    private Customer customer;
    private List<MenuItem> orderedItems;
    private OrderStatus status;

    public Order(int orderId, Customer customer, List<MenuItem> orderedItems) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderedItems = orderedItems;
        this.status = OrderStatus.PREPARING;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<MenuItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItemToOrder(MenuItem item) {
        this.orderedItems.add(item);
    }




    @Override
    public String toString() {
        String itemsString = orderedItems.stream()
                .map(item -> item.getName() + " (" + item.getPrice() + ")")
                .collect(Collectors.joining(", "));
        return "Order ID: " + orderId +
                ", Customer: " + customer.getName() +
                ", Phone: " + customer.getPhoneNumber() +
                ", Items: " + itemsString +
                ", Total: $" + orderedItems.stream().mapToDouble(MenuItem::getPrice).sum() +
                ", Status: " + status;
    }

}
