package main.ui;

import main.business.Customer;
import main.business.Reservation;
import main.business.Table;
import main.business.DataStorage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationPanel extends JDialog {

    private final JComboBox<Table> tableComboBox;
    private final JSpinner timeSpinner;
    private final JTextField nameField = new JTextField(10);
    private final JTextField phoneField = new JTextField(10);
    private final JTextField partySizeField = new JTextField(5);
    private final JButton submitButton = new JButton("Submit");

    public ReservationPanel(Frame owner, List<Table> tables) {
        super(owner, "Make a Reservation", true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Customer Name:"));
        add(nameField);
        add(new JLabel("Phone Number:"));
        add(phoneField);

        tableComboBox = new JComboBox<>(tables.toArray(new Table[0]));
        tableComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Table) {
                    Table table = (Table) value;
                    setText("Table " + table.getTableNumber() + " - Seats " + table.getSeatingCapacity());
                }
                return this;
            }
        });
        add(new JLabel("Table:"));
        add(tableComboBox);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        timeSpinner = new JSpinner(model);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        add(new JLabel("Time:"));
        add(timeSpinner);

        add(new JLabel("Party Size:"));
        add(partySizeField);

        add(submitButton);
        submitButton.addActionListener(e -> submitReservation());

        setLocationRelativeTo(owner);
    }

    private void submitReservation() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        Table selectedTable = (Table) tableComboBox.getSelectedItem();
        Date time = (Date) timeSpinner.getValue();
        int partySize = Integer.parseInt(partySizeField.getText());

        LocalDateTime reservationTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());

        Customer customer = new Customer(name, phone, "");
        Reservation reservation = new Reservation(customer, reservationTime, partySize, selectedTable);
        DataStorage.addReservation(reservation);

        JOptionPane.showMessageDialog(this, "Reservation submitted successfully!");
        dispose(); // Close the dialog
    }
}
