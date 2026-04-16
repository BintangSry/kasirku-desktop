package com.bintang.kasir;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatClientProperties;

public class Kasir {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        UIManager.put("ScrollBar.width", 12);              
        UIManager.put("ScrollBar.thumbArc", 999);          
        UIManager.put("ScrollBar.trackArc", 999);       
        UIManager.put("ScrollBar.showButtons", false);     
        UIManager.put("ScrollBar.minimumThumbSize", new Dimension(8, 40));
        new com.bintang.kasir.ui.LoginForm().setVisible(true);
    }
}
