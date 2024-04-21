package main.ui;

import main.business.DataStorage;
import main.business.Order;
import main.business.OrderStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ViewOrdersPanel extends JDialog {
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JButton updateStatusButton;

    public ViewOrdersPanel(Dialog owner) {
        super(owner, "View and Update Orders", true);
        setSize(800, 800);
        setLayout(new BorderLayout());

        initializeUIComponents();
        loadOrders();
    }

    private void initializeUIComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Order ID");
        tableModel.addColumn("Customer Name");
        tableModel.addColumn("Total Items");
        tableModel.addColumn("Order Status");

        ordersTable = new JTable(tableModel);
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordersTable.setBackground(new Color(255, 255, 255)); // White background for table
        ordersTable.setForeground(new Color(0, 0, 0)); // Black text for readability

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        updateStatusButton = new JButton("Update Status");
        updateStatusButton.setBackground(new Color(70, 130, 180)); // Steel blue color for the update button
        updateStatusButton.setForeground(Color.WHITE); // White text for better visibility
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateStatusButton);
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light gray background for the button panel
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadOrders() {
        List<Order> orders = DataStorage.getOrders();
        for (Order order : orders) {
            tableModel.addRow(new Object[] {
                    order.getOrderId(),
                    order.getCustomer().getName(),
                    order.getOrderedItems().size(),
                    order.getStatus().toString()
            });
        }
    }

    private void updateOrderStatus(ActionEvent e) {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow != -1) {
            int orderId = (Integer) tableModel.getValueAt(selectedRow, 0);
            Order order = DataStorage.getOrders().stream()
                    .filter(o -> o.getOrderId() == orderId)
                    .findFirst()
                    .orElse(null);

            if (order != null) {
                Object[] possibilities = { "PREPARING", "READY", "DELIVERED" };
                String newStatus = (String) JOptionPane.showInputDialog(
                        this,
                        "Select new status:",
                        "Update Order Status",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        order.getStatus().toString());

                if (newStatus != null && newStatus.length() > 0) {
                    order.setStatus(OrderStatus.valueOf(newStatus));
                    tableModel.setValueAt(newStatus, selectedRow, 3);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to update its status.", "Update Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
