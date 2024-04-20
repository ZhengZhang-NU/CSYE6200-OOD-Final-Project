package main.ui;

import javax.swing.*;
import java.awt.*;
import main.business.Table;

import java.util.Arrays;
import java.util.List;

public class CustomerPanel extends JFrame {
    private JButton makeReservationButton;
    private JButton orderOnlineButton;
    private MainFrame mainFrame;

    public CustomerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setTitle("Customer Options");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        initializeComponents();
    }

    private void initializeComponents() {
        makeReservationButton = new JButton("Make a Reservation");
        orderOnlineButton = new JButton("Order Online");

        makeReservationButton.addActionListener(e -> openReservationPanel());
        orderOnlineButton.addActionListener(e -> openOrderOnlinePanel());

        add(makeReservationButton);
        add(orderOnlineButton);
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

    private List<Table> fetchTables() {

        return Arrays.asList(new Table(1, 4), new Table(2, 6), new Table(3, 2));
    }
}
