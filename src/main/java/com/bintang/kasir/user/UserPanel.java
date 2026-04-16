package com.bintang.kasir.user;

import com.bintang.kasir.components.BaseCrudPanel;
import com.bintang.kasir.components.CrudHandler;
import com.bintang.kasir.components.GenericFormDialog;
import com.bintang.kasir.config.connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserPanel extends javax.swing.JPanel {
    
    private BaseCrudPanel crudPanel;

    public UserPanel() {
        setLayout(new BorderLayout());

        crudPanel = new BaseCrudPanel();
        add(crudPanel, BorderLayout.CENTER);

        crudPanel.setCrudHandler(new CrudHandler() {

            @Override
            public void onAdd() {
                String[] cols = {"username", "password", "role"};
                // Sesuaikan tipe datanya. Kasih dropdown untuk role
                String[] types = {"text", "text", "combo:admin,kasir"};

                GenericFormDialog dialog = new GenericFormDialog(null, "Tambah User", cols, types);
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    insertData("users", dialog.getFormData());
                    loadData();
                }
            }

            @Override
            public void onEdit(int id) {
                String[] cols = {"username", "password", "role"};
                String[] types = {"text", "text", "combo:admin,kasir"};

                GenericFormDialog dialog = new GenericFormDialog(null, "Edit User", cols, types);

                Map<String, Object> data = getDataById("users", id);
                data.put("password", ""); // Kosongkan password lama

                dialog.setFormData(data);
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    updateData("users", dialog.getFormData(), id);
                    loadData();
                }
            }

            @Override
            public void onDelete(int id) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin hapus user?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == 0) {
                    deleteData("users", id);
                    loadData();
                }
            }
        });

        loadData();

    }
    
    // ================= LOAD TABLE =================
    private void loadData() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Username", "Role"}, 0
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("role")
                });
            }

            crudPanel.getTable().setModel(model);
            crudPanel.setTitle("Manajemen User");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET DATA =================
    private Map<String, Object> getDataById(String table, int id) {
        Map<String, Object> data = new LinkedHashMap<>();

        try {
            Connection conn = connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                data.put("username", rs.getString("username"));
                data.put("password", "");
                data.put("role", rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // ================= INSERT =================
    private void insertData(String table, Map<String, Object> data) {
        try {
            Connection conn = connection.getConnection();

            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, data.get("username"));
            ps.setObject(2, data.get("password"));
            ps.setObject(3, data.get("role"));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    private void updateData(String table, Map<String, Object> data, int id) {
        try {
            Connection conn = connection.getConnection();

            String sql = "UPDATE users SET username=?, password=?, role=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, data.get("username"));
            ps.setObject(2, data.get("password"));
            ps.setObject(3, data.get("role"));
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    private void deleteData(String table, int id) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();

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
