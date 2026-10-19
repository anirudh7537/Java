package com.shopsphere.swing;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

final class SwingSupport {
    private SwingSupport() { }
    static void error(Component parent, Exception exception) {
        String message = exception instanceof IllegalArgumentException ? exception.getMessage() : "ShopSphere could not complete this operation. Check database configuration.";
        JOptionPane.showMessageDialog(parent, message, "ShopSphere", JOptionPane.ERROR_MESSAGE);
    }
    static void info(Component parent, String message) { JOptionPane.showMessageDialog(parent, message, "ShopSphere", JOptionPane.INFORMATION_MESSAGE); }
    static void onEdt(Runnable task) { if (SwingUtilities.isEventDispatchThread()) task.run(); else SwingUtilities.invokeLater(task); }
}
