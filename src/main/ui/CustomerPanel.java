package main.ui;

import main.business.Table;
import main.ui.MainFrame;
import main.ui.OrderOnlinePanel;
import main.ui.ReservationPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CustomerPanel extends JFrame {
    private JButton makeReservationButton;
    private JButton orderOnlineButton;
    private JButton statusQueryButton;
    private MainFrame mainFrame;

    public CustomerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setTitle("Customer Options");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Set a custom background color
        getContentPane().setBackground(new Color(245, 245, 245)); // Soft gray background

        initializeComponents();
    }

    private void initializeComponents() {
        makeReservationButton = new JButton("Make a Reservation");
        orderOnlineButton = new JButton("Order Online");
        statusQueryButton = new JButton("Status Query");

        // Customize button colors and fonts
        customizeButton(makeReservationButton, new Color(169, 204, 227), Color.WHITE); // Desaturated cyan
        customizeButton(orderOnlineButton, new Color(93, 109, 126), Color.WHITE); // Cool gray blue
        customizeButton(statusQueryButton, new Color(169, 204, 227), Color.WHITE); // Soft blue

        makeReservationButton.addActionListener(e -> openReservationPanel());
        orderOnlineButton.addActionListener(e -> openOrderOnlinePanel());
        statusQueryButton.addActionListener(e -> openStatusQueryPanel());

        add(makeReservationButton);
        add(orderOnlineButton);
        add(statusQueryButton);
    }

    private void customizeButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false); // Remove the focus border around the button
    }

    private void openReservationPanel() {
        List<Table> tables = fetchTables();
        ReservationPanel reservationPanel = new ReservationPanel(this);
        reservationPanel.setVisible(true);
    }

    private void openOrderOnlinePanel() {
        OrderOnlinePanel orderOnlinePanel = new OrderOnlinePanel(this);
        orderOnlinePanel.setVisible(true);
    }

    private void openStatusQueryPanel() {
        StatusQueryPanel statusQueryPanel = new StatusQueryPanel(this);
        statusQueryPanel.setVisible(true);
    }

    private List<Table> fetchTables() {
        return Arrays.asList(new Table(1, 4), new Table(2, 6), new Table(3, 2));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerPanel frame = new CustomerPanel(new MainFrame());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
