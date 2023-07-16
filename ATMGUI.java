import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//JFrame is a gui window that adds components to
/**
 * Represents a simple ATM GUI application.
 */
public class ATMGUI extends JFrame {
    private JLabel balanceLabel;
    private JTextField amountField;
    private JPasswordField passwordField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;


    private double balance = 1000; // Initial balance
    private String password = "1111"; // Password for access

    public ATMGUI() {
        setTitle("Sheryar Yasir ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //It will exit out of the application when we hit the X button
        setLayout(new FlowLayout());


        balanceLabel = new JLabel("Current Balance: $" + balance);
        amountField = new JTextField(10);
        passwordField = new JPasswordField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");

        // Deposit button action listener
       depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordCorrect()) {
                    try {
                        double amount = Double.parseDouble(amountField.getText());
                        balance += amount;
                        balanceLabel.setText("Current Balance: $" + balance);
                        amountField.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                    }
                } else {
                    showIncorrectPasswordMessage();
                }
            }
        });

        // Withdraw button action listener
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (isPasswordCorrect()) {
                    try {
                        double amount = Double.parseDouble(amountField.getText());
                        if (amount <= balance) {
                            balance -= amount;
                            balanceLabel.setText("Current Balance: $" + balance);
                            amountField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient balance.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                    }
                } else {
                    showIncorrectPasswordMessage();
                }
            }
        });
        
        // Check Balance button action listener
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordCorrect()) {
                    JOptionPane.showMessageDialog(null, "Current Balance: $" + balance);
                } else {
                    showIncorrectPasswordMessage();
                }
            }
        });

        add(balanceLabel);
        add(amountField);
        add(passwordField);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);

        pack(); //to adjust the size accordingly
        setVisible(true); //to make the GUI window visible on the screen.
        // Without this, the window would be created but not shown.
    }
    /**
     * Checks whether the entered password is correct.
     *
     * @return true if the entered password is correct, false otherwise.
     */

    private boolean isPasswordCorrect() {
        String enteredPassword = new String(passwordField.getPassword());
        return enteredPassword.equals(password);
    }

    private void showIncorrectPasswordMessage() {
        JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
        passwordField.setText("");  //will remove incorrect password by itself
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new ATMGUI();//the ATMGUI class is a custom class that represents the graphical user interface
                // of the ATM application. By instantiating this class, the GUI of the ATM is displayed and the
                // program's execution continues.

            }
        });
    }
}

