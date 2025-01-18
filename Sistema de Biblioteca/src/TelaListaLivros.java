
import conexao.Conexao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaListaLivros extends JFrame {

    private JTable tableLivros; // Tabela para exibir os livros
    private JScrollPane scrollPane; // Para adicionar rolagem à tabela

    public TelaListaLivros() {
        setTitle("Lista de Livros");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criando a tabela
        tableLivros = new JTable();
        scrollPane = new JScrollPane(tableLivros);
        add(scrollPane, BorderLayout.CENTER);
        
        // Painel para o botão Voltar
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Centraliza o botão
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());
        panel.add(btnVoltar);
        add(panel, BorderLayout.SOUTH); // Adiciona o painel com o botão ao rodapé
        

        // Botão para carregar os livros
        JButton btnCarregarLivros = new JButton("Carregar Livros");
        btnCarregarLivros.addActionListener(e -> carregarLivros());
        add(btnCarregarLivros, BorderLayout.NORTH);

        setLocationRelativeTo(null); // Centraliza a janela
    }

    // Método para carregar livros do banco de dados
    private void carregarLivros() {
        String sql = "SELECT idLivro, titulo, autor, ISBN, categoria FROM livros";
        try (Connection conn = new Conexao().getConexao(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            // Colunas da tabela
            String[] colunas = {"ID", "Título", "Autor", "ISBN", "categoria"};

            // Criando um modelo para o JTable
            DefaultTableModel model = new DefaultTableModel(colunas, 0);

            // Preenchendo a tabela com os dados
            while (rs.next()) {
                String idLivro = rs.getString("idLivro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String ISBN = rs.getString("ISBN");
                String categoria = rs.getString("categoria");
                model.addRow(new Object[]{idLivro, titulo, autor, ISBN, categoria});
            }

            tableLivros.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os livros: " + e.getMessage());
        }
    }

    private void voltarMenuPrincipal() {
        dispose();
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaListaLivros().setVisible(true);
    }
}
