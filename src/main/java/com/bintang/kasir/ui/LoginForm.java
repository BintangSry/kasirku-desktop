package com.bintang.kasir.ui;

import com.bintang.kasir.config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginForm.class.getName());

    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        customPanel1 = new com.bintang.kasir.components.CustomPanel();
        jSeparator2 = new javax.swing.JSeparator();
        icon = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        customPanel2 = new com.bintang.kasir.components.CustomPanel();
        usernameInput = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        customPanel3 = new com.bintang.kasir.components.CustomPanel();
        passwordInput = new javax.swing.JPasswordField();
        jSeparator5 = new javax.swing.JSeparator();
        loginBtn = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page - KasirKu");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 122, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanel2.setLayout(new java.awt.BorderLayout());

        customPanel1.setBackground(new java.awt.Color(255, 255, 255));
        customPanel1.setPreferredSize(new java.awt.Dimension(500, 0));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setPreferredSize(new java.awt.Dimension(300, 100));
        customPanel1.add(jSeparator2);

        icon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/person-logo.png"))); // NOI18N
        icon.setPreferredSize(new java.awt.Dimension(260, 150));
        customPanel1.add(icon);

        jSeparator6.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setPreferredSize(new java.awt.Dimension(300, 5));
        customPanel1.add(jSeparator6);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(200, 200, 200));
        jLabel2.setText("Username");
        jLabel2.setPreferredSize(new java.awt.Dimension(260, 16));
        customPanel1.add(jLabel2);

        customPanel2.setBackground(new java.awt.Color(204, 204, 204));
        customPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        customPanel2.setPreferredSize(new java.awt.Dimension(265, 40));
        customPanel2.setRoundBottomLeft(30);
        customPanel2.setRoundBottomRight(30);
        customPanel2.setRoundTopLeft(30);
        customPanel2.setRoundTopRight(30);

        usernameInput.setBackground(new java.awt.Color(204, 204, 204));
        usernameInput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        usernameInput.setForeground(new java.awt.Color(102, 102, 102));
        usernameInput.setBorder(null);
        usernameInput.setName("usernameInput"); // NOI18N

        javax.swing.GroupLayout customPanel2Layout = new javax.swing.GroupLayout(customPanel2);
        customPanel2.setLayout(customPanel2Layout);
        customPanel2Layout.setHorizontalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );
        customPanel2Layout.setVerticalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        customPanel1.add(customPanel2);

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setPreferredSize(new java.awt.Dimension(300, 5));
        customPanel1.add(jSeparator4);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(200, 200, 200));
        jLabel3.setText("Password");
        jLabel3.setPreferredSize(new java.awt.Dimension(260, 16));
        customPanel1.add(jLabel3);

        customPanel3.setBackground(new java.awt.Color(204, 204, 204));
        customPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        customPanel3.setPreferredSize(new java.awt.Dimension(265, 40));
        customPanel3.setRoundBottomLeft(30);
        customPanel3.setRoundBottomRight(30);
        customPanel3.setRoundTopLeft(30);
        customPanel3.setRoundTopRight(30);

        passwordInput.setBackground(new java.awt.Color(204, 204, 204));
        passwordInput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        passwordInput.setBorder(null);

        javax.swing.GroupLayout customPanel3Layout = new javax.swing.GroupLayout(customPanel3);
        customPanel3.setLayout(customPanel3Layout);
        customPanel3Layout.setHorizontalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );
        customPanel3Layout.setVerticalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        customPanel1.add(customPanel3);

        jSeparator5.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setPreferredSize(new java.awt.Dimension(300, 5));
        customPanel1.add(jSeparator5);

        loginBtn.setBackground(new java.awt.Color(255, 122, 40));
        loginBtn.setBorder(null);
        loginBtn.setText("LOGIN");
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginBtn.setName("loginBtn"); // NOI18N
        loginBtn.setPreferredSize(new java.awt.Dimension(270, 50));
        loginBtn.addActionListener(this::loginBtnActionPerformed);
        customPanel1.add(loginBtn);

        jPanel2.add(customPanel1, java.awt.BorderLayout.EAST);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login-page.png"))); // NOI18N
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameInput.getText();
        String password = new String(passwordInput.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password wajib diisi!");
            return;
        }

        try {
            Connection conn = connection.getConnection();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role"); 

                if (role.equalsIgnoreCase("admin")) {
                    new DashboardAdmin().setVisible(true);
                } else if (role.equalsIgnoreCase("kasir")) {
                    new Dashboard().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Role tidak dikenali!");
                }

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bintang.kasir.components.CustomPanel customPanel1;
    private com.bintang.kasir.components.CustomPanel customPanel2;
    private com.bintang.kasir.components.CustomPanel customPanel3;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private rojerusan.RSMaterialButtonRectangle loginBtn;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
