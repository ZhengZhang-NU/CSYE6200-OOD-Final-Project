package main.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditMenuPanel extends JDialog {
    private JTable menuTable;
    private DefaultTableModel model;

    public EditMenuPanel(Dialog owner) {
        super(owner, "Edit Menu", true);
        setSize(500, 300);
        model = new DefaultTableModel(new Object[]{"ID", "Dish Name", "Price"}, 0);
        menuTable = new JTable(model);
        add(new JScrollPane(menuTable), BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveChanges());
        add(saveButton, BorderLayout.SOUTH);


        loadMenuData();
    }

    private void loadMenuData() {

        model.addRow(new Object[]{"1", "Chicken Soup", "8.99"});
        model.addRow(new Object[]{"2", "Beef Stew", "11.50"});
    }

    private void saveChanges() {

    }
}
