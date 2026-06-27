class Livro {

    private int idLivro;
    private String nome;
    private String autor;
    private int anoLancamento;
    private String genero;
    private boolean disponivel;
    private int idDono;

    Livro(String nome, String autor, int anoLancamento, String genero, boolean disponivel, int idDono) {

    setNome(nome);
    setAutor(autor);
    setAnoLancamento(anoLancamento);
    setGenero(genero);
    setDisponivel(disponivel);
    setIdDono(idDono);

    }



    private void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }
    public String getNome() {
        return nome;
    }

    private void setAutor(String autor) {
        if(autor != null && !nome.trim().isEmpty()) {
            this.autor = autor;
        }
    }

    public String getAutor() {
        return autor;
    }

    private void setAnoLancamento(int anoLancamento) {
       if(anoLancamento <= 2026) {
           this.anoLancamento = anoLancamento ;
       }
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    private void setGenero(String genero) {
        if(genero != null && !genero.trim().isEmpty()) {
            this.genero = genero;
        }
    }

    public String getGenero() {
        return genero;
    }

    private void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    private void setIdDono(int idDono) {
        this.idDono = idDono;

    }

    public int getIdDono() {
      return idDono;
    }

}