package main.ui;

import main.business.*;
import main.business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class OrderOnlinePanel extends JDialog {

    private JTable menuTable;
    private DefaultTableModel tableModel;
    private final JButton addToCartButton = new JButton("Add to Cart");
    private final JButton confirmOrderButton = new JButton("Confirm Order");
    private final JTextField nameField = new JTextField(20);
    private final JTextField addressField = new JTextField(30);
    private final JTextField phoneField = new JTextField(20);
    private final JTextArea cartArea = new JTextArea(5, 30);
    private final List<MenuItem> cartItems = new ArrayList<>();

    public OrderOnlinePanel(JFrame owner) {
        super(owner, "Order Online", true);
        setSize(600, 600);
        setLayout(new BorderLayout());

        initializeMenuTable();
        initializeControls();

        add(new JScrollPane(menuTable), BorderLayout.CENTER);
        add(createCartPanel(), BorderLayout.EAST);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private void initializeMenuTable() {
        tableModel = new DefaultTableModel(new Object[]{"Name", "Price", "Description"}, 0);
        menuTable = new JTable(tableModel);
        loadMenuItems();
    }

    private void initializeControls() {
        addToCartButton.addActionListener(e -> addToCart());
        confirmOrderButton.addActionListener(e -> confirmOrder());
    }

    private JPanel createCartPanel() {
        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createInfoPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(4, 2));
        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("Address:"));
        infoPanel.add(addressField);
        infoPanel.add(new JLabel("Phone:"));
        infoPanel.add(phoneField);
        return infoPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addToCartButton);
        bottomPanel.add(confirmOrderButton);
        return bottomPanel;
    }


    private void loadMenuItems() {

        String filePath = "menu.csv";
        List<String[]> menuData = main.util.CSVUtils.readCSV(filePath);
        for (String[] item : menuData) {
            if (item.length >= 3) {
                String name = item[0];
                String price = item[1];
                String description = item[2];
                tableModel.addRow(new Object[]{name, price, description});
            }
        }
    }

    private void addToCart() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String price = (String) tableModel.getValueAt(selectedRow, 1);
            String description = (String) tableModel.getValueAt(selectedRow, 2);
            MenuItem item = new MenuItem(name, Double.parseDouble(price), description);
            cartItems.add(item);
            cartArea.append(name + " - $" + price + "\n");
        }
    }

    private void confirmOrder() {
        String customerName = nameField.getText();
        String customerAddress = addressField.getText();
        String customerPhone = phoneField.getText();

        Customer customer = new Customer(customerName, customerPhone, customerAddress);
        Order order = new Order(DataStorage.generateOrderId(), customer, cartItems);
        DataStorage.addOrder(order);

        JOptionPane.showMessageDialog(this, "Order submitted successfully!");
        dispose(); // Close the dialog after order confirmation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame owner = new JFrame();
            OrderOnlinePanel panel = new OrderOnlinePanel(owner);
            panel.setVisible(true);
        });
    }
}
