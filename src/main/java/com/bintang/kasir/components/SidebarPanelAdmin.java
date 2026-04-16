package com.bintang.kasir.components;

import com.bintang.kasir.ui.LoginForm;
import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SidebarPanelAdmin extends javax.swing.JPanel {

    private NavigationHandler navigationHandler;
    
    public void setNavigationHandler(NavigationHandler handler) {
        this.navigationHandler = handler;
    }
    
    public SidebarPanelAdmin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dashboardBtn = new javax.swing.JButton();
        menuBtn = new javax.swing.JButton();
        transactionBtn = new javax.swing.JButton();
        stockBtn = new javax.swing.JButton();
        UserBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 231, 235), 1, true));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 1));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 0));

        jLabel1.setBackground(new java.awt.Color(31, 41, 55));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/hamburger-icon.png"))); // NOI18N
        jLabel1.setText("Admin Sidebar Menu");

        dashboardBtn.setBackground(new java.awt.Color(102, 102, 102));
        dashboardBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dashboardBtn.setForeground(new java.awt.Color(107, 114, 128));
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setBorder(null);
        dashboardBtn.setBorderPainted(false);
        dashboardBtn.setContentAreaFilled(false);
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setFocusPainted(false);
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dashboardBtn.addActionListener(this::dashboardBtnActionPerformed);

        menuBtn.setBackground(new java.awt.Color(102, 102, 102));
        menuBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBtn.setForeground(new java.awt.Color(107, 114, 128));
        menuBtn.setText("Menu");
        menuBtn.setBorder(null);
        menuBtn.setBorderPainted(false);
        menuBtn.setContentAreaFilled(false);
        menuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuBtn.setFocusPainted(false);
        menuBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        menuBtn.addActionListener(this::menuBtnActionPerformed);

        transactionBtn.setBackground(new java.awt.Color(102, 102, 102));
        transactionBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        transactionBtn.setForeground(new java.awt.Color(107, 114, 128));
        transactionBtn.setText("Transaksi");
        transactionBtn.setBorder(null);
        transactionBtn.setBorderPainted(false);
        transactionBtn.setContentAreaFilled(false);
        transactionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transactionBtn.setFocusPainted(false);
        transactionBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transactionBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        transactionBtn.addActionListener(this::transactionBtnActionPerformed);

        stockBtn.setBackground(new java.awt.Color(102, 102, 102));
        stockBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        stockBtn.setForeground(new java.awt.Color(107, 114, 128));
        stockBtn.setText("Stok");
        stockBtn.setBorder(null);
        stockBtn.setBorderPainted(false);
        stockBtn.setContentAreaFilled(false);
        stockBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stockBtn.setFocusPainted(false);
        stockBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        stockBtn.addActionListener(this::stockBtnActionPerformed);

        UserBtn.setBackground(new java.awt.Color(102, 102, 102));
        UserBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UserBtn.setForeground(new java.awt.Color(107, 114, 128));
        UserBtn.setText("User");
        UserBtn.setBorder(null);
        UserBtn.setBorderPainted(false);
        UserBtn.setContentAreaFilled(false);
        UserBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UserBtn.setFocusPainted(false);
        UserBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UserBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        UserBtn.addActionListener(this::UserBtnActionPerformed);

        reportBtn.setBackground(new java.awt.Color(102, 102, 102));
        reportBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reportBtn.setForeground(new java.awt.Color(107, 114, 128));
        reportBtn.setText("Laporan");
        reportBtn.setBorder(null);
        reportBtn.setBorderPainted(false);
        reportBtn.setContentAreaFilled(false);
        reportBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportBtn.setFocusPainted(false);
        reportBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        reportBtn.addActionListener(this::reportBtnActionPerformed);

        logoutBtn.setBackground(new java.awt.Color(102, 102, 102));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(107, 114, 128));
        logoutBtn.setText("Logout");
        logoutBtn.setBorder(null);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transactionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 334, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);

        int confirm = JOptionPane.showConfirmDialog(
            window,
            "Apakah kamu yakin ingin logout?",
            "Logout",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            window.dispose();
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void UserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserBtnActionPerformed
        if (navigationHandler != null) {
            navigationHandler.showUser();
        }
    }//GEN-LAST:event_UserBtnActionPerformed

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        if (navigationHandler != null) {
            navigationHandler.showMenu();
        }
    }//GEN-LAST:event_menuBtnActionPerformed

    private void transactionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionBtnActionPerformed
        if (navigationHandler != null) {
            navigationHandler.showTransaction();
        }
    }//GEN-LAST:event_transactionBtnActionPerformed

    private void stockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBtnActionPerformed
        if (navigationHandler != null) {
            navigationHandler.showStock();
        }
    }//GEN-LAST:event_stockBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        navigationHandler.showReport();
    }//GEN-LAST:event_reportBtnActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        if (navigationHandler != null) {
            navigationHandler.showDashboard();
        }
    }//GEN-LAST:event_dashboardBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UserBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton menuBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JButton stockBtn;
    private javax.swing.JButton transactionBtn;
    // End of variables declaration//GEN-END:variables
}
