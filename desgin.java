import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JTextArea;

public class desgin extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public desgin() {
        // Set up the frame
        setTitle("desgin Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize text area and add to frame
        textArea = new JTextArea();
        textArea.setFont(new Font("vikrant", Font.PLAIN, 14));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Initialize file chooser
        fileChooser = new JFileChooser();

        // Set up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Set up file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Add menu items
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add action listeners
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage());
            }
        }
    }

    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
                writer.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                desgin editor = new desgin();
                editor.setVisible(true);
            }
        });
    }
}



