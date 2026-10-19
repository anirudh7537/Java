package com.shopsphere.swing;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public final class SwingAdminMain {
    private SwingAdminMain() { }
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { System.err.println("Using the default Swing Look and Feel: " + ex.getMessage()); }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
