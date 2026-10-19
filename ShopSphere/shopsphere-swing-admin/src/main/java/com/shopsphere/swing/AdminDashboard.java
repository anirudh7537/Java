package com.shopsphere.swing;

import com.shopsphere.common.model.User;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class AdminDashboard extends JFrame {
    public AdminDashboard(User admin) {
        super("ShopSphere Administration"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(1080, 700); setLocationRelativeTo(null);
        add(new JLabel("ShopSphere  /  Signed in as " + admin.getName(), SwingConstants.LEFT), BorderLayout.NORTH);
        JTabbedPane tabs = new JTabbedPane(); tabs.addTab("Products", new ProductFrame()); tabs.addTab("Categories", new CategoryFrame()); tabs.addTab("Customers", new CustomerFrame()); tabs.addTab("Orders", new OrderFrame()); tabs.addTab("Inventory", new InventoryFrame()); add(tabs, BorderLayout.CENTER);
    }
}
