package com.shopsphere.swing;

import com.shopsphere.common.model.User;
import com.shopsphere.service.ShopSphereServices;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends JPanel {
    private final ShopSphereServices services = new ShopSphereServices();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Mobile", "Active"}, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };
    private final JTable table = new JTable(model);
    private List<User> rows = List.of();
    public CustomerFrame() { super(new BorderLayout(8, 8)); JButton reload = new JButton("Refresh"); reload.addActionListener(event -> reload()); JButton toggle = new JButton("Activate / deactivate selected"); toggle.addActionListener(event -> toggle()); JPanel actions = new JPanel(); actions.add(reload); actions.add(toggle); add(actions, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER); reload(); }
    private void reload() { try { rows = services.admin().customers(); model.setRowCount(0); for (User u : rows) model.addRow(new Object[]{u.getUserId(), u.getName(), u.getEmail(), u.getMobile(), u.isActive()}); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void toggle() { int row = table.getSelectedRow(); if (row < 0) return; try { User user = rows.get(row); services.admin().setCustomerActive(user.getUserId(), !user.isActive()); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
}
