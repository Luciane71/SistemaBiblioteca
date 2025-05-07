package br.com.biblioteca.dao;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.config.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LivroDAO {

    private static final Logger logger = Logger.getLogger(LivroDAO.class.getName());
    private final Connection conn;

    public LivroDAO() {
        this.conn = Conexao.getConexao();
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearLivro(rs);
                }
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar livro por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titulo + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    livros.add(mapearLivro(rs));
                }
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar livros por título: " + e.getMessage());
        }
        return livros;
    }

    public List<Livro> buscarPorCategoria(String categoria) {
        String sql = "SELECT * FROM livros WHERE categoria LIKE ?";
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + categoria + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    livros.add(mapearLivro(rs));
                }
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar livros por categoria: " + e.getMessage());
        }
        return livros;
    }

    public boolean inserir(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, isbn, categoria) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setString(4, livro.getCategoria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Erro ao inserir livro: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, isbn = ?, categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setString(4, livro.getCategoria());
            stmt.setInt(5, livro.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livros.add(mapearLivro(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    private Livro mapearLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setIsbn(rs.getString("isbn"));
        livro.setCategoria(rs.getString("categoria"));
        return livro;
    }
}
