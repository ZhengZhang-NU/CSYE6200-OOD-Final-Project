package main.ui;

import main.business.Order;
import main.business.Reservation;
import main.business.DataStorage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusQueryPanel extends JDialog {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextArea resultArea;

    public StatusQueryPanel(JFrame owner) {
        super(owner, "Status Query", true);
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Set background colors for the input panel and main frame
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBackground(new Color(245, 245, 245)); // Light gray for input panel

        nameField = new JTextField();
        phoneField = new JTextField();
        JButton searchButton = new JButton("Search");

        // Customizing button colors and text area
        searchButton.setBackground(new Color(100, 149, 237)); // Cornflower blue for the search button
        searchButton.setForeground(Color.WHITE); // White text

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(240, 248, 255)); // Alice blue for the result area

        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchCustomerOrders());
        add(searchButton, BorderLayout.SOUTH);
    }

    private void searchCustomerOrders() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        List<Order> orders = DataStorage.findOrdersByNameAndPhone(name, phone);
        List<Reservation> reservations = DataStorage.findReservationsByNameAndPhone(name, phone);

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Orders:\n");
        for (Order order : orders) {
            resultBuilder.append(order.toString()).append("\n");
        }
        resultBuilder.append("\nReservations:\n");
        for (Reservation reservation : reservations) {
            resultBuilder.append(reservation.toString()).append("\n");
        }

        resultArea.setText(resultBuilder.toString());
    }
}
