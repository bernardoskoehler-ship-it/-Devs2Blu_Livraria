package org.example;

public class Administrador extends Conta{
    Administrador(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    public boolean valorValido(int valor) {
        if(valor > 0) {
            return true;
        }
        return false;
    }
    public boolean nomeValido(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean adicionarLivro(Biblioteca biblioteca, String nome, String autor, int anoLancamento, String genero) {
        if (!nomeValido(nome) || !nomeValido(autor) || !valorValido(anoLancamento)) {
            return false;
        }
        int id = biblioteca.gerarProximoIdLivro();
        Livro livro = new Livro(id, nome, autor, anoLancamento, genero);
        biblioteca.adicionarLivro(livro);
        return true;
    }

    public boolean removerLivro(Biblioteca biblioteca, int idLivro) {
        Livro livro = biblioteca.buscarLivro(idLivro);
        if(livro == null) {
            return false;
        }
        if(!livro.isDisponivel()) {
            return false;
        }
        biblioteca.removerLivro(livro);
        return true;
    }
}
