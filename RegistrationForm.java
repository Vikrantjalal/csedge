import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RegistrationForm extends Frame implements ActionListener {
    Label nameLabel, genderLabel, countryLabel;
    TextField nameField;
    CheckboxGroup genderGroup;
    Checkbox male, female;
    Choice countryChoice;
    Button submitButton, displayButton, exportButton;
    TextArea displayArea;
    ArrayList<User> users = new ArrayList<>();

    public RegistrationForm() {
        // Set layout manager
        setLayout(new FlowLayout());

        // Initialize components
        nameLabel = new Label("Name:");
        nameField = new TextField(20);
        genderLabel = new Label("Gender:");
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        countryLabel = new Label("Country:");
        countryChoice = new Choice();
        submitButton = new Button("Submit");
        displayButton = new Button("Display Data");
        exportButton = new Button("Export Data");
        displayArea = new TextArea(10, 40);

        // Add countries to choice
        countryChoice.add("India ");
        countryChoice.add("Germany");
        countryChoice.add("Portugal");
        countryChoice.add("Argentina");
        countryChoice.add("Brazil");

        // Add components to frame
        add(nameLabel);
        add(nameField);
        add(genderLabel);
        add(male);
        add(female);
        add(countryLabel);
        add(countryChoice);
        add(submitButton);
        add(displayButton);
        add(exportButton);
        add(displayArea);

        // Set frame properties
        setTitle("User Registration Form");
        setSize(500, 400);
        setVisible(true);

        // Add action listeners
        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        exportButton.addActionListener(this);

        // Add window listener for closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            // Get data from form
            String name = nameField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String country = countryChoice.getSelectedItem();

            // Create new user and add to list
            User user = new User(name, gender, country);
            users.add(user);

            // Clear form
            nameField.setText("");
            genderGroup.setSelectedCheckbox(male);
            countryChoice.select(0);

        } else if (ae.getSource() == displayButton) {
            // Display data
            displayArea.setText("");
            for (User user : users) {
                displayArea.append(user.toString() + "\n");
            }
        } else if (ae.getSource() == exportButton) {
            // Export data to file
            try (FileWriter writer = new FileWriter("user_data.txt")) {
                for (User user : users) {
                    writer.write(user.toString() + "\n");
                }
                displayArea.setText("Data exported successfully!");
            } catch (IOException e) {
                displayArea.setText("Error exporting data.");
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}

class User implements Serializable {
    private String name;
    private String gender;
    private String country;

    public User(String name, String gender, String country) {
        this.name = name;
        this.gender = gender;
        this.country = country;
    }

    public String toString() {
        return "Name: " + name + ", Gender: " + gender + ", Country: " + country;
    }
}
