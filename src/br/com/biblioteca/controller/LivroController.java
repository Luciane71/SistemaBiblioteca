package br.com.biblioteca.controller;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Livro;

import java.util.List;

public class LivroController {

    private final LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    public boolean salvar(Livro livro) {
        return livroDAO.inserir(livro);
    }

    public List<Livro> listarLivros() {
        return livroDAO.listar();
    }

    public Livro buscarLivroPorId(int id) {
        return livroDAO.buscarPorId(id);
    }
}

