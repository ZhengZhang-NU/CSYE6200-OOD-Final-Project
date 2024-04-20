package main.ui;



import main.business.CSVUtils;

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
        setSize(600, 500);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Dish Name", "Price", "Description"}, 0);
        menuTable = new JTable(model);
        add(new JScrollPane(menuTable), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
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
        addButton.addActionListener(this::addMenuItem);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save Changes");

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
