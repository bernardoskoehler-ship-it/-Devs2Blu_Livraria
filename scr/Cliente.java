package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class Cliente extends Conta {
    Cliente(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ArrayList<String> historicoCliente = new ArrayList<>();

    private void adicionarHistorico(Livro livro, String acao) {
        String data = LocalDate.now().format(FORMATTER);

        historicoCliente.add(("Livro ID: ") + livro.getIdLivro() + " | " + livro.getNome() + " | " + acao + " | " + data);
    }

    public boolean alugarLivro(int idLivro, Biblioteca biblioteca) {
        Livro livro = biblioteca.buscarLivro(idLivro);
        if (livro == null) {
            return false;
        }
        if (!livro.isDisponivel()) {
            return false;
        }
        livro.alugar(this.getId());
        adicionarHistorico(livro, "alugado");
        return true;
    }

    public boolean devolverLivro(int idLivro, Biblioteca biblioteca) {
        Livro livro = biblioteca.buscarLivro(idLivro);
        if (livro == null) {
            return false;
        }
        if (livro.isDisponivel()) {
            return false;
        }
        if (livro.getIdDono() != this.getId()) {
            return false;
        }
        livro.devolver();
        adicionarHistorico(livro, "devolvido");
        return true;
    }

    public void mostrarHistorico() {
        for (String h : historicoCliente) {
            System.out.println(h);
        }
    }
}
