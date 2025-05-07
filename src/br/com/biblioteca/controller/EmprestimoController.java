package br.com.biblioteca.controller;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Emprestimo;

public class EmprestimoController {

    private final EmprestimoDAO emprestimoDAO;

    public EmprestimoController() {
        this.emprestimoDAO = new EmprestimoDAO();
    }

    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        return emprestimoDAO.registrarEmprestimo(emprestimo);
    }

    public boolean registrarDevolucao(Emprestimo emprestimo) {
        return emprestimoDAO.registrarDevolucao(emprestimo);
    }

    public double calcularMulta(long diasAtraso, double valorPorDia) {
        if (diasAtraso <= 0) return 0.0;
        return diasAtraso * valorPorDia;
    }
}


