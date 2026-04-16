package com.bintang.kasir.components;

import com.bintang.kasir.config.connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class ReportPanel extends javax.swing.JPanel {
    
    private BaseCrudPanel crudPanel;
    private JLabel lblTotalRevenue; // Untuk menampilkan total pendapatan

    public ReportPanel() {
        setLayout(new BorderLayout());

        crudPanel = new BaseCrudPanel();
        crudPanel.setTitle("Laporan Penjualan");

         // 1. Sembunyikan tombol yang tidak perlu
        crudPanel.getDeleteBtn().setVisible(false);
        crudPanel.getEditBtn().setVisible(false);
        
        // 2. PINDAHKAN addBtn ke CENTER agar tampilannya bagus (tidak kepotong)
        JPanel rightPanel = crudPanel.getRightPanel();
        rightPanel.add(crudPanel.getAddBtn(), BorderLayout.CENTER); // Pindah posisi ke Center

        // 3. Setting Tombol Cetak
        crudPanel.getAddBtn().setText("CETAK LAPORAN");
        crudPanel.getAddBtn().setBackground(new Color(52, 152, 219));
        // Kita tidak perlu setPreferredSize lebar lagi karena CENTER akan otomatis mengisi ruang
        crudPanel.getAddBtn().setPreferredSize(new Dimension(0, 0)); 

        // Tambahkan aksi untuk tombol Cetak
        crudPanel.setCrudHandler(new CrudHandler() {
            @Override
            public void onAdd() {
                printReport(); // Panggil fungsi cetak
            }
            @Override
            public void onEdit(int id) {}
            @Override
            public void onDelete(int id) {}
        });

        add(crudPanel, BorderLayout.CENTER);

        // --- TAMBAHKAN PANEL SUMMARY DI BAWAH UNTUK TOTAL PENDAPATAN ---
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        lblTotalRevenue = new JLabel("Total Pendapatan: Rp 0");
        lblTotalRevenue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalRevenue.setForeground(new Color(31,41,55));

        summaryPanel.add(lblTotalRevenue);
        add(summaryPanel, BorderLayout.SOUTH);

        loadReportData();
    }

    private void loadReportData() {
        long grandTotal = 0; // Menyimpan total uang masuk

        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            
            // Ambil data transaksi beserta nama kasir
            String sql = "SELECT t.transaction_code, u.username, t.total, t.created_at " +
                         "FROM transactions t " +
                         "JOIN users u ON t.user_id = u.id " +
                         "ORDER BY t.created_at DESC";
                         
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Kode TRX", "Tanggal Waktu", "Nama Kasir", "Nominal Belanja"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Tabel murni read-only
                }
            };

            while (rs.next()) {
                int totalBelanja = rs.getInt("total");
                grandTotal += totalBelanja; // Tambahkan ke Grand Total

                model.addRow(new Object[]{
                        rs.getString("transaction_code"),
                        rs.getString("created_at"),
                        rs.getString("username"),
                        "Rp " + totalBelanja
                });
            }

            crudPanel.getTable().setModel(model);

            // Update label total pendapatan di bawah tabel
            lblTotalRevenue.setText("Total Pendapatan: Rp " + grandTotal);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat laporan: " + e.getMessage());
        }
    }

    // Fungsi canggih bawaan Java Swing untuk mencetak tabel ke PDF/Printer
    private void printReport() {
        try {
            // Setup Header dan Footer untuk kertas print
            MessageFormat header = new MessageFormat("Laporan Penjualan KasirKu");
            MessageFormat footer = new MessageFormat("Halaman {0}");

            // Tampilkan dialog Print
            boolean complete = crudPanel.getTable().print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
            if (complete) {
                JOptionPane.showMessageDialog(this, "Laporan berhasil dicetak!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Proses cetak dibatalkan.", "Info", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak: " + pe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
