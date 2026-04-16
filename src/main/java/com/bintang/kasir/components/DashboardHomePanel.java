package com.bintang.kasir.components;

import com.bintang.kasir.config.connection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardHomePanel extends javax.swing.JPanel {

    public DashboardHomePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 246, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Buat Wrapper Panel untuk bagian atas (Title + Cards)
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(new Color(245, 246, 250));

        // 2. Label Welcome
        JLabel lblWelcome = new JLabel("Overview Dashboard");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(31, 41, 55));
        lblWelcome.setBorder(new EmptyBorder(0, 0, 20, 0)); // Kasih jarak bawah
        topWrapper.add(lblWelcome, BorderLayout.NORTH);

        // 3. Container Kartu
        JPanel cardContainer = new JPanel(new GridLayout(1, 4, 20, 0));
        cardContainer.setBackground(new Color(245, 246, 250));
        
        // Ambil Data
        long todayRevenue = getTodayRevenue();
        int todayTrx = getTodayTransactionCount();
        int lowStock = getLowStockCount();
        int totalUser = getTotalUserCount();

        // Tambah Kartu (Ukuran kartu akan otomatis proporsional sekarang)
        cardContainer.add(createStatCard("Pendapatan Hari Ini", "Rp " + todayRevenue, new Color(46, 204, 113)));
        cardContainer.add(createStatCard("Transaksi Hari Ini", String.valueOf(todayTrx), new Color(52, 152, 219)));
        cardContainer.add(createStatCard("Stok Hampir Habis", String.valueOf(lowStock), new Color(231, 76, 60)));
        cardContainer.add(createStatCard("Total User", String.valueOf(totalUser), new Color(155, 89, 182)));

        topWrapper.add(cardContainer, BorderLayout.CENTER);

        // MASUKKAN SEMUA KE POSISI NORTH (UTARA) AGAR TIDAK MELAR KE BAWAH
        add(topWrapper, BorderLayout.NORTH);

        // (Opsional) Posisi CENTER sekarang kosong, bisa kamu isi dengan gambar 
        // atau dibiarkan kosong agar terlihat seperti Dashboard modern yang bersih.
        JPanel emptySpace = new JPanel();
        emptySpace.setOpaque(false);
        add(emptySpace, BorderLayout.CENTER);
    }

    private JPanel createStatCard(String title, String value, Color color) {
        CustomPanel card = new CustomPanel();
        card.setBackground(Color.WHITE);
        card.setRoundTopLeft(20);
        card.setRoundTopRight(20);
        card.setRoundBottomLeft(20);
        card.setRoundBottomRight(20);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding dalam kartu
        
        // Kita kunci tinggi maksimal kartu agar terlihat kotak/persegi panjang rapi
        card.setPreferredSize(new Dimension(0, 120)); 

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitle.setForeground(new Color(107, 114, 128));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValue.setForeground(color);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        return card;
    }

    // --- (Fungsi query getTodayRevenue dll tetap sama seperti sebelumnya) ---
    private long getTodayRevenue() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(total) FROM transactions WHERE DATE(created_at) = CURDATE()");
            if (rs.next()) return rs.getLong(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    private int getTodayTransactionCount() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM transactions WHERE DATE(created_at) = CURDATE()");
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    private int getLowStockCount() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM menu WHERE stock < 10");
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    private int getTotalUserCount() {
        try {
            Connection conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM users");
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
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
