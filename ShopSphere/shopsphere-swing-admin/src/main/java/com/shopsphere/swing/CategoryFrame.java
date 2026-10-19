package com.shopsphere.swing;

import com.shopsphere.common.model.Category;
import com.shopsphere.service.ShopSphereServices;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CategoryFrame extends JPanel {
    private final ShopSphereServices services = new ShopSphereServices();
    private final JTextField name = new JTextField();
    private final JTextField description = new JTextField();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Active"}, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };
    private final JTable table = new JTable(model);
    private List<Category> rows = List.of();
    private long selectedId;
    public CategoryFrame() {
        super(new BorderLayout(8, 8)); JPanel actions = new JPanel(); JButton add = new JButton("New"); add.addActionListener(event -> clear()); JButton save = new JButton("Save category"); save.addActionListener(event -> save()); JButton deactivate = new JButton("Deactivate"); deactivate.addActionListener(event -> deactivate()); actions.add(add); actions.add(save); actions.add(deactivate); add(actions, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel form = new JPanel(new GridLayout(1, 4, 6, 4)); form.add(new JLabel("Name")); form.add(name); form.add(new JLabel("Description")); form.add(description); add(form, BorderLayout.SOUTH);
        table.getSelectionModel().addListSelectionListener(event -> { if (!event.getValueIsAdjusting() && table.getSelectedRow() >= 0) select(table.getSelectedRow()); }); reload();
    }
    private void reload() { try { rows = services.admin().categories(); model.setRowCount(0); for (Category c : rows) model.addRow(new Object[]{c.getCategoryId(), c.getName(), c.getDescription(), c.isActive()}); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void select(int row) { Category c = rows.get(row); selectedId = c.getCategoryId(); name.setText(c.getName()); description.setText(c.getDescription()); }
    private void clear() { selectedId = 0; table.clearSelection(); name.setText(""); description.setText(""); }
    private void save() { try { Category c = new Category(selectedId, name.getText(), description.getText(), true); services.admin().saveCategory(c); reload(); clear(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void deactivate() { int row = table.getSelectedRow(); if (row >= 0) try { services.admin().deactivateCategory(rows.get(row).getCategoryId()); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
}
