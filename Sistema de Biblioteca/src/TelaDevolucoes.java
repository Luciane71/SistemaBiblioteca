import conexao.Conexao;
import javax.swing.*;
import java.sql.*;

public class TelaDevolucoes extends JFrame {

    private JTextField txtUsuario, txtLivro, txtDataDevolucao;

    public TelaDevolucoes() {
        setTitle("Devolução de Livros");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(20, 30, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 200, 25);
        add(txtUsuario);

        JLabel lblLivro = new JLabel("Livro:");
        lblLivro.setBounds(20, 70, 100, 25);
        add(lblLivro);

        txtLivro = new JTextField();
        txtLivro.setBounds(120, 70, 200, 25);
        add(txtLivro);

        JLabel lblDataDevolucao = new JLabel("Data de Devolução:");
        lblDataDevolucao.setBounds(20, 110, 120, 25);
        add(lblDataDevolucao);

        txtDataDevolucao = new JTextField();
        txtDataDevolucao.setBounds(140, 110, 180, 25);
        add(txtDataDevolucao);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(120, 160, 100, 30);
        add(btnConfirmar);
        btnConfirmar.addActionListener(e -> registrarDevolucao());

        setLocationRelativeTo(null); // Centraliza a janela

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(240, 160, 100, 30);
        add(btnVoltar);
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());
    }

    private void registrarDevolucao() {
        String usuario = txtUsuario.getText();
        String livro = txtLivro.getText();
        String dataDevolucao = txtDataDevolucao.getText();

        // Verificar se os campos estão preenchidos
        if (usuario.isEmpty() || livro.isEmpty() || dataDevolucao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
        } else {
            // Inserir no banco de dados
            String sql = "INSERT INTO devolucao (usuario_idUsuario, cadastrodelivros_idLivro, data_devolucao) VALUES (?, ?, ?)";
            try (Connection conn = new Conexao().getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Substituir os parâmetros com os dados fornecidos
                stmt.setInt(1, Integer.parseInt(usuario)); // ID do usuário
                stmt.setInt(2, Integer.parseInt(livro));   // ID do livro
                stmt.setString(3, dataDevolucao);          // Data de devolução

                int resultado = stmt.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao registrar devolução.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        }
    }

    private void voltarMenuPrincipal() {
        dispose();
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaDevolucoes().setVisible(true);
    }
}
