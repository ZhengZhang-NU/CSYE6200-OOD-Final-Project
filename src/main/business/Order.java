package main.business;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<MenuItem> orderedItems;
    private OrderStatus status;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderedItems = new ArrayList<>();
        this.status = OrderStatus.PREPARING; // Default status
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

    // Add item to order
    public void addItemToOrder(MenuItem item) {
        orderedItems.add(item);
    }

    // Remove item from order
    public boolean removeItemFromOrder(MenuItem item) {
        return orderedItems.remove(item);
    }
}
