package com.bintang.kasir.components;

import com.bintang.kasir.model.CartItem;
import java.awt.Window;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingUtilities;

public class OrderPanel extends javax.swing.JPanel {
    
    private Map<Integer, CartItem> cartMap = new LinkedHashMap<>();
    
    public interface CheckoutListener {
        void onCheckoutSuccess();
    }

    private CheckoutListener checkoutListener;

    public void setCheckoutListener(CheckoutListener listener) {
        this.checkoutListener = listener;
    }

    public OrderPanel() {
        initComponents();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        updateCartUI();
    }
    
    // Fungsi untuk menambah barang dari MainPanel
    public void addItemToCart(int id, String name, int price, int maxStock, String image) {
        
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        if (maxStock < 1) {
            JOptionPane.showMessageDialog(parentWindow, "Maaf, stok habis!");
            return;
        }

        // Jika barang sudah ada, tambah QTY saja. Jika belum, buat baru.
        if (cartMap.containsKey(id)) {
            CartItem item = cartMap.get(id);
            if (item.qty < item.maxStock) {
                item.qty++;
            } else {
                JOptionPane.showMessageDialog(parentWindow, "Stok maksimal " + maxStock);
            }
        } else {
            cartMap.put(id, new CartItem(id, name, price, 1, maxStock, image));
        }
        updateCartUI();
    }

    // Fungsi merender ulang UI keranjang dan hitung total
    private void updateCartUI() {
        
        jPanel2.removeAll(); // Bersihkan list
        
        int totalItems = 0;
        int totalPrice = 0;

        for (CartItem item : cartMap.values()) {
            // Hitung total
            totalItems += item.qty;
            totalPrice += (item.price * item.qty);

            // Buat panel item baru
            OrderItemPanel panel = new OrderItemPanel(item, new OrderItemPanel.CartActionListener() {
                @Override
                public void onQtyChanged(int id, int newQty) {
                    cartMap.get(id).qty = newQty;
                    updateCartUI(); // Update total harga
                }

                @Override
                public void onDelete(int id) {
                    cartMap.remove(id);
                    updateCartUI(); // Update tampilan
                }
            });
            jPanel2.add(panel);
            jPanel2.add(Box.createVerticalStrut(10));
        }

        // Update Label Total (Sesuaikan dengan nama variabel JLabel kamu)
        itemLabel.setText(totalItems + " Items");
        priceLabel.setText("Rp " + totalPrice);

        jPanel2.revalidate();
        jPanel2.repaint();
    }
    
    private void processCheckout() {
        
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        if (cartMap.isEmpty()) {
            JOptionPane.showMessageDialog(parentWindow, "Keranjang masih kosong!");
            return;
        }

        // 1. Hitung Grand Total dan buat struk sementara
        int grandTotal = 0;
        StringBuilder orderSummary = new StringBuilder("Detail Pesanan:\n\n");
        for (CartItem item : cartMap.values()) {
            int subtotal = item.price * item.qty;
            grandTotal += subtotal;
            orderSummary.append("- ").append(item.name)
                        .append(" x").append(item.qty)
                        .append(" = Rp ").append(subtotal).append("\n");
        }
        orderSummary.append("\nTotal Bayar: Rp ").append(grandTotal);

        // 2. Tampilkan Konfirmasi Pesanan
        int confirm = JOptionPane.showConfirmDialog(parentWindow, orderSummary.toString(), "Konfirmasi Pesanan", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // Batal order
        }

        // 3. Input Uang Pelanggan
        String inputUang = JOptionPane.showInputDialog(parentWindow, "Masukkan jumlah uang pelanggan (Total: Rp " + grandTotal + "):");
        if (inputUang == null || inputUang.trim().isEmpty()) return;

        int paidAmount = 0;
        try {
            paidAmount = Integer.parseInt(inputUang);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentWindow, "Nominal uang tidak valid! Masukkan angka saja.");
            return;
        }

        if (paidAmount < grandTotal) {
            JOptionPane.showMessageDialog(parentWindow, "Uang tidak cukup! Kurang Rp " + (grandTotal - paidAmount));
            return;
        }

        int changeAmount = paidAmount - grandTotal;

        // 4. Proses ke Database
        saveTransactionToDatabase(grandTotal, paidAmount, changeAmount);
    }

    private void saveTransactionToDatabase(int total, int paid, int change) {
        
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        Connection conn = null;
        try {
            conn = com.bintang.kasir.config.connection.getConnection();
            conn.setAutoCommit(false); // MULAI TRANSAKSI (Mencegah data setengah masuk)

            // A. Simpan ke tabel transactions
            String trxCode = "TRX" + System.currentTimeMillis(); // Generate kode TRX unik
            int userId = 1; // Asumsi ID User yang login adalah 1. (Nanti bisa diganti dinamis)
            
            String sqlTrx = "INSERT INTO transactions (transaction_code, user_id, total, paid, change_amount, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
            PreparedStatement psTrx = conn.prepareStatement(sqlTrx, Statement.RETURN_GENERATED_KEYS);
            psTrx.setString(1, trxCode);
            psTrx.setInt(2, userId);
            psTrx.setInt(3, total);
            psTrx.setInt(4, paid);
            psTrx.setInt(5, change);
            psTrx.executeUpdate();

            // Ambil ID Transaksi yang baru saja terbuat
            ResultSet rsTrx = psTrx.getGeneratedKeys();
            int trxId = 0;
            if (rsTrx.next()) {
                trxId = rsTrx.getInt(1);
            }

            // B. Siapkan Query untuk Details dan Potong Stok
            String sqlDetail = "INSERT INTO transaction_details (transaction_id, menu_id, price, qty, subtotal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psDetail = conn.prepareStatement(sqlDetail);

            String sqlUpdateStock = "UPDATE menu SET stock = stock - ? WHERE id = ?";
            PreparedStatement psUpdateStock = conn.prepareStatement(sqlUpdateStock);

            // C. Looping isi keranjang untuk dimasukkan ke database
            for (CartItem item : cartMap.values()) {
                int subtotal = item.price * item.qty;

                // Set parameter untuk transaction_details
                psDetail.setInt(1, trxId);
                psDetail.setInt(2, item.id);
                psDetail.setInt(3, item.price);
                psDetail.setInt(4, item.qty);
                psDetail.setInt(5, subtotal);
                psDetail.addBatch(); // Kumpulkan query

                // Set parameter untuk potong stok menu
                psUpdateStock.setInt(1, item.qty);
                psUpdateStock.setInt(2, item.id);
                psUpdateStock.addBatch(); // Kumpulkan query
            }

            // Eksekusi semua query yang dikumpulkan
            psDetail.executeBatch();
            psUpdateStock.executeBatch();

            conn.commit(); // SIMPAN SEMUA PERUBAHAN KE DATABASE

            // D. Tampilkan Pesan Sukses dan Kembalian
            JOptionPane.showMessageDialog(parentWindow, "Transaksi Berhasil!\nKembalian: Rp " + change, "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // E. Kosongkan Keranjang
            cartMap.clear();
            updateCartUI();

            // F. Beritahu Dashboard untuk Me-refresh Menu
            if (checkoutListener != null) {
                checkoutListener.onCheckoutSuccess();
            }

        } catch (Exception e) {
            // JIKA TERJADI ERROR, BATALKAN SEMUA PERUBAHAN DATABASE (Rollback)
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentWindow, "Terjadi kesalahan sistem: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        customPanel3 = new com.bintang.kasir.components.CustomPanel();
        orderBtn = new rojerusan.RSMaterialButtonRectangle();
        itemLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        customPanel3.setBackground(new java.awt.Color(255, 122, 40));
        customPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        customPanel3.setPreferredSize(new java.awt.Dimension(0, 80));
        customPanel3.setRoundBottomLeft(20);
        customPanel3.setRoundBottomRight(20);
        customPanel3.setRoundTopLeft(20);
        customPanel3.setRoundTopRight(20);

        orderBtn.setBackground(new java.awt.Color(255, 255, 255));
        orderBtn.setForeground(new java.awt.Color(255, 122, 40));
        orderBtn.setText("Order");
        orderBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        orderBtn.setPreferredSize(new java.awt.Dimension(130, 50));
        orderBtn.addActionListener(this::orderBtnActionPerformed);

        itemLabel.setForeground(new java.awt.Color(255, 255, 255));
        itemLabel.setText("10 items");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Rp. 100.000");

        javax.swing.GroupLayout customPanel3Layout = new javax.swing.GroupLayout(customPanel3);
        customPanel3.setLayout(customPanel3Layout);
        customPanel3Layout.setHorizontalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemLabel)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(orderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        customPanel3Layout.setVerticalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customPanel3Layout.createSequentialGroup()
                        .addComponent(itemLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceLabel))
                    .addComponent(orderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(customPanel3, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setBackground(new java.awt.Color(250, 250, 250));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void orderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderBtnActionPerformed
        processCheckout();
    }//GEN-LAST:event_orderBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bintang.kasir.components.CustomPanel customPanel3;
    private javax.swing.JLabel itemLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle orderBtn;
    private javax.swing.JLabel priceLabel;
    // End of variables declaration//GEN-END:variables
}
