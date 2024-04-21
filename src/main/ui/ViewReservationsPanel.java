package main.ui;

import main.business.DataStorage;
import main.business.Reservation;
import main.business.ReservationStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewReservationsPanel extends JDialog {
    private JTable reservationsTable;
    private DefaultTableModel tableModel;
    private JButton approveButton;
    private JButton rejectButton;

    public ViewReservationsPanel(Dialog owner) {
        super(owner, "View and Process Reservations", true);
        setSize(800, 800);
        setLayout(new BorderLayout());

        initializeTable();
        initializeButtons();
    }

    private void initializeTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Reservation ID");
        tableModel.addColumn("Customer Name");
        tableModel.addColumn("Table Number");
        tableModel.addColumn("Reservation Time");
        tableModel.addColumn("Party Size");
        tableModel.addColumn("Status");

        reservationsTable = new JTable(tableModel);
        reservationsTable.setBackground(new Color(255, 255, 255)); // White background for table
        reservationsTable.setForeground(new Color(0, 0, 0)); // Black text for readability

        loadReservations();

        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        scrollPane.setBackground(new Color(245, 245, 245)); // Light gray background for scroll pane
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light gray background for button panel

        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");

        approveButton.setBackground(new Color(34, 139, 34)); // Forest green for approve button
        approveButton.setForeground(Color.WHITE); // White text for better visibility
        rejectButton.setBackground(new Color(220, 20, 60)); // Crimson for reject button
        rejectButton.setForeground(Color.WHITE); // White text for better visibility

        approveButton.addActionListener(e -> updateStatus(ReservationStatus.APPROVED));
        rejectButton.addActionListener(e -> updateStatus(ReservationStatus.REJECTED));

        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadReservations() {
        for (Reservation reservation : DataStorage.getReservations()) {
            tableModel.addRow(new Object[] {
                    reservation.getReservationId(),
                    reservation.getCustomer().getName(),
                    reservation.getTable().getTableNumber(),
                    reservation.getReservationTime().toString(),
                    reservation.getPartySize(),
                    reservation.getStatus().toString()
            });
        }
    }

    private void updateStatus(ReservationStatus newStatus) {
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow != -1) {
            String reservationId = (String) tableModel.getValueAt(selectedRow, 0);
            Reservation reservation = DataStorage.getReservationById(reservationId);
            if (reservation != null) {
                reservation.setStatus(newStatus);
                tableModel.setValueAt(newStatus.toString(), selectedRow, 5);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a reservation to update its status.", "Update Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
