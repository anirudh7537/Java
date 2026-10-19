package com.shopsphere.swing;

import com.shopsphere.common.model.Product;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductFrame extends JPanel {
    private final ProductController controller = new ProductController();
    private final JTextField search = new JTextField(18);
    private final JTextField name = new JTextField();
    private final JTextField brand = new JTextField();
    private final JTextField categoryId = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField discount = new JTextField("0");
    private final JTextField stock = new JTextField("0");
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Brand", "Category", "Price", "Stock", "Active"}, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };
    private final JTable table = new JTable(model);
    private List<Product> rows = List.of();
    private long selectedId;

    public ProductFrame() {
        super(new BorderLayout(8, 8)); JPanel toolbar = new JPanel(); JButton filter = new JButton("Search"); filter.addActionListener(event -> reload()); JButton add = new JButton("New product"); add.addActionListener(event -> clearForm()); JButton save = new JButton("Save product"); save.addActionListener(event -> save()); JButton deactivate = new JButton("Deactivate"); deactivate.addActionListener(event -> deactivate()); toolbar.add(search); toolbar.add(filter); toolbar.add(add); toolbar.add(save); toolbar.add(deactivate); add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER); JPanel form = new JPanel(new GridLayout(2, 6, 6, 4)); addField(form, "Name", name); addField(form, "Brand", brand); addField(form, "Category ID", categoryId); addField(form, "Price", price); addField(form, "Discount %", discount); addField(form, "Stock", stock); add(form, BorderLayout.SOUTH);
        table.getSelectionModel().addListSelectionListener(event -> { if (!event.getValueIsAdjusting() && table.getSelectedRow() >= 0) select(table.getSelectedRow()); }); reload();
    }
    private void addField(JPanel panel, String label, JTextField field) { panel.add(new JLabel(label)); panel.add(field); }
    private void reload() { try { rows = controller.search(search.getText()); model.setRowCount(0); for (Product p : rows) model.addRow(new Object[]{p.getProductId(), p.getName(), p.getBrand(), p.getCategoryName(), p.getPrice(), p.getStock(), p.isActive()}); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void select(int index) { Product p = rows.get(index); selectedId = p.getProductId(); name.setText(p.getName()); brand.setText(p.getBrand()); categoryId.setText(Long.toString(p.getCategoryId())); price.setText(p.getPrice().toPlainString()); discount.setText(p.getDiscount().toPlainString()); stock.setText(Integer.toString(p.getStock())); }
    private void clearForm() { selectedId = 0; table.clearSelection(); name.setText(""); brand.setText(""); categoryId.setText(""); price.setText(""); discount.setText("0"); stock.setText("0"); }
    private void save() { try { Product p = new Product(); p.setProductId(selectedId); p.setName(name.getText()); p.setBrand(brand.getText()); p.setCategoryId(Long.parseLong(categoryId.getText())); p.setPrice(new BigDecimal(price.getText())); p.setDiscount(new BigDecimal(discount.getText())); p.setStock(Integer.parseInt(stock.getText())); p.setActive(true); controller.save(p); clearForm(); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
    private void deactivate() { int row = table.getSelectedRow(); if (row < 0) return; try { controller.deactivate(rows.get(row).getProductId()); reload(); } catch (Exception ex) { SwingSupport.error(this, ex); } }
}
