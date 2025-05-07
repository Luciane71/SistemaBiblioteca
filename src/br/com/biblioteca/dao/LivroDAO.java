package dao;

import beans.Livros;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {

    private Conexao conexao;
    private Connection conn;

    public LivrosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public Livros getLivros(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Livros livros = new Livros();

            if (rs.next()) {
                livros.setId(rs.getInt("id"));
                livros.setTitulo(rs.getString("titulo"));
                livros.setAutor(rs.getString("autor"));
                livros.setISBN(rs.getString("isbn"));
                livros.setCategoria(rs.getString("categoria"));
            }

            return livros;
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
            return null;
        }
    }

    public List<Livros> getLivros(String titulo) {
        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            List<Livros> listaLivros = new ArrayList<>();
            while (rs.next()) {
                Livros livros = new Livros();
                livros.setId(rs.getInt("id"));
                livros.setTitulo(rs.getString("titulo"));
                livros.setAutor(rs.getString("autor"));
                livros.setISBN(rs.getString("isbn"));
                livros.setCategoria(rs.getString("categoria"));
                listaLivros.add(livros);
            }

            return listaLivros;
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
            return null;
        }
    }

    public void inserir(Livros livros) {
        String sql = "INSERT INTO livros (titulo, autor, isbn, categoria) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, livros.getTitulo());
            stmt.setString(2, livros.getAutor());
            stmt.setString(3, livros.getISBN());
            stmt.setString(4, livros.getCategoria());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public void editar(Livros livros) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, isbn = ?, categoria = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, livros.getTitulo());
            stmt.setString(2, livros.getAutor());
            stmt.setString(3, livros.getISBN());
            stmt.setString(4, livros.getCategoria());
            stmt.setInt(5, livros.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao editar livro: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }

    public List<Livros> getLivrosPorCategoria(String categoria) {
        String sql = "SELECT * FROM livros WHERE categoria LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + categoria + "%");
            ResultSet rs = stmt.executeQuery();

            List<Livros> listaLivros = new ArrayList<>();
            while (rs.next()) {
                Livros livros = new Livros();
                livros.setId(rs.getInt("id"));
                livros.setTitulo(rs.getString("titulo"));
                livros.setAutor(rs.getString("autor"));
                livros.setISBN(rs.getString("isbn"));
                livros.setCategoria(rs.getString("categoria"));
                listaLivros.add(livros);
            }

            return listaLivros;
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros por categoria: " + e.getMessage());
            return null;
        }
    }
}
