package com.dixi.batidaponto.repository;

import com.dixi.batidaponto.model.RegistroDesconsiderado;
import com.dixi.batidaponto.model.RegistroPonto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PontoRepository {

    // Armazenamento em memória
    private final List<RegistroPonto> registros = new ArrayList<>();
    private final List<RegistroDesconsiderado> registrosDesconsiderados = new ArrayList<>();

    // Contador para IDs únicos
    private Long nextId = 1L;
    private Long nextIdDesconsiderado = 1L;

    // ========== MÉTODOS PARA REGISTROS APROVADOS ==========

    public List<RegistroPonto> findAll() {
        return new ArrayList<>(registros);
    }

    public RegistroPonto findByData(String data) {
        return registros.stream()
                .filter(r -> r.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    public RegistroPonto save(RegistroPonto registro) {
        if (registro.getId() == null) {
            registro.setId(nextId++);
            registros.add(registro);
        } else {
            // Atualiza registro existente
            for (int i = 0; i < registros.size(); i++) {
                if (registros.get(i).getId().equals(registro.getId())) {
                    registros.set(i, registro);
                    break;
                }
            }
        }
        return registro;
    }

    public List<RegistroPonto> findByDataRange(String dataInicial, String dataFinal) {
        return registros.stream()
                .filter(r -> {
                    String data = r.getData();
                    return (dataInicial == null || data.compareTo(dataInicial) >= 0) &&
                            (dataFinal == null || data.compareTo(dataFinal) <= 0);
                })
                .collect(Collectors.toList());
    }

    // ========== MÉTODOS PARA REGISTROS DESCONSIDERADOS ==========

    public List<RegistroDesconsiderado> findAllDesconsiderados() {
        return new ArrayList<>(registrosDesconsiderados);
    }

    public RegistroDesconsiderado saveDesconsiderado(RegistroDesconsiderado registro) {
        if (registro.getId() == null) {
            registro.setId(nextIdDesconsiderado++);
        }
        registrosDesconsiderados.add(registro);
        return registro;
    }

    public List<RegistroDesconsiderado> findDesconsideradosByDataRange(String dataInicial, String dataFinal) {
        return registrosDesconsiderados.stream()
                .filter(r -> {
                    String data = r.getData();
                    return (dataInicial == null || data.compareTo(dataInicial) >= 0) &&
                            (dataFinal == null || data.compareTo(dataFinal) <= 0);
                })
                .collect(Collectors.toList());
    }
}