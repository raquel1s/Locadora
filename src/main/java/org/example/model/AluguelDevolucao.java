package org.example.model;

import java.time.LocalDate;

public class AluguelDevolucao extends Aluguel{

    private String tituloFilme;
    private String nomeCliente;

    public AluguelDevolucao(int id, LocalDate dataAluguel, String tituloFilme, String nomeCliente) {
        super(id, dataAluguel);
        this.tituloFilme = tituloFilme;
        this.nomeCliente = nomeCliente;
    }

    public AluguelDevolucao(int id, LocalDate dataAluguel, LocalDate dataDevolucao, String tituloFilme, String nomeCliente) {
        super(id, dataAluguel, dataDevolucao);
        this.tituloFilme = tituloFilme;
        this.nomeCliente = nomeCliente;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Título do Filme - " + tituloFilme +
                "\nNome do Cliente - " + nomeCliente;
    }
}
