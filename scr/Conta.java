abstract class Conta {
    int id;
    private String nome;
    private String email;
    private String senha;
    private boolean logado = false;

    Conta(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    // GET/SET //
    private void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }
    public String getNome() {
        return nome;
    }

    private void setEmail(String email) {
        if(email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
    }
    public String getEmail() {
        return email;
    }

    private void setSenha(String senha) {
        if( senha != null && !senha.trim().isEmpty() && senha.length() > 7) {
            this.senha = senha;
        }
    }
    public String getSenha() {
        return senha;
    }

    public boolean isLogado() {
        return logado;
    }




}
