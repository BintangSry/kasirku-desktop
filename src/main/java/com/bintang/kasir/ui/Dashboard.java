package com.bintang.kasir.ui;

import com.bintang.kasir.components.NavigationHandler;
import com.bintang.kasir.components.SidebarPanel;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Dashboard.class.getName());

    public Dashboard() {
        this.setUndecorated(true);
        
        initComponents();
        setLocationRelativeTo(null);
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
        
        mainPanel2.setMenuClickListener((id, name, price, stock, image) -> {
            orderPanel1.addItemToCart(id, name, price, stock, image);
        });
        
        orderPanel1.setCheckoutListener(() -> {
            mainPanel2.loadMenu(); 
            
            mainPanel2.setMenuClickListener((id, name, price, stock, image) -> {
                orderPanel1.addItemToCart(id, name, price, stock, image);
            });
        });
        
        sidebarPanel1.setSidebarListener(new SidebarPanel.SidebarListener() {
            @Override
            public void onCategorySelected(String category) {
                mainPanel2.loadMenuByCategory(category);
            }
            @Override
            public void onDashboardSelected() {
                mainPanel2.loadMenu();
            }
        });
        
        headerPanel1.setSearchListener(new com.bintang.kasir.components.HeaderPanel.SearchListener() {
            @Override
            public void onSearch(String query) {
                // Panggil fungsi search di mainPanel
                mainPanel2.loadMenuBySearch(query);
            }
        });
        
        headerPanel1.setBarcodeListener(code -> {
            try {
                java.sql.Connection conn = com.bintang.kasir.config.connection.getConnection();
                String sql = "SELECT id, name, price, stock, image FROM menu WHERE barcode = ?";
                java.sql.PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, code);
                java.sql.ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    int stock = rs.getInt("stock");
                    String image = rs.getString("image");

                    // MASUKKAN KE KERANJANG
                    orderPanel1.addItemToCart(id, name, price, stock, image);

                    // Opsional: Bunyi Beep sukses
                    java.awt.Toolkit.getDefaultToolkit().beep();
                } else {
                    JOptionPane.showMessageDialog(this, "Barcode tidak terdaftar: " + code);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hedarPanel = new javax.swing.JPanel();
        headerPanel1 = new com.bintang.kasir.components.HeaderPanel();
        sidebarPanel = new javax.swing.JPanel();
        sidebarPanel1 = new com.bintang.kasir.components.SidebarPanel();
        orderPanel = new javax.swing.JPanel();
        orderPanel1 = new com.bintang.kasir.components.OrderPanel();
        mainPanel = new javax.swing.JPanel();
        mainPanel2 = new com.bintang.kasir.components.MainPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - KasirKu");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        hedarPanel.setBackground(new java.awt.Color(255, 255, 255));
        hedarPanel.setPreferredSize(new java.awt.Dimension(0, 70));
        hedarPanel.setLayout(new java.awt.BorderLayout());
        hedarPanel.add(headerPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(hedarPanel, java.awt.BorderLayout.NORTH);

        sidebarPanel.setBackground(new java.awt.Color(255, 255, 255));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(220, 0));
        sidebarPanel.setLayout(new java.awt.BorderLayout());
        sidebarPanel.add(sidebarPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        orderPanel.setBackground(new java.awt.Color(250, 250, 250));
        orderPanel.setPreferredSize(new java.awt.Dimension(320, 0));
        orderPanel.setLayout(new java.awt.BorderLayout());
        orderPanel.add(orderPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(orderPanel, java.awt.BorderLayout.EAST);

        mainPanel.setBackground(new java.awt.Color(245, 246, 250));
        mainPanel.setPreferredSize(new java.awt.Dimension(630, 630));
        mainPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(mainPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bintang.kasir.components.HeaderPanel headerPanel1;
    private javax.swing.JPanel hedarPanel;
    private javax.swing.JPanel mainPanel;
    private com.bintang.kasir.components.MainPanel mainPanel2;
    private javax.swing.JPanel orderPanel;
    private com.bintang.kasir.components.OrderPanel orderPanel1;
    private javax.swing.JPanel sidebarPanel;
    private com.bintang.kasir.components.SidebarPanel sidebarPanel1;
    // End of variables declaration//GEN-END:variables
}
