package main.ui;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JDialog {

    public EmployeePanel(Frame owner) {
        super(owner, "Employee Panel", true);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));  // 4 buttons in a column

        JButton editMenuButton = new JButton("Edit Menu");
        JButton editTablesButton = new JButton("Edit Tables");
        JButton viewReservationsButton = new JButton("View and Process Reservations");
        JButton viewOrdersButton = new JButton("View and Update Orders");

        editMenuButton.addActionListener(e -> openEditMenuPanel());
        editTablesButton.addActionListener(e -> openEditTablesPanel());
        viewReservationsButton.addActionListener(e -> openViewReservationsPanel());
        viewOrdersButton.addActionListener(e -> openViewOrdersPanel());

        add(editMenuButton);
        add(editTablesButton);
        add(viewReservationsButton);
        add(viewOrdersButton);
    }

    private void openEditMenuPanel() {
        EditMenuPanel editMenuPanel = new EditMenuPanel(this);
        editMenuPanel.setVisible(true);
    }

    private void openEditTablesPanel() {
        EditTablesPanel editTablesPanel = new EditTablesPanel(this);
        editTablesPanel.setVisible(true);
    }

    private void openViewReservationsPanel() {
        ViewReservationsPanel viewReservationsPanel = new ViewReservationsPanel(this);
        viewReservationsPanel.setVisible(true);
    }

    private void openViewOrdersPanel() {
        ViewOrdersPanel viewOrdersPanel = new ViewOrdersPanel(this);
        viewOrdersPanel.setVisible(true);
    }
}
