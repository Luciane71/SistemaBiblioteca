package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import conexao.Conexao; // Aqui está sua classe para obter a conexão com o banco de dados
import beans.Emprestimo; // Sua classe de modelo para empréstimos

public class EmprestimoDAO {
    
    private Connection conn;

    public EmprestimoDAO() {
        this.conn = new Conexao().getConexao();
    }

    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (usuario, livro, data_emprestimo) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emprestimo.getUsuario());
            stmt.setString(2, emprestimo.getLivro());
            stmt.setString(3, emprestimo.getDataEmprestimo());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
            return false;
        }
    }
}

