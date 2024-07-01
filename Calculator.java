import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame implements ActionListener {
    // Define components
    private TextField tfDisplay;
    private Button[] btnNumbers;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnClear;
    private Panel panel;

    private String operator;
    private double num1, num2, result;

    public Calculator() {
        // Frame properties
        setTitle("AWT Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());
        

        // Initialize display text field
        tfDisplay = new TextField();
        add(tfDisplay, BorderLayout.NORTH);

        // Initialize buttons
        btnNumbers = new Button[10];
        for (int i = 0; i < 10; i++) {
            btnNumbers[i] = new Button(String.valueOf(i));
            btnNumbers[i].addActionListener(this);
        }
        btnAdd = new Button("+");
        btnSubtract = new Button("-");
        btnMultiply = new Button("*");
        btnDivide = new Button("/");
        btnEquals = new Button("=");
        btnClear = new Button("C");

        btnAdd.addActionListener(this);
        btnSubtract.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnDivide.addActionListener(this);
        btnEquals.addActionListener(this);
        btnClear.addActionListener(this);

        // Add buttons to panel
        panel = new Panel();
        panel.setLayout(new GridLayout(4, 5));

        // Add number buttons
        for (int i = 1; i < 10; i++) {
            panel.add(btnNumbers[i]);
        }

        panel.add(btnAdd);
        panel.add(btnNumbers[0]);
        panel.add(btnSubtract);
        panel.add(btnMultiply);
        panel.add(btnDivide);
        panel.add(btnEquals);
        panel.add(btnClear);

        add(panel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                tfDisplay.setText(tfDisplay.getText() + command);
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                num1 = Double.parseDouble(tfDisplay.getText());
                operator = command;
                tfDisplay.setText("");
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(tfDisplay.getText());
                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> {
                        if (num2 == 0) {
                            tfDisplay.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                    }
                }
                tfDisplay.setText(String.valueOf(result));
            } else if (command.equals("C")) {
                tfDisplay.setText("");
            }
        } catch (NumberFormatException nfe) {
            tfDisplay.setText("Error");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
