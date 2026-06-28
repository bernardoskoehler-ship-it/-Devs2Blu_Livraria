package org.example;

import java.time.Year;

class Livro {

    private int idLivro;
    private String nome;
    private String autor;
    private int anoLancamento;
    private String genero;
    private boolean disponivel = true;
    private int idDono = 0;

    Livro(int id,String nome, String autor, int anoLancamento, String genero) {
        this.idLivro = id;
        setNome(nome);
        setAutor(autor);
        setAnoLancamento(anoLancamento);
        setGenero(genero);
    }

    public int getIdLivro() {
        return idLivro;
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

    private boolean setAutor(String autor) {
        if(autor != null && !autor.trim().isEmpty()) {
            this.autor = autor;
            return true;
        }
        return false;
    }

    public String getAutor() {
        return autor;
    }

    private boolean setAnoLancamento(int anoLancamento) {
        int anoAtual = Year.now().getValue();

        if (anoLancamento <= anoAtual) {
            this.anoLancamento = anoLancamento;
            return true;
        }
        return false;
    }

    public String getAnoLancamentoFormatado() {
        if (this.anoLancamento < 0) {
            return Math.abs(this.anoLancamento) + " A.C.";
        }
        return this.anoLancamento + " D.C.";
    }

    private boolean setGenero(String genero) {
        if(genero != null && !genero.trim().isEmpty()) {
            this.genero = genero;
            return true;
        }
        return false;
    }

    public String getGenero() {
        return genero;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    public int getIdDono() {
        return idDono;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }

    public void alugar(int idUsuario) {
        this.idDono = idUsuario;
        this.disponivel = false;
    }

    public void devolver() {
        this.idDono = 0;
        this.disponivel = true;
    }

}
