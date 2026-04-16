package com.bintang.kasir.components;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HeaderPanel extends javax.swing.JPanel {

    // 1. Buat Interface Listener
    public interface SearchListener {
        void onSearch(String query);
    }

    private SearchListener searchListener;

    public void setSearchListener(SearchListener listener) {
        this.searchListener = listener;
    }
    
    // Tambahkan interface baru
    public interface BarcodeListener {
        void onBarcodeScanned(String code);
    }
    
    private BarcodeListener barcodeListener;
    
    public void setBarcodeListener(BarcodeListener listener) {
        this.barcodeListener = listener;
    }
    
    public HeaderPanel() {
        initComponents();
        
        searchInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { triggerSearch(); }
            @Override
            public void removeUpdate(DocumentEvent e) { triggerSearch(); }
            @Override
            public void changedUpdate(DocumentEvent e) { triggerSearch(); }

            private void triggerSearch() {
                if (searchListener != null) {
                    searchListener.onSearch(searchInput.getText());
                }
            }
        });
        
        // Event Klik pada scanBtn (JLabel)
        scanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarcodeScannerDialog dialog = new BarcodeScannerDialog(
                    (Frame) SwingUtilities.getWindowAncestor(HeaderPanel.this),
                    result -> {
                        if (barcodeListener != null) {
                            barcodeListener.onBarcodeScanned(result);
                        }
                    }
                );
                dialog.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLeft = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelRight = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        orderNumber = new javax.swing.JLabel();
        scanBtn = new javax.swing.JLabel();
        panelCenter = new javax.swing.JPanel();
        customPanel1 = new com.bintang.kasir.components.CustomPanel();
        customPanel2 = new com.bintang.kasir.components.CustomPanel();
        jLabel3 = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 231, 235), 1, true));
        setPreferredSize(new java.awt.Dimension(0, 70));
        setLayout(new java.awt.BorderLayout());

        panelLeft.setBackground(new java.awt.Color(255, 255, 255));
        panelLeft.setPreferredSize(new java.awt.Dimension(160, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cashier_logo.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(24, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 122, 40));
        jLabel1.setText("KasirKu");
        jLabel1.setPreferredSize(new java.awt.Dimension(70, 70));

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );

        add(panelLeft, java.awt.BorderLayout.WEST);

        panelRight.setBackground(new java.awt.Color(250, 250, 250));
        panelRight.setPreferredSize(new java.awt.Dimension(320, 0));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/order-icon.png"))); // NOI18N

        orderNumber.setBackground(new java.awt.Color(255, 255, 255));
        orderNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderNumber.setForeground(new java.awt.Color(31, 41, 55));
        orderNumber.setText("Order Panel");

        scanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/scan-icon.png"))); // NOI18N
        scanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(scanBtn)
                .addGap(14, 14, 14))
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(orderNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelRight, java.awt.BorderLayout.EAST);

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCenter.setPreferredSize(new java.awt.Dimension(0, 70));
        panelCenter.setLayout(new java.awt.BorderLayout());

        customPanel1.setBackground(new java.awt.Color(243, 244, 246));
        customPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        customPanel1.setRoundBottomLeft(40);
        customPanel1.setRoundBottomRight(40);
        customPanel1.setRoundTopLeft(40);
        customPanel1.setRoundTopRight(40);
        customPanel1.setLayout(new java.awt.BorderLayout());

        customPanel2.setBackground(new java.awt.Color(255, 255, 255));
        customPanel2.setRoundBottomLeft(40);
        customPanel2.setRoundBottomRight(40);
        customPanel2.setRoundTopLeft(40);
        customPanel2.setRoundTopRight(40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/search-logo.png"))); // NOI18N

        searchInput.setBackground(new java.awt.Color(255, 255, 255));
        searchInput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchInput.setBorder(null);

        javax.swing.GroupLayout customPanel2Layout = new javax.swing.GroupLayout(customPanel2);
        customPanel2.setLayout(customPanel2Layout);
        customPanel2Layout.setHorizontalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        customPanel2Layout.setVerticalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(searchInput))
                .addContainerGap())
        );

        customPanel1.add(customPanel2, java.awt.BorderLayout.CENTER);

        panelCenter.add(customPanel1, java.awt.BorderLayout.EAST);

        add(panelCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bintang.kasir.components.CustomPanel customPanel1;
    private com.bintang.kasir.components.CustomPanel customPanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelRight;
    private javax.swing.JLabel scanBtn;
    private javax.swing.JTextField searchInput;
    // End of variables declaration//GEN-END:variables
}
