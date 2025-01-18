import conexao.Conexao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaListaUsuarios extends JFrame {

    private JTable tableUsuarios;

    public TelaListaUsuarios() {
        setTitle("Lista de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabela para exibir os usuários
        tableUsuarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());
        add(btnVoltar, BorderLayout.SOUTH);

        // Carregar os usuários ao inicializar a tela
        carregarUsuarios();

        setLocationRelativeTo(null); // Centraliza a janela
    }

    private void carregarUsuarios() {
        String sql = "SELECT idUsuario, nome, email FROM usuario"; // Ajuste a consulta conforme a sua tabela
        try (Connection conn = new Conexao().getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Colunas da tabela
            String[] colunas = {"ID", "Nome", "Email"};
            
            // Criando um modelo para o JTable
            DefaultTableModel model = new DefaultTableModel(colunas, 0);

            // Preenchendo a tabela com os dados
            while (rs.next()) {
                String idUsuario = rs.getString("idUsuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                model.addRow(new Object[]{idUsuario, nome, email});
            }

            // Definindo o modelo para a tabela
            tableUsuarios.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os usuários: " + e.getMessage());
        }
    }

    private void voltarMenuPrincipal() {
        dispose();
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaListaUsuarios().setVisible(true);
    }
}
