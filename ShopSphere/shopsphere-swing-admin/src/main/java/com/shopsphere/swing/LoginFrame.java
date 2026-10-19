package com.shopsphere.swing;

import com.shopsphere.common.model.User;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginFrame extends JFrame {
    private final JTextField email = new JTextField(24);
    private final JPasswordField password = new JPasswordField(24);
    private final LoginController controller = new LoginController();

    public LoginFrame() {
        super("ShopSphere Admin Sign In"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(430, 270); setLocationRelativeTo(null);
        JPanel form = new JPanel(new GridLayout(0, 1, 8, 8)); form.setBorder(BorderFactory.createEmptyBorder(26, 32, 18, 32));
        form.add(new JLabel("Administrator email")); form.add(email); form.add(new JLabel("Password")); form.add(password);
        JButton signIn = new JButton("Sign in"); signIn.addActionListener(event -> authenticate()); form.add(signIn);
        add(form, BorderLayout.CENTER);
    }
    private void authenticate() {
        char[] secret = password.getPassword();
        try {
            User user = controller.login(email.getText(), secret).orElseThrow(() -> new IllegalArgumentException("Email or password is incorrect."));
            dispose(); new AdminDashboard(user).setVisible(true);
        } catch (Exception ex) { SwingSupport.error(this, ex); }
        finally { Arrays.fill(secret, '\0'); password.setText(""); }
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true)); }
}
