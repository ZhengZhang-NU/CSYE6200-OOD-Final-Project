package main;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSVUtils {


    public static List<Customer> readCustomersFromCSV(String filePath) {
        List<Customer> customers = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = parseCustomer(line);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
        }
        return customers;
    }

    // Helper method to parse a line of CSV into a Customer object
    private static Customer parseCustomer(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");
        String customerId = st.nextToken().trim();
        String name = st.nextToken().trim();
        String phoneNumber = st.nextToken().trim();
        return new Customer(name, phoneNumber, customerId);
    }

    // Method to write Customers to CSV
    public static void writeCustomersToCSV(String filePath, List<Customer> customers) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            for (Customer customer : customers) {
                String line = String.format("%s,%s\n", customer.getName(), customer.getPhoneNumber());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }
}
