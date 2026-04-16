package com.bintang.kasir.components;

import com.bintang.kasir.config.connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionPanel extends javax.swing.JPanel {

    private BaseCrudPanel crudPanel;

    public TransactionPanel() {
        setLayout(new BorderLayout());

        crudPanel = new BaseCrudPanel();
        crudPanel.setTitle("Riwayat Transaksi");
        
        crudPanel.getAddBtn().setVisible(false);
        crudPanel.getDeleteBtn().setVisible(false);
        crudPanel.getEditBtn().setVisible(false);

        add(crudPanel, BorderLayout.CENTER);

        loadTransactionData();
    }

    private void loadTransactionData() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            
            // Query JOIN dengan users untuk mengambil nama kasir
            String sql = "SELECT t.id, t.transaction_code, u.username, t.total, t.paid, t.change_amount, t.created_at " +
                         "FROM transactions t " +
                         "JOIN users u ON t.user_id = u.id " +
                         "ORDER BY t.created_at DESC";
                         
            ResultSet rs = st.executeQuery(sql);

            // Kolom "Aksi" untuk tombol Detail
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Kode TRX", "Kasir", "Total", "Dibayar", "Kembalian", "Tanggal", "Aksi"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 7; // Hanya kolom Aksi yang bisa diklik
                }
            };

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("transaction_code"),
                        rs.getString("username"),
                        "Rp " + rs.getInt("total"),
                        "Rp " + rs.getInt("paid"),
                        "Rp " + rs.getInt("change_amount"),
                        rs.getString("created_at"),
                        rs.getInt("id") // Kirim ID transaksi ke kolom Aksi untuk dicari detailnya
                });
            }

            JTable table = crudPanel.getTable();
            table.setModel(model);

            // Terapkan Renderer dan Editor untuk Tombol Detail
            table.getColumnModel().getColumn(7).setCellRenderer(new DetailButtonRenderer());
            table.getColumnModel().getColumn(7).setCellEditor(new DetailButtonEditor(new JCheckBox()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================================
    // INNER CLASS: Tombol Detail Renderer & Editor
    // ==========================================
    class DetailButtonRenderer extends JButton implements TableCellRenderer {
        public DetailButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(46, 204, 113)); // Warna hijau
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Detail");
            return this;
        }
    }

    class DetailButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private int transactionId;
        private boolean isPushed;

        public DetailButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Detail");
            button.setOpaque(true);
            button.setBackground(new Color(39, 174, 96));
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
                transactionId = Integer.parseInt(value.toString());
            }
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                showTransactionDetail(transactionId);
            }
            isPushed = false;
            return transactionId;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    // ==========================================
    // FUNGSI MENAMPILKAN MODAL DETAIL TRANSAKSI
    // ==========================================
    private void showTransactionDetail(int trxId) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Detail Transaksi", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(Color.WHITE);

        // Buat tabel untuk detail item
        JTable detailTable = new JTable();
        detailTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detailTable.setRowHeight(30);
        detailTable.getTableHeader().setBackground(new Color(255, 122, 40));
        detailTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableModel detailModel = new DefaultTableModel(
                new String[]{"Item Menu", "Harga", "Qty", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        try {
            Connection conn = connection.getConnection();
            String sql = "SELECT m.name, td.price, td.qty, td.subtotal " +
                         "FROM transaction_details td " +
                         "JOIN menu m ON td.menu_id = m.id " +
                         "WHERE td.transaction_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, trxId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                detailModel.addRow(new Object[]{
                        rs.getString("name"),
                        "Rp " + rs.getInt("price"),
                        rs.getInt("qty"),
                        "Rp " + rs.getInt("subtotal")
                });
            }

            detailTable.setModel(detailModel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(detailTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);

        dialog.add(scrollPane, BorderLayout.CENTER);

        // Tombol Tutup
        JButton btnClose = new JButton("Tutup");
        btnClose.setBackground(new Color(231, 76, 60));
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dialog.dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(btnClose);

        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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
