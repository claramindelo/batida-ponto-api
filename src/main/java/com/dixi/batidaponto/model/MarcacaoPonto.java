package com.dixi.batidaponto.model;

import java.time.LocalDateTime;

public class MarcacaoPonto {
    private String hora;
    private String foto; // Base64 da foto
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataHoraCompleta; // Para validações

    // Construtores
    public MarcacaoPonto() {
    }

    public MarcacaoPonto(String hora, String foto, Double latitude, Double longitude, LocalDateTime dataHoraCompleta) {
        this.hora = hora;
        this.foto = foto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHoraCompleta = dataHoraCompleta;
    }

    // Getters e Setters
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getDataHoraCompleta() {
        return dataHoraCompleta;
    }

    public void setDataHoraCompleta(LocalDateTime dataHoraCompleta) {
        this.dataHoraCompleta = dataHoraCompleta;
    }
}