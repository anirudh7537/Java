package com.shopsphere.swing;

import com.shopsphere.common.model.Order;
import com.shopsphere.service.ShopSphereServices;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderFrame extends JPanel {
    private final ShopSphereServices services = new ShopSphereServices();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Customer", "Placed", "Status", "Payment", "Total"}, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };
    private final JTable table = new JTable(model);
    private final JComboBox<String> status = new JComboBox<>(new String[]{"CONFIRMED", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED"});
    private List<Order> rows = List.of();
    public OrderFrame() { super(new BorderLayout(8, 8)); JButton refresh = new JButton("Refresh"); refresh.addActionListener(event -> reload()); JButton update = new JButton("Update status"); update.addActionListener(event -> update()); JPanel actions = new JPanel(); actions.add(status); actions.add(update); actions.add(refresh); add(actions, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER); reload(); }
    private void reload() { try { rows = services.admin().orders(); model.setRowCount(0); for (Order o : rows) model.addRow(new Object[]{o.getOrderId(), o.getUserId(), o.getCreatedAt(), o.getOrderStatus(), o.getPaymentStatus(), o.getTotalAmount()}); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void update() { int row = table.getSelectedRow(); if (row < 0) return; try { services.admin().updateOrder(rows.get(row).getOrderId(), (String) status.getSelectedItem()); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
}
