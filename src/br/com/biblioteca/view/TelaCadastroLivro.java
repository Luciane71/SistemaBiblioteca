import javax.swing.*;
import beans.Livros;
import dao.LivrosDAO;
import javax.swing.JOptionPane;

public class TelaCadastroLivros extends JFrame {

    private JTextField txtTitulo, txtAutor, txtISBN, txtCategoria;

    public TelaCadastroLivros() {
        setTitle("Cadastro de Livros");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 30, 100, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 30, 200, 25);
        add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 70, 100, 25);
        add(lblAutor);

        txtAutor = new JTextField();
        txtAutor.setBounds(120, 70, 200, 25);
        add(txtAutor);

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(20, 110, 100, 25);
        add(lblISBN);

        txtISBN = new JTextField();
        txtISBN.setBounds(120, 110, 200, 25);
        add(txtISBN);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 150, 100, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(120, 150, 200, 25);
        add(txtCategoria);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 200, 100, 30);
        add(btnSalvar);
        btnSalvar.addActionListener(e -> salvarLivro());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(240, 200, 100, 30);
        add(btnVoltar);
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        setLocationRelativeTo(null); // Centraliza a janela
    }

    private void salvarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String isbn = txtISBN.getText();
        String categoria = txtCategoria.getText();

        // Validação de campos
        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{

        try {
            // Criar um objeto Livro
            Livros livros = new Livros();
            livros.setTitulo(titulo);
            livros.setAutor(autor);
            livros.setISBN(isbn);
            livros.setCategoria(categoria);

            // Chamar a DAO para salvar
            LivrosDAO dao = new LivrosDAO();
            dao.inserir(livros);

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");

            // Limpar os campos
            txtTitulo.setText("");
            txtAutor.setText("");
            txtISBN.setText("");
            txtCategoria.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private void voltarMenuPrincipal() {
        // Fechar a janela atual
        dispose();

        // Abrir o menu principal (substitua por sua classe de menu principal)
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaCadastroLivros().setVisible(true);
    }
}
