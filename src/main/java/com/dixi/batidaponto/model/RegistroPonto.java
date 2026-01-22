package com.dixi.batidaponto.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroPonto {
    private Long id;
    private String data; // Formato: DD/MM/YYYY
    private List<MarcacaoPonto> horarios;

    // Construtores
    public RegistroPonto() {
        this.horarios = new ArrayList<>();
    }

    public RegistroPonto(Long id, String data) {
        this.id = id;
        this.data = data;
        this.horarios = new ArrayList<>();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<MarcacaoPonto> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<MarcacaoPonto> horarios) {
        this.horarios = horarios;
    }

    // Método auxiliar para adicionar uma marcação
    public void adicionarMarcacao(MarcacaoPonto marcacao) {
        this.horarios.add(marcacao);
    }
}