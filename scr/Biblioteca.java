package org.example;

import java.util.ArrayList;

public class Biblioteca {

    Conta administrador = new Administrador("ADM", "adm@gmail.com", "12345678");

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

    public boolean buscarADM(String email) {
        if(email.equals("adm@gmail.com")) return true;
        return false;
    }
    public Conta buscarContaPorEmail(String email) {
        if (administrador != null && administrador.getEmail().equals(email)) {
            return administrador;
        }
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
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

    public void mostrarLivros() {
        for(Livro livro : livros) {
            System.out.println("id: " + livro.getIdLivro() + " | nome: " + livro.getNome() + " | Autor: " + livro.getAutor() + " | Ano de Lançamento: " + livro.getAnoLancamentoFormatado() + " | Disponivel: " + livro.isDisponivel());
        }
    }
    public void mostrarLivrosAlugadosPorId(int id) {
        for(Livro livro : livros) {
            if (livro.getIdDono() == id) {
                System.out.println("id: " + livro.getIdLivro() + " | nome: " + livro.getNome() + " | Autor: " + livro.getAutor() + " | Ano de Lançamento: " + livro.getAnoLancamentoFormatado());
            }
        }
    }
    public void mostrarClientes() {
        for(Cliente cliente : clientes) {
            System.out.println("id: " + cliente.getId() + " | nome: " + cliente.getNome() + " | email: " + cliente.getEmail());
            System.out.println("livros alugados:");
            mostrarLivrosAlugadosPorId(cliente.getId());
            cliente.mostrarHistorico();
            System.out.println("------");
        }
    }
}
