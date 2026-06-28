package org.example;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();

    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Livro buscarLivro(int idLivro) {
        for (Livro livro : livros) {
            if(livro.getIdLivro() == idLivro) {
                return livro;
            }
        }
        return null;
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente cliente : clientes) {
            if(cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    public int gerarProximoIdLivro() {
        if (livros.isEmpty()) {
            return 1;
        }
        return livros.get(livros.size() - 1).getIdLivro() + 1;
    }

    public int gerarProximoIdCliente() {
        if (clientes.isEmpty()) {
            return 1;
        }
        return clientes.get(clientes.size() - 1).getId() + 1;
    }

    public void cadastrarCliente(String nome, String email, String senha) {
        Cliente cliente = new Cliente(gerarProximoIdCliente(), nome, email, senha);
        clientes.add(cliente);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public boolean removerLivro(Livro livro) {
        return livros.remove(livro);
    }
}
