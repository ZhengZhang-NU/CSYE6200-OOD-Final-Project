package main.ui;

import main.business.Person;
import main.business.PersonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonCreationPanel extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> typeComboBox;
    private JButton createButton;
    private JTextArea infoArea;

    public PersonCreationPanel() {
        super("Person Factory Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        nameField = new JTextField(10);
        phoneField = new JTextField(10);
        typeComboBox = new JComboBox<>(new String[]{"Customer", "Employee"});
        createButton = new JButton("Create");
        infoArea = new JTextArea(5, 20);
        infoArea.setEditable(false);

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(new JLabel("Type:"));
        add(typeComboBox);
        add(createButton);
        add(new JScrollPane(infoArea));

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPerson();
            }
        });

        setVisible(true);
    }

    private void createPerson() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String type = (String) typeComboBox.getSelectedItem();
        try {
            Person person = PersonFactory.createPerson(type, name, phone, "123");  // Assuming ID is hardcoded for simplicity
            infoArea.setText("Created: " + person.getClass().getSimpleName() + "\nName: " + person.getName() + "\nPhone: " + person.getPhoneNumber());
        } catch (IllegalArgumentException e) {
            infoArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new PersonCreationPanel();
    }
}
