package main.ui;

import javax.swing.*;
import java.awt.*;

public class OrderOnlinePanel extends JDialog {

    private final JList<String> menuList = new JList<>();
    private final DefaultListModel<String> menuListModel = new DefaultListModel<>();
    private final JButton addToCartButton = new JButton("Add to Cart");
    private final JButton confirmOrderButton = new JButton("Confirm Order");

    public OrderOnlinePanel(CustomerPanel owner) {
        super(owner, "Order Online", true);
        setSize(300, 400);
        setLayout(new BorderLayout());


        menuList.setModel(menuListModel);
        JScrollPane scrollPane = new JScrollPane(menuList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addToCartButton);
        bottomPanel.add(confirmOrderButton);
        add(bottomPanel, BorderLayout.SOUTH);

        addToCartButton.addActionListener(e -> addToCart());
        confirmOrderButton.addActionListener(e -> confirmOrder());
    }

    private void addToCart() {
        String selectedItem = menuList.getSelectedValue();

    }

    private void confirmOrder() {

    }
}
