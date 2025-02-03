import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String expression = "";
    private final ScriptEngine engine;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display Field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "sqrt", "log", "sin",
            "cos", "tan", "^", "(" , ")"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        // Script Engine for Evaluating Expressions
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("C")) {
            expression = "";
        } else if (command.equals("=")) {
            try {
                expression = expression.replace("^", "**"); // Handle power operator
                Object result = engine.eval(expression);
                expression = result.toString();
            } catch (ScriptException ex) {
                expression = "Error";
            }
        } else if (command.equals("sqrt")) {
            expression += "Math.sqrt(";
        } else if (command.equals("log")) {
            expression += "Math.log(";
        } else if (command.equals("sin")) {
            expression += "Math.sin(";
        } else if (command.equals("cos")) {
            expression += "Math.cos(";
        } else if (command.equals("tan")) {
            expression += "Math.tan(";
        } else {
            expression += command;
        }
        
        display.setText(expression);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculator::new);
    }
}
