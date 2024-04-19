package main;


public class PersonFactory {

    public static Person createPerson(String type, String name, String phoneNumber, String id) {
        switch (type.toLowerCase()) {
            case "customer":
                return new Customer(name, phoneNumber, id);
            case "employee":
                return new Employee(name, phoneNumber, id);
            default:
                throw new IllegalArgumentException("Invalid person type.");
        }
    }
}