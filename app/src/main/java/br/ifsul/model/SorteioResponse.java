package br.ifsul.model;

import java.util.Date;
import java.util.List;

public class SorteioResponse {
    private String tema;
    private int numero;
    private Date data;
    private List<Integer> numerosSorteados;

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Integer> getNumerosSorteados() {
        return numerosSorteados;
    }

    public void setNumerosSorteados(List<Integer> numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }
}
