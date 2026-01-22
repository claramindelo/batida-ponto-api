package com.dixi.batidaponto.service;

import com.dixi.batidaponto.model.MarcacaoPonto;
import com.dixi.batidaponto.model.RegistroDesconsiderado;
import com.dixi.batidaponto.model.RegistroPonto;
import com.dixi.batidaponto.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PontoService {

    @Autowired
    private PontoRepository repository;

    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter HORA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter HORA_COMPLETA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Armazena a última marcação para validação de intervalo
    private LocalDateTime ultimaMarcacao = null;

    /**
     * Registra um ponto (marcação apropriada)
     */
    public RegistroPonto registrarPonto(LocalDateTime dataHora, String foto, Double latitude, Double longitude) {
        // Validação 1: Localização obrigatória
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Localização é obrigatória para registrar o ponto");
        }

        // Validação 2: Intervalo mínimo de 1 minuto
        if (ultimaMarcacao != null) {
            long segundosDecorridos = ChronoUnit.SECONDS.between(ultimaMarcacao, dataHora);
            if (segundosDecorridos < 60) {
                throw new IllegalArgumentException("Intervalo mínimo de 1 minuto entre marcações não respeitado");
            }
        }

        // Formata data e hora
        String dataFormatada = dataHora.format(DATA_FORMATTER);
        String horaFormatada = dataHora.format(HORA_FORMATTER);

        // Cria a marcação
        MarcacaoPonto marcacao = new MarcacaoPonto(
                horaFormatada,
                foto,
                latitude,
                longitude,
                dataHora
        );

        // Busca ou cria registro para essa data
        RegistroPonto registro = repository.findByData(dataFormatada);

        if (registro == null) {
            registro = new RegistroPonto(null, dataFormatada);
        }

        registro.adicionarMarcacao(marcacao);

        // Atualiza última marcação
        ultimaMarcacao = dataHora;

        return repository.save(registro);
    }

    /**
     * Registra uma marcação desconsiderada
     */
    public RegistroDesconsiderado registrarDesconsiderado(LocalDateTime dataHora, String motivo, String foto, Double latitude, Double longitude) {
        String dataFormatada = dataHora.format(DATA_FORMATTER);
        String horaFormatada = dataHora.format(HORA_COMPLETA_FORMATTER);

        RegistroDesconsiderado desconsiderado = new RegistroDesconsiderado(
                null,
                dataFormatada,
                horaFormatada,
                motivo,
                foto,
                latitude,
                longitude
        );

        return repository.saveDesconsiderado(desconsiderado);
    }

    /**
     * Lista todos os registros aprovados
     */
    public List<RegistroPonto> listarRegistros() {
        return repository.findAll();
    }

    /**
     * Lista todos os registros desconsiderados
     */
    public List<RegistroDesconsiderado> listarDesconsiderados() {
        return repository.findAllDesconsiderados();
    }

    /**
     * Filtra registros por período
     */
    public List<RegistroPonto> filtrarRegistros(String dataInicial, String dataFinal) {
        return repository.findByDataRange(dataInicial, dataFinal);
    }

    /**
     * Filtra desconsiderados por período
     */
    public List<RegistroDesconsiderado> filtrarDesconsiderados(String dataInicial, String dataFinal) {
        return repository.findDesconsideradosByDataRange(dataInicial, dataFinal);
    }
}