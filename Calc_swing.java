import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        display = new JTextField();
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        String[] buttonLabels = {"7", "8", "9", "/",
                                 "4", "5", "6", "*",
                                 "1", "2", "3", "-",
                                 "C", "0", "=", "+"};
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789".contains(command)) {
            display.setText(display.getText() + command);
        } else if ("+-*/".contains(command)) {
            display.setText(display.getText() + " " + command + " ");
        } else if ("C".equals(command)) {
            display.setText("");
        } else if ("=".equals(command)) {
            try {
                display.setText(evaluateExpression(display.getText()));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    private String evaluateExpression(String expression) {
        // Implement this method to evaluate the expression
        // For simplicity, you can use built-in libraries or write your own logic
        return ""; // Placeholder
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}