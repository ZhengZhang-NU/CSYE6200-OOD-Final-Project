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
        add(new JScrollPane(tablesTable), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        tableNumberField = new JTextField(10);
        seatingCapacityField = new JTextField(10);

        inputPanel.add(new JLabel("Table Number:"));
        inputPanel.add(tableNumberField);
        inputPanel.add(new JLabel("Seating Capacity:"));
        inputPanel.add(seatingCapacityField);

        JButton addButton = new JButton("Add Table");
        addButton.addActionListener(this::addTable);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Table");
        JButton saveButton = new JButton("Save Changes");

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
