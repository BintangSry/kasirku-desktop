package com.bintang.kasir.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String URL =
        "jdbc:mysql://localhost:3306/db_kasir?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL tidak ditemukan", e);
        } catch (SQLException e) {
            throw new RuntimeException("Koneksi database gagal: " + e.getMessage(), e);
        }
    }
}
