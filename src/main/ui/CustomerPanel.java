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
    private JButton statusQueryButton; // 新按钮
    private MainFrame mainFrame;

    public CustomerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setTitle("Customer Options");
        setSize(300, 300); // 更新尺寸以适应新按钮
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10)); // 更新布局以适应新按钮

        initializeComponents();
    }

    private void initializeComponents() {
        makeReservationButton = new JButton("Make a Reservation");
        orderOnlineButton = new JButton("Order Online");
        statusQueryButton = new JButton("Status Query"); // 初始化状态查询按钮

        makeReservationButton.addActionListener(e -> openReservationPanel());
        orderOnlineButton.addActionListener(e -> openOrderOnlinePanel());
        statusQueryButton.addActionListener(e -> openStatusQueryPanel()); // 为新按钮设置监听器

        add(makeReservationButton);
        add(orderOnlineButton);
        add(statusQueryButton); // 添加到面板
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
}
