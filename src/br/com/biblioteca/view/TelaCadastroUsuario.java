
import javax.swing.*;
import beans.Usuario;
import dao.UsuarioDAO;
import javax.swing.JOptionPane;

public class TelaCadastroUsuario extends JFrame {

    private JTextField txtNome, txtCpf, txtEmail;
    private JComboBox<String> comboTipoUsuario;
    private JComboBox<String> comboPapelFuncionario;
    private JPasswordField txtSenha;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(400, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 30, 100, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 30, 200, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 70, 100, 25);
        add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(120, 70, 200, 25);
        add(txtCpf);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(20, 110, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 110, 200, 25);
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 150, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();  // Usando JPasswordField
        txtSenha.setBounds(120, 150, 200, 25);
        add(txtSenha);

        JLabel lblTipoUsuario = new JLabel("Tipo de Usuário:");
        lblTipoUsuario.setBounds(20, 190, 100, 25);
        add(lblTipoUsuario);

        comboTipoUsuario = new JComboBox<>(new String[]{"Funcionário", "Usuário da Biblioteca"});
        comboTipoUsuario.setBounds(120, 190, 200, 25);
        add(comboTipoUsuario);

        JLabel lblPapelFuncionario = new JLabel("Papel:");
        lblPapelFuncionario.setBounds(20, 230, 100, 25);
        add(lblPapelFuncionario);

        comboPapelFuncionario = new JComboBox<>(new String[]{"Gerente", "Operador"});
        comboPapelFuncionario.setBounds(120, 230, 200, 25);
        add(comboPapelFuncionario);

        // Oculta o comboPapelFuncionario inicialmente (aparece apenas para Funcionário)
        lblPapelFuncionario.setVisible(false);
        comboPapelFuncionario.setVisible(false);

        comboTipoUsuario.addActionListener(e -> {
            boolean isFuncionario = comboTipoUsuario.getSelectedItem().equals("Funcionário");
            lblPapelFuncionario.setVisible(isFuncionario);
            comboPapelFuncionario.setVisible(isFuncionario);
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 270, 100, 30);
        add(btnSalvar);
        btnSalvar.addActionListener(e -> salvarUsuario());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(240, 270, 100, 30);
        add(btnVoltar);
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        setLocationRelativeTo(null); // Centraliza a janela
    }

    private void salvarUsuario() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword()); // Captura a senha
        String tipoUsuario = (String) comboTipoUsuario.getSelectedItem();
        String papelFuncionario = tipoUsuario.equals("Funcionário") ? (String) comboPapelFuncionario.getSelectedItem() : "N/A";

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
            return;
        }

        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCPF(cpf);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipodeusuario(tipoUsuario);
            usuario.setPapelfuncionario (papelFuncionario);
            

            UsuarioDAO dao = new UsuarioDAO();
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            // Limpar os campos
            txtNome.setText("");
            txtCpf.setText("");
            txtEmail.setText("");
            txtSenha.setText("");
            comboTipoUsuario.setSelectedIndex(0); 
            comboPapelFuncionario.setSelectedIndex(0); 
            comboPapelFuncionario.setVisible(false);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuário: " + ex.getMessage());
        }
    }

    private void voltarMenuPrincipal() {
        // Fechar a janela atual
        dispose();

        // Abrir o menu principal (substitua por sua classe de menu principal)
        new TelaMenuPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaCadastroUsuario().setVisible(true);
    }
}
