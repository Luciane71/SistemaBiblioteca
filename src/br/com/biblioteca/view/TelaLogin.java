
import dao.UsuarioDAO;
import javax.swing.*;


public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
 

    public TelaLogin() {
        setTitle("Login - Sistema de Biblioteca");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        
        JLabel lblUsuario = new JLabel("Nome ou E-mail:");
        lblUsuario.setBounds(20, 30, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 30, 150, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 70, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 70, 150, 25);
        add(txtSenha);

        btnLogin = new JButton("Acessar");
        btnLogin.setBounds(150, 110, 100, 25);
        add(btnLogin);

        btnLogin.addActionListener(e -> autenticar());

        setLocationRelativeTo(null); // Centraliza a janela
    }

    private void autenticar() {
    String usuario = txtUsuario.getText();
    String senha = new String(txtSenha.getPassword());
    
    if (usuario.isEmpty() || senha.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha os campos de usuário e senha.");
        return;
    }

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    if (usuarioDAO.autenticar(usuario, senha)) {
        JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
        new TelaMenuPrincipal().setVisible(true); // Abre o menu principal
        dispose(); // Fecha a tela de login
    } else {
        JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!");
    }
}

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}

