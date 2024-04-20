package main.ui;

import main.business.*;
import main.business.CSVUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class ReservationPanel extends JDialog {
    private JComboBox<String> tableComboBox;
    private JSpinner dateSpinner;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField partySizeField;
    private JButton submitButton;

    public ReservationPanel(Frame owner) {
        super(owner, "Make a Reservation", true);
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        tableComboBox = new JComboBox<>();
        dateSpinner = new JSpinner(new SpinnerDateModel());
        nameField = new JTextField();
        phoneField = new JTextField();
        partySizeField = new JTextField();
        submitButton = new JButton("Submit");

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm:ss");
        dateSpinner.setEditor(timeEditor);

        inputPanel.add(new JLabel("Table:"));
        inputPanel.add(tableComboBox);
        inputPanel.add(new JLabel("Date and Time:"));
        inputPanel.add(dateSpinner);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Party Size:"));
        inputPanel.add(partySizeField);

        add(inputPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        loadTablesIntoComboBox();

        submitButton.addActionListener(e -> submitReservation());
    }

    private void loadTablesIntoComboBox() {
        List<Table> tables = fetchTables();
        for (Table table : tables) {
            tableComboBox.addItem("Table " + table.getTableNumber() + " - Seats " + table.getSeatingCapacity());
        }
    }


    private List<Table> fetchTables() {
        List<String[]> data = CSVUtils.readCSV("tables.csv");
        List<Table> tables = new ArrayList<>();
        for (String[] row : data) {
            if (row.length >= 2) {
                int tableNumber = Integer.parseInt(row[0]);
                int seatingCapacity = Integer.parseInt(row[1]);
                tables.add(new Table(tableNumber, seatingCapacity));
            }
        }
        return tables;
    }


    private void submitReservation() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        Date date = (Date) dateSpinner.getValue();
        String name = nameField.getText();
        String phone = phoneField.getText();
        int partySize = Integer.parseInt(partySizeField.getText());
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        // Extract table number and capacity from the selected item
        String[] parts = selectedTable.split(" - Seats ");
        int tableNumber = Integer.parseInt(parts[0].replace("Table ", ""));
        int seatingCapacity = Integer.parseInt(parts[1]);

        Customer newCustomer = new Customer(name, phone, UUID.randomUUID().toString());
        Table table = new Table(tableNumber, seatingCapacity);
        Reservation newReservation = new Reservation(newCustomer, dateTime, partySize, table);
        DataStorage.addReservation(newReservation);

        JOptionPane.showMessageDialog(this, "Reservation submitted successfully!");
        dispose();
    }
}
