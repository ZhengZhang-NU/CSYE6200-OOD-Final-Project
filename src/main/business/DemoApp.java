package main.business;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class DemoApp {


    private void demoCSVUtils() {
        String filePath = "example.csv";
        List<String[]> dataToWrite = Arrays.asList(
                new String[]{"Name", "Phone", "Address"},
                new String[]{"Alice", "123456789", "123 Wonderland Ave"},
                new String[]{"Bob", "987654321", "456 Nowhere Blvd"}
        );
        CSVUtils.writeCSV(dataToWrite, filePath);
        System.out.println("Data written to CSV.");

        List<String[]> dataRead = CSVUtils.readCSV(filePath);
        System.out.println("Data read from CSV:");
        for (String[] record : dataRead) {
            System.out.println(Arrays.toString(record));
        }
    }

    private void demoBusinessLogic() {
        Customer alice = new Customer("Alice", "123456789", "123 Wonderland Ave");
        DataStorage.addCustomer(alice);
        System.out.println("Customer added: " + alice.getName());

        Reservation reservation = new Reservation(alice, java.time.LocalDateTime.now(), 4, new Table(101, 4));
        DataStorage.addReservation(reservation);
        System.out.println("Reservation added for: " + alice.getName() + " at table " + reservation.getTable().getTableNumber());

        Menu menu = createSortedMenu();

        List<MenuItem> itemsToOrder = Arrays.asList(menu.getItems().get(0), menu.getItems().get(1));
        Order order = new Order(DataStorage.generateOrderId(), alice, itemsToOrder);
        DataStorage.addOrder(order);
        System.out.println("Order placed: " + order);

        Employee bob = new Employee("Bob", "234567890", "E001");
        updateOrderStatus(order, OrderStatus.READY, bob);
    }

    private Menu createSortedMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Burger", 8.99, "Beef burger with cheese"));
        menu.addItem(new MenuItem("Fries", 2.99, "Crispy fries with ketchup"));
        menu.addItem(new MenuItem("Soda", 1.99, "Carbonated soft drink"));
        menu.getItems().sort(Comparator.comparingDouble(MenuItem::getPrice));
        System.out.println("Menu sorted by price:");
        for (MenuItem item : menu.getItems()) {
            System.out.println(item.getName() + ": $" + item.getPrice());
        }
        return menu;
    }


    private void updateOrderStatus(Order order, OrderStatus newStatus, Employee employee) {
        System.out.println("Order status updated by employee " + employee.getName() + ":");
        order.setStatus(newStatus);
        System.out.println(order);
    }

    public void drive() {


        System.out.println("Demonstrating CSV Utils:");
        demoCSVUtils();

        System.out.println("\nDemonstrating Business Logic:");
        demoBusinessLogic();


    }


}
