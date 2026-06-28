package org.example;

abstract class Conta {
    private int id;
    private String nome;
    private String email;
    private String senha;

    Conta(int id, String nome, String email, String senha) {
        this.id = id;
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    private boolean setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
            return true;
        }
        return false;
    }
    public String getNome() {
        return nome;
    }

    private boolean setEmail(String email) {
        if(email != null && !email.trim().isEmpty()) {
            this.email = email;
            return true;
        }
        return false;
    }
    public String getEmail() {
        return email;
    }

    private boolean setSenha(String senha) {
        if( senha != null && !senha.trim().isEmpty() && senha.length() > 7) {
            this.senha = senha;
            return true;
        }
        return false;
    }
    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }
}
