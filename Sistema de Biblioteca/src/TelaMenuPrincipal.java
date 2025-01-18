import javax.swing.*;

public class TelaMenuPrincipal extends JFrame {

    public TelaMenuPrincipal() {
        setTitle("Menu Principal - Sistema de Biblioteca");
        setSize(400, 500);  // Aumentei o tamanho para acomodar todos os botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Botões existentes
        JButton btnCadastroLivros = new JButton("Cadastro de Livros");
        btnCadastroLivros.setBounds(100, 50, 200, 30);
        add(btnCadastroLivros);

        JButton btnEmprestimos = new JButton("Empréstimos");
        btnEmprestimos.setBounds(100, 100, 200, 30);
        add(btnEmprestimos);

        JButton btnDevolucoes = new JButton("Devoluções");
        btnDevolucoes.setBounds(100, 150, 200, 30);
        add(btnDevolucoes);

        JButton btnCadastroUsuario = new JButton("Cadastro de Usuários");
        btnCadastroUsuario.setBounds(100, 200, 200, 30);
        add(btnCadastroUsuario);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 250, 200, 30); // Novo botão de login
        add(btnLogin);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 300, 200, 30);
        add(btnSair);

        // Novos botões para a lista de livros e lista de usuários
        JButton btnListaLivros = new JButton("Lista de Livros");
        btnListaLivros.setBounds(100, 350, 200, 30);
        add(btnListaLivros);

        JButton btnListaUsuarios = new JButton("Lista de Usuários");
        btnListaUsuarios.setBounds(100, 400, 200, 30);
        add(btnListaUsuarios);

        // Ações dos botões
        btnCadastroLivros.addActionListener(e -> new TelaCadastroLivros().setVisible(true));
        btnEmprestimos.addActionListener(e -> new TelaEmprestimos().setVisible(true));
        btnDevolucoes.addActionListener(e -> new TelaDevolucoes().setVisible(true));
        btnCadastroUsuario.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));
        btnLogin.addActionListener(e -> new TelaLogin().setVisible(true)); // Abre a tela de login
        btnSair.addActionListener(e -> dispose());

        // Ações para as novas telas
        btnListaLivros.addActionListener(e -> new TelaListaLivros().setVisible(true)); // Abre a tela de Lista de Livros
        btnListaUsuarios.addActionListener(e -> new TelaListaUsuarios().setVisible(true)); // Abre a tela de Lista de Usuários

        setLocationRelativeTo(null); // Centraliza a janela
    }

    public static void main(String[] args) {
        new TelaMenuPrincipal().setVisible(true);
    }
}
