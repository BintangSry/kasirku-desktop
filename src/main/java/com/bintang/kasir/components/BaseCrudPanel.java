package com.bintang.kasir.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class BaseCrudPanel extends javax.swing.JPanel {
    
    private CrudHandler handler;
    
    public void setCrudHandler(CrudHandler handler) {
        this.handler = handler;
    }

    public BaseCrudPanel() {
        initComponents();
        styleTable();
    }
    
    private void styleTable() {
        // Basic table
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(45);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setBackground(Color.WHITE);

        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // HEADER
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255,122,40));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(100, 40));

        // RENDERER (zebra + padding)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0
                            ? Color.WHITE
                            : new Color(245, 247, 250));
                    c.setForeground(new Color(60, 60, 60));
                }

                setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
                return c;
            }
        });

        // ALIGNMENT
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(right); // Harga
        table.getColumnModel().getColumn(3).setCellRenderer(right); // Stok

    }
    
    public javax.swing.JTable getTable() {
        return table;
    }
    
    public void setTitle(String newTitle) {
        if (title != null) {
            this.title.setText(newTitle);
        }
    }
    
    public void setTableData(String[] columns, Object[][] data) {
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        deleteBtn = new rojerusan.RSMaterialButtonRectangle();
        addBtn = new rojerusan.RSMaterialButtonRectangle();
        editbtn = new rojerusan.RSMaterialButtonRectangle();
        leftPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(245, 246, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 630));
        jPanel1.setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(245, 246, 250));
        topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 8));
        topPanel.setPreferredSize(new java.awt.Dimension(0, 50));
        topPanel.setLayout(new java.awt.BorderLayout());

        rightPanel.setBackground(new java.awt.Color(245, 246, 250));
        rightPanel.setPreferredSize(new java.awt.Dimension(300, 0));
        rightPanel.setLayout(new java.awt.BorderLayout());

        deleteBtn.setBackground(new java.awt.Color(231, 76, 60));
        deleteBtn.setText("delete");
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setPreferredSize(new java.awt.Dimension(100, 0));
        deleteBtn.addActionListener(this::deleteBtnActionPerformed);
        rightPanel.add(deleteBtn, java.awt.BorderLayout.EAST);

        addBtn.setBackground(new java.awt.Color(46, 204, 113));
        addBtn.setText("Tambah");
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setPreferredSize(new java.awt.Dimension(100, 0));
        addBtn.addActionListener(this::addBtnActionPerformed);
        rightPanel.add(addBtn, java.awt.BorderLayout.WEST);

        editbtn.setBackground(new java.awt.Color(52, 152, 219));
        editbtn.setText("Edit");
        editbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editbtn.setPreferredSize(new java.awt.Dimension(100, 0));
        editbtn.addActionListener(this::editbtnActionPerformed);
        rightPanel.add(editbtn, java.awt.BorderLayout.CENTER);

        topPanel.add(rightPanel, java.awt.BorderLayout.EAST);

        leftPanel.setBackground(new java.awt.Color(245, 246, 250));
        leftPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        leftPanel.setLayout(new java.awt.BorderLayout());

        title.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        title.setForeground(new java.awt.Color(31, 41, 55));
        title.setText("Title");
        leftPanel.add(title, java.awt.BorderLayout.CENTER);

        topPanel.add(leftPanel, java.awt.BorderLayout.CENTER);

        jPanel1.add(topPanel, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBackground(new java.awt.Color(245, 246, 250));
        scrollPane.setBorder(null);
        scrollPane.setForeground(new java.awt.Color(245, 246, 250));

        table.setBackground(new java.awt.Color(245, 246, 250));
        table.setForeground(new java.awt.Color(107, 114, 128));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(table);

        jPanel1.add(scrollPane, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if (handler != null) handler.onAdd();
    }//GEN-LAST:event_addBtnActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) table.getValueAt(row, 0);
            handler.onEdit(id);
        }
    }//GEN-LAST:event_editbtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) table.getValueAt(row, 0);
            handler.onDelete(id);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    public javax.swing.JPanel getRightPanel() {
        return rightPanel;
    }
    
    public JButton getAddBtn() { return addBtn; }
    public JButton getEditBtn() { return editbtn; }
    public JButton getDeleteBtn() { return deleteBtn; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle addBtn;
    private rojerusan.RSMaterialButtonRectangle deleteBtn;
    private rojerusan.RSMaterialButtonRectangle editbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JLabel title;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
