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
        setSize(600, 400);
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
        loadReservations();

        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");

        approveButton.addActionListener(e -> updateStatus(ReservationStatus.APPROVED));
        rejectButton.addActionListener(e -> updateStatus(ReservationStatus.REJECTED));

        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadReservations() {
        for (Reservation reservation : DataStorage.getReservations()) {
            tableModel.addRow(new Object[]{
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
            JOptionPane.showMessageDialog(this, "Please select a reservation to update its status.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
