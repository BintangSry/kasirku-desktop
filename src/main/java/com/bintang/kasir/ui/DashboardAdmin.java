package com.bintang.kasir.ui;

import com.bintang.kasir.components.BaseCrudPanel;
import com.bintang.kasir.components.NavigationHandler;
import com.bintang.kasir.components.SidebarPanelAdmin;
import com.bintang.kasir.menu.MenuPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author ACER
 */
public class DashboardAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardAdmin.class.getName());

    public DashboardAdmin() {
        this.setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        setMainPanel(new com.bintang.kasir.components.DashboardHomePanel());
        
        sidebarPanelAdmin1.setNavigationHandler(new NavigationHandler() {

            @Override
            public void showMenu() {
                setMainPanel(new com.bintang.kasir.menu.MenuPanel());
            }

            @Override
            public void showUser() {
                setMainPanel(new com.bintang.kasir.user.UserPanel());
            }

            @Override
            public void showDashboard() {
                setMainPanel(new com.bintang.kasir.components.DashboardHomePanel());
            }
            @Override
            public void showTransaction() {
                setMainPanel(new com.bintang.kasir.components.TransactionPanel()); 
            }
            @Override
            public void showStock() {
                setMainPanel(new com.bintang.kasir.components.StockPanel());
            }
            @Override
            public void showReport() {
                setMainPanel(new com.bintang.kasir.components.ReportPanel());
            }
        });
    }
    
    public void setMainPanel(JPanel panel) {
        mainPanelAdmin.removeAll();
        mainPanelAdmin.setLayout(new BorderLayout());
        mainPanelAdmin.add(panel, BorderLayout.CENTER);
        mainPanelAdmin.revalidate();
        mainPanelAdmin.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hedarPanel = new javax.swing.JPanel();
        headerPanelAdmin2 = new com.bintang.kasir.components.HeaderPanelAdmin();
        sidebarPanel = new javax.swing.JPanel();
        sidebarPanelAdmin1 = new com.bintang.kasir.components.SidebarPanelAdmin();
        mainPanelAdmin = new javax.swing.JPanel();
        mainPanel1 = new com.bintang.kasir.components.MainPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard - KasirKu");

        hedarPanel.setBackground(new java.awt.Color(255, 255, 255));
        hedarPanel.setPreferredSize(new java.awt.Dimension(0, 70));
        hedarPanel.setLayout(new java.awt.BorderLayout());
        hedarPanel.add(headerPanelAdmin2, java.awt.BorderLayout.CENTER);

        getContentPane().add(hedarPanel, java.awt.BorderLayout.NORTH);

        sidebarPanel.setBackground(new java.awt.Color(255, 255, 255));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(220, 0));
        sidebarPanel.setLayout(new java.awt.BorderLayout());
        sidebarPanel.add(sidebarPanelAdmin1, java.awt.BorderLayout.CENTER);

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        mainPanelAdmin.setBackground(new java.awt.Color(245, 246, 250));
        mainPanelAdmin.setPreferredSize(new java.awt.Dimension(780, 630));
        mainPanelAdmin.setLayout(new java.awt.BorderLayout());
        mainPanelAdmin.add(mainPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanelAdmin, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new DashboardAdmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bintang.kasir.components.HeaderPanelAdmin headerPanelAdmin2;
    private javax.swing.JPanel hedarPanel;
    private com.bintang.kasir.components.MainPanel mainPanel1;
    private javax.swing.JPanel mainPanelAdmin;
    private javax.swing.JPanel sidebarPanel;
    private com.bintang.kasir.components.SidebarPanelAdmin sidebarPanelAdmin1;
    // End of variables declaration//GEN-END:variables
}
