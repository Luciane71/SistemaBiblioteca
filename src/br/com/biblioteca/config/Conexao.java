package br.com.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "LSsantin1911$";

    private static Connection conn;

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Torna o construtor público
    public Conexao() {}

    public static Connection getConexao() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}


