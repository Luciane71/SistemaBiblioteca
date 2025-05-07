package dao;

import beans.Usuario;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }


    public Usuario getUsuario(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Usuario usuario = new Usuario();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCPF(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipodeusuario(rs.getString("tipodeusuario"));
            }
            return usuario;

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    public List<Usuario> getUsuario(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCPF(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipodeusuario(rs.getString("tipodeusuario"));
                listaUsuarios.add(usuario);
            }
            return listaUsuarios;

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuários: " + e.getMessage());
            return null;
        }
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, CPF, email, senha, tipodeusuario, papelfuncionario) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCPF());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipodeusuario());
            stmt.setString(6, usuario.getPapelfuncionario());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE (email = ? OR nome = ?) AND senha = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, usuario);
            stmt.setString(3, senha);

            ResultSet rs = stmt.executeQuery();

            // Se houver algum resultado, significa que as credenciais estão corretas
            return rs.next();

        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }

        return false; // Se não encontrar nenhum usuário, retorna false
    }
    
        
    public void editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, senha = ?, tipodeusuario = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCPF());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipodeusuario());
            stmt.setInt(6, usuario.getId());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao editar usuário: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public List<Usuario> getUsuariosPorId(int id1, int id2) {
        String sql = "SELECT * FROM usuario WHERE id >= ? AND id <= ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id1);
            stmt.setInt(2, id2);
            ResultSet rs = stmt.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCPF(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipodeusuario(rs.getString("tipodeusuario"));
                listaUsuarios.add(usuario);
            }
            return listaUsuarios;

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuários por ID: " + e.getMessage());
            return null;
        }
        
        
    }
}
