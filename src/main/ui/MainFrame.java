package main.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Restaurant Reservation System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton employeeButton = new JButton("Employee");
        JButton customerButton = new JButton("Customer");

        employeeButton.addActionListener(e -> openEmployeePanel());
        customerButton.addActionListener(e -> openCustomerPanel());

        add(employeeButton);
        add(customerButton);
    }

    private void openEmployeePanel() {
        EmployeePanel employeePanel = new EmployeePanel(this);
        employeePanel.setVisible(true);
    }

    private void openCustomerPanel() {
        CustomerPanel customerPanel = new CustomerPanel(this);
        customerPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
