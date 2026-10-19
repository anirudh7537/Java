package com.shopsphere.swing;

import com.shopsphere.common.model.Product;
import com.shopsphere.service.ShopSphereServices;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class InventoryFrame extends JPanel {
    private final ShopSphereServices services = new ShopSphereServices();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Product", "Category", "On hand"}, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };
    private final JTable table = new JTable(model);
    private final JSpinner quantity = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    private List<Product> rows = List.of();
    public InventoryFrame() { super(new BorderLayout(8, 8)); JPanel actions = new JPanel(); actions.add(new JLabel("Set on-hand quantity:")); actions.add(quantity); JButton update = new JButton("Update selected"); update.addActionListener(event -> update()); JButton refresh = new JButton("Refresh"); refresh.addActionListener(event -> reload()); actions.add(update); actions.add(refresh); add(actions, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER); table.getSelectionModel().addListSelectionListener(event -> { if (!event.getValueIsAdjusting() && table.getSelectedRow() >= 0) quantity.setValue(rows.get(table.getSelectedRow()).getStock()); }); reload(); }
    private void reload() { try { rows = services.admin().products(null, null, "name"); model.setRowCount(0); for (Product p : rows) model.addRow(new Object[]{p.getProductId(), p.getName(), p.getCategoryName(), p.getStock()}); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void update() { int row = table.getSelectedRow(); if (row < 0) return; try { services.admin().updateStock(rows.get(row).getProductId(), (Integer) quantity.getValue()); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
}
