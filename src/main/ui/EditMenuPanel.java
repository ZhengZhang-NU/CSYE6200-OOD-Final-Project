package main.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class EditMenuPanel extends JDialog {
    private JTable menuTable;
    private DefaultTableModel model;
    private JTextField nameField, priceField, descriptionField;

    public EditMenuPanel(Dialog owner) {
        super(owner, "Edit Menu", true);
        setSize(800, 800);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Dish Name", "Price", "Description"}, 0);
        menuTable = new JTable(model);
        menuTable.setFillsViewportHeight(true);
        menuTable.setBackground(new Color(255, 255, 255)); // White color for table background for clarity
        menuTable.setForeground(new Color(51, 51, 51)); // Dark grey for text for readability
        add(new JScrollPane(menuTable), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(240, 240, 240)); // Soft light gray for input panel background
        nameField = new JTextField(10);
        priceField = new JTextField(10);
        descriptionField = new JTextField(20);

        inputPanel.add(new JLabel("Dish Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);

        JButton addButton = new JButton("Add to Menu");
        addButton.setBackground(new Color(120, 199, 119)); // Soft green for add button
        addButton.setForeground(Color.WHITE); // White text for better visibility
        addButton.addActionListener(this::addMenuItem);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240)); // Consistent background with input panel
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(219, 83, 77)); // Soft red for delete button
        deleteButton.setForeground(Color.WHITE);
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(72, 133, 237)); // Soft blue for save button
        saveButton.setForeground(Color.WHITE);

        deleteButton.addActionListener(this::deleteMenuItem);
        saveButton.addActionListener(e -> saveChanges());


        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loadMenuData();
    }

    private void addMenuItem(ActionEvent e) {
        String name = nameField.getText();
        String price = priceField.getText();
        String description = descriptionField.getText();

        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Dish name must contain only letters.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!name.isEmpty() && !price.isEmpty() && !description.isEmpty()) {
            model.addRow(new Object[]{name, price, description});
            nameField.setText("");
            priceField.setText("");
            descriptionField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMenuItem(ActionEvent e) {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveChanges() {
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            int cols = model.getColumnCount();
            String[] rowData = new String[cols];
            for (int j = 0; j < cols; j++) {
                rowData[j] = model.getValueAt(i, j).toString();
            }
            data.add(rowData);
        }
        main.business.CSVUtils.writeCSV(data, "menu.csv"); // Specify the file path for menu data
        JOptionPane.showMessageDialog(this, "Changes saved successfully!");
        dispose(); // Optionally close the dialog
    }

    private void loadMenuData() {
        List<String[]> data = main.business.CSVUtils.readCSV("menu.csv"); // Load data from the specified file
        for (String[] rowData : data) {
            model.addRow(rowData);
        }
    }
}
