package com.bintang.kasir.components;

import com.bintang.kasir.config.connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockPanel extends javax.swing.JPanel {

    private BaseCrudPanel crudPanel;

    public StockPanel() {
        setLayout(new BorderLayout());

        crudPanel = new BaseCrudPanel();
        crudPanel.setTitle("Manajemen Stok Barang");
        
        // MODIFIKASI TOMBOL KHUSUS UNTUK HALAMAN STOK
        crudPanel.getAddBtn().setVisible(false);    // Sembunyikan tombol Tambah
        crudPanel.getDeleteBtn().setVisible(false); // Sembunyikan tombol Delete
        crudPanel.getEditBtn().setText("Update Stok"); // Ubah teks tombol Edit
        crudPanel.getEditBtn().setPreferredSize(new Dimension(130, 0)); // Lebarkan sedikit

        add(crudPanel, BorderLayout.CENTER);

        crudPanel.setCrudHandler(new CrudHandler() {
            @Override
            public void onAdd() { /* Tidak dipakai */ }

            @Override
            public void onDelete(int id) { /* Tidak dipakai */ }

            @Override
            public void onEdit(int id) {
                // Saat tombol Update Stok diklik
                openStockUpdateDialog(id);
            }
        });

        loadStockData();
    }

    private void loadStockData() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            
            // Urutkan dari stok yang paling sedikit (ASC)
            String sql = "SELECT id, name, category, stock FROM menu ORDER BY stock ASC";
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID Menu", "Nama Produk", "Kategori", "Sisa Stok"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Tabel read-only
                }
            };

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("stock")
                });
            }

            crudPanel.getTable().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openStockUpdateDialog(int id) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT name, stock FROM menu WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String menuName = rs.getString("name");
                int currentStock = rs.getInt("stock");

                // Kita pakai GenericFormDialog untuk input stok baru
                String[] cols = {"Stok Baru (" + menuName + ")"};
                String[] types = {"text"};

                GenericFormDialog dialog = new GenericFormDialog(null, "Update Stok", cols, types);
                
                // Isi form dengan stok saat ini
                Map<String, Object> data = new LinkedHashMap<>();
                data.put(cols[0], currentStock);
                dialog.setFormData(data);

                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    Map<String, Object> result = dialog.getFormData();
                    try {
                        int newStock = Integer.parseInt(result.get(cols[0]).toString());
                        
                        if(newStock < 0) {
                            JOptionPane.showMessageDialog(this, "Stok tidak boleh minus!");
                            return;
                        }

                        // Simpan stok baru ke database
                        PreparedStatement updatePs = conn.prepareStatement("UPDATE menu SET stock=? WHERE id=?");
                        updatePs.setInt(1, newStock);
                        updatePs.setInt(2, id);
                        updatePs.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Stok berhasil diupdate!");
                        loadStockData(); // Refresh tabel

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
