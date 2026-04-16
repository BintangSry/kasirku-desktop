package com.bintang.kasir.menu;

import com.bintang.kasir.components.BaseCrudPanel;
import com.bintang.kasir.components.CrudHandler;
import com.bintang.kasir.components.GenericFormDialog;
import com.bintang.kasir.config.connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuPanel extends javax.swing.JPanel {
    
    private BaseCrudPanel crudPanel;
    
    public MenuPanel() {
        setLayout(new BorderLayout());

        crudPanel = new BaseCrudPanel();
        add(crudPanel, BorderLayout.CENTER);

        // 🔥 SET HANDLER
        crudPanel.setCrudHandler(new CrudHandler() {

            @Override
            public void onAdd() {
                String[] cols = {"name", "price", "stock", "category", "barcode", "image"};
                String[] types = {"text", "text", "text", "combo:makanan,minuman", "text", "image"};

                GenericFormDialog dialog = new GenericFormDialog(null, "Tambah Menu", cols, types);
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    insertData("menu", dialog.getFormData());
                    loadData();
                }
            }

            @Override
            public void onEdit(int id) {
                String[] cols = {"name", "price", "stock", "category", "barcode", "image"};
                String[] types = {"text", "text", "text", "combo:makanan,minuman", "text", "image"};

                GenericFormDialog dialog = new GenericFormDialog(null, "Edit Menu", cols, types);

                Map<String, Object> data = getDataById("menu", id);
                dialog.setFormData(data);

                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    updateData("menu", dialog.getFormData(), id);
                    loadData();
                }
            }

            @Override
            public void onDelete(int id) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin hapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == 0) {
                    deleteData("menu", id);
                    loadData();
                }
            }
        });

        loadData(); // 🔥 tampilkan data awal
    }
    
    // ================= LOAD DATA =================
    private void loadData() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM menu");

            // Custom Model agar kolom tombol "Gambar" bisa diklik
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Nama", "Harga", "Stok", "Kategori", "Barcode", "Gambar"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 6; // Hanya kolom indeks ke-6 (Gambar) yang bisa diklik
                }
            };

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("category"),
                        rs.getString("barcode"),
                        rs.getString("image") // Menyimpan path gambar, tapi nanti dirender jadi tombol
                });
            }

            JTable table = crudPanel.getTable();
            table.setModel(model);

            // Terapkan Custom Renderer dan Editor untuk kolom "Gambar" (Indeks ke-6)
            table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));

            // Sesuaikan lebar kolom agar rapi
            table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
            table.getColumnModel().getColumn(6).setPreferredWidth(80);  // Gambar
            
            crudPanel.setTitle("Manajemen Menu");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET DATA BY ID =================
    private Map<String, Object> getDataById(String table, int id) {
        Map<String, Object> data = new LinkedHashMap<>(); // Pakai LinkedHashMap

        try {
            Connection conn = connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                for (int i = 1; i <= colCount; i++) {
                    String colName = meta.getColumnName(i);
                    data.put(colName, rs.getObject(colName));
                }
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

            StringBuilder col = new StringBuilder();
            StringBuilder val = new StringBuilder();

            for (String key : data.keySet()) {
                col.append(key).append(",");
                val.append("?,");
            }

            col.deleteCharAt(col.length() - 1);
            val.deleteCharAt(val.length() - 1);

            String sql = "INSERT INTO " + table + " (" + col + ") VALUES (" + val + ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            int i = 1;
            for (Object v : data.values()) {
                ps.setObject(i++, v);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    private void updateData(String table, Map<String, Object> data, int id) {
        try {
            Connection conn = connection.getConnection();

            StringBuilder set = new StringBuilder();

            for (String key : data.keySet()) {
                set.append(key).append("=?,");
            }

            set.deleteCharAt(set.length() - 1);

            String sql = "UPDATE " + table + " SET " + set + " WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            int i = 1;
            for (Object v : data.values()) {
                ps.setObject(i++, v);
            }

            ps.setInt(i, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    private void deleteData(String table, int id) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ====================================================================
    // INNER CLASS: Custom Cell Renderer untuk menggambar tombol "Lihat"
    // ====================================================================
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(52, 152, 219)); // Warna biru cerah
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Lihat");
            return this;
        }
    }

    // ====================================================================
    // INNER CLASS: Custom Cell Editor untuk menangani aksi klik tombol
    // ====================================================================
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String imagePath;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(41, 128, 185)); // Biru agak gelap saat ditekan
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 12));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (value != null) {
                imagePath = value.toString();
            } else {
                imagePath = "";
            }
            button.setText("Lihat");
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                showImagePreview(imagePath); // Panggil fungsi preview gambar
            }
            isPushed = false;
            return imagePath;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    // ====================================================================
    // FUNGSI UNTUK MENAMPILKAN DIALOG PREVIEW GAMBAR
    // ====================================================================
    private void showImagePreview(String path) {
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        if (path == null || path.isEmpty()) {
            JOptionPane.showMessageDialog(parentWindow, "Tidak ada gambar untuk menu ini.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog previewDialog = new JDialog(SwingUtilities.getWindowAncestor(parentWindow), "Preview Gambar", Dialog.ModalityType.APPLICATION_MODAL);
        previewDialog.setLayout(new BorderLayout());
        previewDialog.getContentPane().setBackground(Color.WHITE);

        try {
            // Mencoba me-load gambar dari resources atau local file
            ImageIcon icon = null;
            java.net.URL imgURL = getClass().getResource(path);
            
            if (imgURL != null) {
                icon = new ImageIcon(imgURL);
            } else {
                // Fallback jika dijalankan via IDE sebelum di-build
                String projectPath = System.getProperty("user.dir");
                icon = new ImageIcon(projectPath + "/src/main/resources" + path);
            }

            // Skala gambar agar muat di modal jika terlalu besar
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
            imgLabel.setHorizontalAlignment(JLabel.CENTER);
            imgLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            previewDialog.add(imgLabel, BorderLayout.CENTER);

        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Gambar tidak ditemukan!");
            errorLabel.setHorizontalAlignment(JLabel.CENTER);
            previewDialog.add(errorLabel, BorderLayout.CENTER);
        }

        previewDialog.setSize(300, 320);
        previewDialog.setLocationRelativeTo(this);
        previewDialog.setVisible(true);
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
