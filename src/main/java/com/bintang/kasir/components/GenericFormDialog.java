package com.bintang.kasir.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

public class GenericFormDialog extends javax.swing.JDialog {
    
    private Map<String, JComponent> fields = new LinkedHashMap<>();
    private boolean saved = false;
    
    public GenericFormDialog(java.awt.Frame parent, String title, String[] columns, String[] types) {
        super(parent, title, true);
        
        // 1. Setup Tampilan Utama yang Modern
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(Color.WHITE);
        mainContainer.setBorder(new EmptyBorder(20, 30, 20, 30));

        // 2. Setup Form Panel
        JPanel formPanel = new JPanel(new GridLayout(columns.length, 2, 15, 15));
        formPanel.setBackground(Color.WHITE);

        for (int i = 0; i < columns.length; i++) {
            String col = columns[i];
            String type = types[i];

            JLabel label = new JLabel(col.toUpperCase());
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));
            label.setForeground(new Color(100, 100, 100));
            formPanel.add(label);

            // LOGIKA JENIS INPUT
            if (type.equals("text")) {
                JTextField textField = new JTextField();
                styleField(textField);
                fields.put(col, textField);
                formPanel.add(textField);

            } else if (type.startsWith("combo:")) {
                // Contoh format: "combo:Makanan,Minuman"
                String[] items = type.substring(6).split(",");
                JComboBox<String> comboBox = new JComboBox<>(items);
                comboBox.setBackground(Color.WHITE);
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                fields.put(col, comboBox);
                formPanel.add(comboBox);

            } else if (type.equals("image")) {
                // Custom panel untuk upload gambar
                JPanel imagePanel = new JPanel(new BorderLayout(5, 0));
                imagePanel.setBackground(Color.WHITE);
                
                JTextField pathField = new JTextField();
                pathField.setEditable(false);
                styleField(pathField);
                
                JButton btnBrowse = new JButton("Pilih...");
                btnBrowse.setBackground(new Color(52, 152, 219));
                btnBrowse.setForeground(Color.WHITE);
                
                btnBrowse.addActionListener(e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String newPath = copyImageToAssets(selectedFile);
                        if(newPath != null) pathField.setText(newPath);
                    }
                });

                imagePanel.add(pathField, BorderLayout.CENTER);
                imagePanel.add(btnBrowse, BorderLayout.EAST);
                
                fields.put(col, pathField);
                formPanel.add(imagePanel);
            }
        }

        // 3. Setup Tombol Action
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton btnCancel = new JButton("Batal");
        btnCancel.setBackground(new Color(236, 240, 241));
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancel.setPreferredSize(new Dimension(100, 35));
        
        JButton btnSave = new JButton("Simpan");
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSave.setPreferredSize(new Dimension(100, 35));

        btnSave.addActionListener(e -> {
            saved = true;
            dispose();
        });

        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);

        // Gabungkan semuanya
        mainContainer.add(formPanel, BorderLayout.CENTER);
        mainContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(mainContainer);
        
        pack(); // Biarkan Java menghitung tinggi otomatis agar tidak kepotong
        setMinimumSize(new Dimension(450, getHeight() + 10)); // Lebar minimal 450px, tambah sedikit extra tinggi
        setLocationRelativeTo(parent); // Posisi ke tengah layar
    }
    
    // Fungsi bantuan untuk styling TextField
    private void styleField(JTextField field) {
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    // Fungsi otomatis copy gambar ke folder project
    private String copyImageToAssets(File sourceFile) {
        try {
            // Asumsi folder assets ada di project kamu
            String projectPath = System.getProperty("user.dir");
            File destFolder = new File(projectPath + "/src/main/resources/assets");
            
            if (!destFolder.exists()) destFolder.mkdirs(); // Buat folder jika belum ada

            File destFile = new File(destFolder, sourceFile.getName());
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            return "/assets/" + sourceFile.getName(); // Return path untuk disimpan ke DB
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyalin gambar: " + e.getMessage());
            return null;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    // Mengambil data dari komponen
    public Map<String, Object> getFormData() {
        Map<String, Object> data = new LinkedHashMap<>();
        for (String key : fields.keySet()) {
            JComponent comp = fields.get(key);
            if (comp instanceof JTextField) {
                data.put(key, ((JTextField) comp).getText());
            } else if (comp instanceof JComboBox) {
                data.put(key, ((JComboBox<?>) comp).getSelectedItem().toString());
            }
        }
        return data;
    }
    
    // Mengisi form saat tombol Edit diklik
    public void setFormData(Map<String, Object> data) {
        for (String key : data.keySet()) {
            if (fields.containsKey(key)) {
                JComponent comp = fields.get(key);
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setText(String.valueOf(data.get(key)));
                } else if (comp instanceof JComboBox) {
                    ((JComboBox<String>) comp).setSelectedItem(String.valueOf(data.get(key)));
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
