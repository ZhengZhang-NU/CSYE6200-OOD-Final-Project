package main.ui;

import javax.swing.*;
import java.awt.*;
import main.business.Table;
import java.util.List;

public class CustomerPanel extends JFrame {
    private JButton makeReservationButton;
    private JButton orderOnlineButton;
    private MainFrame mainFrame; // 添加对 MainFrame 的引用

    public CustomerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame; // 存储对主框架的引用
        setTitle("Customer Options");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 使用 DISPOSE_ON_CLOSE 而不是 EXIT_ON_CLOSE
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
        ReservationPanel reservationPanel = new ReservationPanel(this, tables);
        reservationPanel.setVisible(true);
    }

    private void openOrderOnlinePanel() {
        OrderOnlinePanel orderOnlinePanel = new OrderOnlinePanel(this);
        orderOnlinePanel.setVisible(true);
    }

    private List<Table> fetchTables() {
        // 实际的表获取应当更复杂，这里仅作为示例
        return java.util.Arrays.asList(new Table(1, 4), new Table(2, 6), new Table(3, 2));
    }
}
