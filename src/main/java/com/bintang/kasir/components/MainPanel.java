package com.bintang.kasir.components;

import com.bintang.kasir.config.connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MainPanel extends javax.swing.JPanel {
    
    private MenuItemPanel.MenuClickListener menuListener;

    public void setMenuClickListener(MenuItemPanel.MenuClickListener listener) {
        this.menuListener = listener;
    }

    public MainPanel() {
        initComponents();
        
        menuContainer.setPreferredSize(null);
        menuContainer.setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));
        
        loadMenu();
    }
    
    public void loadMenu() {
        menuContainer.removeAll();

        try {
            Connection conn = com.bintang.kasir.config.connection.getConnection(); 

            Statement st = conn.createStatement();
            String sql = "SELECT id, name, price, image, stock FROM menu WHERE stock > 0"; 

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");

                MenuItemPanel item = new MenuItemPanel(id, name, price, image, stock);

                item.setMenuClickListener((menuId, menuName, menuPrice, menuStock, menuImage) -> {
                    if (menuListener != null) {
                        menuListener.onMenuClicked(menuId, menuName, menuPrice, menuStock, menuImage);
                    }
                });
                
                menuContainer.add(item);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat menu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        menuContainer.revalidate();
        menuContainer.repaint();
    }
    
     public void loadMenuByCategory(String category) {
        menuContainer.removeAll();

        try {
            Connection conn = com.bintang.kasir.config.connection.getConnection(); 
            Statement st = conn.createStatement();
            
            String sql = "SELECT id, name, price, image, stock FROM menu WHERE stock > 0 AND category = '" + category + "'"; 

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");

                MenuItemPanel item = new MenuItemPanel(id, name, price, image, stock);
                
                item.setMenuClickListener((menuId, menuName, menuPrice, menuStock, menuImage) -> {
                    if (menuListener != null) {
                        menuListener.onMenuClicked(menuId, menuName, menuPrice, menuStock, menuImage);
                    }
                });
                
                menuContainer.add(item);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat menu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        menuContainer.revalidate();
        menuContainer.repaint();
    }
     
    public void loadMenuBySearch(String query) {
        menuContainer.removeAll();

        try {
            Connection conn = com.bintang.kasir.config.connection.getConnection(); 
            // Gunakan LIKE untuk pencarian fleksibel
            String sql = "SELECT id, name, price, image, stock FROM menu WHERE name LIKE ? AND stock > 0"; 
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + query + "%"); // Cari yang mengandung kata tersebut

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");

                MenuItemPanel item = new MenuItemPanel(id, name, price, image, stock);

                // JANGAN LUPA: Pasang lagi listener kliknya agar bisa masuk keranjang
                item.setMenuClickListener((menuId, menuName, menuPrice, menuStock, menuImage) -> {
                    if (menuListener != null) {
                        menuListener.onMenuClicked(menuId, menuName, menuPrice, menuStock, menuImage);
                    }
                });

                menuContainer.add(item);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        menuContainer.revalidate();
        menuContainer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuContainer = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(245, 246, 250));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuContainer.setBackground(new java.awt.Color(245, 246, 250));
        menuContainer.setPreferredSize(new java.awt.Dimension(0, 0));
        menuContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        jScrollPane1.setViewportView(menuContainer);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menuContainer;
    // End of variables declaration//GEN-END:variables
}
