package br.com.biblioteca.view;

import br.com.biblioteca.config.Conexao;
import javax.swing.*;
import java.sql.*;

public class TelaEmprestimos extends JFrame {

    private JTextField txtUsuario;
    private JTextField txtLivro;
    private JTextField txtDataEmprestimo;

    public TelaEmprestimos() {
        setTitle("Empréstimos de Livros");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário ID:");
        lblUsuario.setBounds(20, 30, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 200, 25);
        add(txtUsuario);

        JLabel lblLivro = new JLabel("Livro ID:");
        lblLivro.setBounds(20, 70, 100, 25);
        add(lblLivro);

        txtLivro = new JTextField();
        txtLivro.setBounds(120, 70, 200, 25);
        add(txtLivro);

        JLabel lblDataEmprestimo = new JLabel("Data do Empréstimo:");
        lblDataEmprestimo.setBounds(20, 110, 120, 25);
        add(lblDataEmprestimo);

        txtDataEmprestimo = new JTextField();
        txtDataEmprestimo.setBounds(160, 110, 160, 25);
        add(txtDataEmprestimo);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(120, 150, 100, 30);
        add(btnConfirmar);

        btnConfirmar.addActionListener(e -> registrarEmprestimo());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(240, 150, 100, 30);
        add(btnVoltar);
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        setLocationRelativeTo(null);
    }

    private void registrarEmprestimo() {
        int usuarioId = Integer.parseInt(txtUsuario.getText());
        int livroId = Integer.parseInt(txtLivro.getText());
        String dataEmprestimo = txtDataEmprestimo.getText();

        // Inserir no banco de dados
        String sql = "INSERT INTO emprestimo (usuario_idUsuario, cadastrodelivros_idLivro, data_emprestimo) VALUES (?, ?, ?)";
        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, livroId);
            stmt.setString(3, dataEmprestimo);

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao registrar empréstimo.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    private void voltarMenuPrincipal() {
        dispose();
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaEmprestimos().setVisible(true);
    }
}
