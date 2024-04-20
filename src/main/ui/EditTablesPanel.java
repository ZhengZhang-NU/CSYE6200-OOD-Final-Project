package main.ui;

import main.business.CSVUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class EditTablesPanel extends JDialog {
    private JTable tablesTable;
    private DefaultTableModel tableModel;
    private JTextField tableNumberField, seatingCapacityField;

    public EditTablesPanel(Dialog owner) {
        super(owner, "Edit Tables", true);
        setSize(800, 800);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Table Number", "Seating Capacity"}, 0);
        tablesTable = new JTable(tableModel);
        tablesTable.setFillsViewportHeight(true);
        tablesTable.setBackground(new Color(255, 255, 255)); // White color for table background
        tablesTable.setForeground(new Color(51, 51, 51)); // Dark grey for text
        add(new JScrollPane(tablesTable), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(240, 240, 240)); // Soft light gray for input panel background
        tableNumberField = new JTextField(10);
        seatingCapacityField = new JTextField(10);

        inputPanel.add(new JLabel("Table Number:"));
        inputPanel.add(tableNumberField);
        inputPanel.add(new JLabel("Seating Capacity:"));
        inputPanel.add(seatingCapacityField);

        JButton addButton = new JButton("Add Table");
        addButton.setBackground(new Color(120, 199, 119)); // Soft green for add button
        addButton.setForeground(Color.WHITE); // White text for better visibility
        addButton.addActionListener(this::addTable);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240)); // Consistent background with input panel
        JButton deleteButton = new JButton("Delete Table");
        deleteButton.setBackground(new Color(219, 83, 77)); // Soft red for delete button
        deleteButton.setForeground(Color.WHITE);
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(72, 133, 237)); // Soft blue for save button
        saveButton.setForeground(Color.WHITE);

        deleteButton.addActionListener(this::deleteTable);
        saveButton.addActionListener(this::saveChanges);

        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTableData();
    }

    private void addTable(ActionEvent e) {
        String tableNumber = tableNumberField.getText();
        String seatingCapacity = seatingCapacityField.getText();
        if (!tableNumber.isEmpty() && !seatingCapacity.isEmpty()) {
            try {
                Integer.parseInt(tableNumber);  // Validate table number is an integer
                Integer.parseInt(seatingCapacity);  // Validate seating capacity is an integer
                tableModel.addRow(new Object[]{tableNumber, seatingCapacity});
                tableNumberField.setText("");
                seatingCapacityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integer values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTable(ActionEvent e) {
        int selectedRow = tablesTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveChanges(ActionEvent e) {
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int cols = tableModel.getColumnCount();
            String[] rowData = new String[cols];
            for (int j = 0; j < cols; j++) {
                rowData[j] = tableModel.getValueAt(i, j).toString();
            }
            data.add(rowData);
        }
        CSVUtils.writeCSV(data, "tables.csv");  // Save to tables.csv
        JOptionPane.showMessageDialog(this, "Changes saved successfully!");
        dispose();
    }

    private void loadTableData() {
        List<String[]> data = CSVUtils.readCSV("tables.csv");  // Load from tables.csv
        for (String[] rowData : data) {
            tableModel.addRow(rowData);
        }
    }
}
