package br.com.biblioteca.dao;

import br.com.biblioteca.config.Conexao;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmprestimoDAO {

    private static final Logger logger = Logger.getLogger(EmprestimoDAO.class.getName());
    private final Connection conn;

    public EmprestimoDAO() {
        this.conn = Conexao.getConexao();
    }

    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, emprestimo.getDataDevolucao() != null ? Date.valueOf(emprestimo.getDataDevolucao()) : null);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Erro ao registrar empréstimo: " + e.getMessage());
            return false;
        }
    }

    public List<Emprestimo> listarEmprestimos() {
        String sql = "SELECT * FROM emprestimos";
        List<Emprestimo> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id"));
                // Aqui seria ideal recuperar Usuario e Livro por seus DAOs
                e.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                if (rs.getDate("data_devolucao") != null) {
                    e.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
                }
                lista.add(e);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public boolean registrarDevolucao(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(2, emprestimo.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Erro ao registrar devolução: " + e.getMessage());
            return false;
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearEmprestimo(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar empréstimos: " + e.getMessage());
        }

        return lista;
    }

    public List<Emprestimo> buscarPorUsuario(int usuarioId) {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos WHERE usuario_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearEmprestimo(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar empréstimos por usuário: " + e.getMessage());
        }

        return lista;
    }
    
    private Emprestimo mapearEmprestimo(ResultSet rs) throws SQLException {
    Emprestimo e = new Emprestimo();
    e.setId(rs.getInt("id"));

    // Para carregar corretamente o usuário e livro, seria ideal usar seus DAOs.
    Usuario usuario = new Usuario();
    usuario.setId(rs.getInt("usuario_id"));
    e.setUsuario(usuario);

    Livro livro = new Livro();
    livro.setId(rs.getInt("livro_id"));
    e.setLivro(livro);

    e.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());

    Date dataDevolucao = rs.getDate("data_devolucao");
    if (dataDevolucao != null) {
        e.setDataDevolucao(dataDevolucao.toLocalDate());
    }

    return e;
}

}
