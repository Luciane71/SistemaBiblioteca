package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Usuario;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarPorId(id); // ✅ Aqui você resolve o erro
    }

    public boolean salvar(Usuario usuario) {
        return usuarioDAO.salvar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }
}
