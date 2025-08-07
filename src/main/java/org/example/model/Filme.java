package org.example.model;

public class Filme {

    private int id;
    private String titulo;
    private String genero;
    private int anoLancamento;

    public Filme(int id, String titulo, String genero, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    public Filme(String titulo, String genero, int anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "\nId - " + id +
                "\nTítulo - " + titulo +
                "\nGênero - " + genero +
                "\nAno de Lançamento - " + anoLancamento +
                "\n";
    }
}
