package com.dixi.batidaponto.model;

public class RegistroDesconsiderado {
    private Long id;
    private String data; // Formato: DD/MM/YYYY
    private String hora; // Formato: HH:MM:SS
    private String motivo;
    private String foto; // Base64 da foto
    private Double latitude;
    private Double longitude;

    // Construtores
    public RegistroDesconsiderado() {
    }

    public RegistroDesconsiderado(Long id, String data, String hora, String motivo, String foto, Double latitude, Double longitude) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
        this.foto = foto;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
}