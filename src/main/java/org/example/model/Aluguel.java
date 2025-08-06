package org.example.model;

import java.util.Date;

public class Aluguel {

    private int id;
    private int clienteId;
    private int filmeId;
    private Date dataAluguel;
    private Date dataDevolucao;

    public Aluguel(int id, int clienteId, int filmeId, Date dataAluguel, Date dataDevolucao) {
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(int clienteId, int filmeId, Date dataAluguel, Date dataDevolucao) {
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
