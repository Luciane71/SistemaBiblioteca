package br.com.biblioteca.dao;

import br.com.biblioteca.config.Conexao;
import br.com.biblioteca.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() {
        this.conn = Conexao.getConnection();
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearUsuario(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
    String sql = "SELECT * FROM usuario WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapearUsuario(rs);
        }
    } catch (SQLException e) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return null;
}


    public boolean salvar(Usuario usuario) {
    String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        return false;
    }
}


    public List<Usuario> listar() {
    List<Usuario> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuario";
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            usuarios.add(mapearUsuario(rs));
        }
    } catch (SQLException e) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return usuarios;
}

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        // outros campos, se houver
        return usuario;
    }

    public boolean autenticar(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void inserir(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
