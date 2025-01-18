
package beans;

public class Usuario {
    private int id;
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private String tipodeusuario;
    private String papelfuncionario;

    public String getPapelfuncionario() {
        return papelfuncionario;
    }

    public void setPapelfuncionario(String papelfuncionario) {
        this.papelfuncionario = papelfuncionario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipodeusuario() {
        return tipodeusuario;
    }

    public void setTipodeusuario(String tipodeusuario) {
        this.tipodeusuario = tipodeusuario;
    }
    
    
    
}
