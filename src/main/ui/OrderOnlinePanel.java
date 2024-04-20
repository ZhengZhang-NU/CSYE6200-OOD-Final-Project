package main.ui;

import main.business.Customer;
import main.business.DataStorage;
import main.business.MenuItem;
import main.business.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderOnlinePanel extends JDialog {

    private JTable menuTable;
    private DefaultTableModel tableModel;
    private final JButton addToCartButton = new JButton("Add to Cart");
    private final JButton confirmOrderButton = new JButton("Confirm Order");
    private final JButton sortPriceButton = new JButton("Sort by Price");
    private final JTextField nameField = new JTextField(20);
    private final JTextField addressField = new JTextField(30);
    private final JTextField phoneField = new JTextField(20);
    private final JTextArea cartArea = new JTextArea(5, 30);
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final List<MenuItem> cartItems = new ArrayList<>();

    public OrderOnlinePanel(JFrame owner) {
        super(owner, "Order Online", true);
        setSize(800, 800);
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
        sortPriceButton.addActionListener(e -> sortMenuByPrice());
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
        bottomPanel.add(sortPriceButton);
        return bottomPanel;
    }

    private void loadMenuItems() {
        String filePath = "menu.csv";
        List<String[]> menuData = main.business.CSVUtils.readCSV(filePath);
        for (String[] item : menuData) {
            if (item.length >= 3) {
                String name = item[0];
                double price = Double.parseDouble(item[1]);
                String description = item[2];
                MenuItem menuItem = new MenuItem(name, price, description);
                menuItems.add(menuItem);
                tableModel.addRow(new Object[]{name, price, description});
            }
        }
    }

    private void sortMenuByPrice() {
        menuItems.sort(Comparator.comparingDouble(MenuItem::getPrice));
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (MenuItem item : menuItems) {
            tableModel.addRow(new Object[]{item.getName(), String.format("$%.2f", item.getPrice()), item.getDescription()});
        }
    }

    private void addToCart() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow != -1) {
            MenuItem item = menuItems.get(selectedRow);
            cartItems.add(item);
            cartArea.append(item.getName() + " - $" + item.getPrice() + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to add to your cart.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmOrder() {
        String customerName = nameField.getText().trim();
        String customerAddress = addressField.getText().trim();
        String customerPhone = phoneField.getText().trim();

        if (customerName.isEmpty() || customerAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and address cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!customerPhone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone number must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = new Customer(customerName, customerPhone, customerAddress);
        Order order = new Order(DataStorage.generateOrderId(), customer, new ArrayList<>(cartItems));
        DataStorage.addOrder(order);

        JOptionPane.showMessageDialog(this, "Order submitted successfully!");
        dispose();
    }
}
