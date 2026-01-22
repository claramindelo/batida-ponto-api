package com.dixi.batidaponto.controller;

import com.dixi.batidaponto.model.RegistroDesconsiderado;
import com.dixi.batidaponto.model.RegistroPonto;
import com.dixi.batidaponto.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ponto")
@CrossOrigin(origins = "*")
public class PontoController {

    @Autowired
    private PontoService service;

    /**
     * POST /api/ponto/registrar
     * Registra um ponto usando a DATA/HORA DO SERVIDOR
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPonto(@RequestBody Map<String, Object> payload) {
        try {
            // Extrai apenas foto e localização do payload
            String foto = (String) payload.get("foto");
            Double latitude = payload.get("latitude") != null ? ((Number) payload.get("latitude")).doubleValue() : null;
            Double longitude = payload.get("longitude") != null ? ((Number) payload.get("longitude")).doubleValue() : null;

            // ⭐ USA A DATA/HORA DO SERVIDOR (impossível fraudar)
            LocalDateTime dataHoraServidor = LocalDateTime.now();

            // Registra o ponto
            RegistroPonto registro = service.registrarPonto(dataHoraServidor, foto, latitude, longitude);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Ponto registrado com sucesso!");
            response.put("registro", registro);
            response.put("dataHoraServidor", dataHoraServidor.toString()); // Retorna para o frontend

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro ao registrar ponto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * POST /api/ponto/desconsiderado
     * Registra uma marcação desconsiderada usando a DATA/HORA DO SERVIDOR
     */
    @PostMapping("/desconsiderado")
    public ResponseEntity<?> registrarDesconsiderado(@RequestBody Map<String, Object> payload) {
        try {
            String motivo = (String) payload.get("motivo");
            String foto = (String) payload.get("foto");
            Double latitude = payload.get("latitude") != null ? ((Number) payload.get("latitude")).doubleValue() : null;
            Double longitude = payload.get("longitude") != null ? ((Number) payload.get("longitude")).doubleValue() : null;

            // ⭐ USA A DATA/HORA DO SERVIDOR
            LocalDateTime dataHoraServidor = LocalDateTime.now();

            RegistroDesconsiderado desconsiderado = service.registrarDesconsiderado(dataHoraServidor, motivo, foto, latitude, longitude);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Marcação desconsiderada registrada");
            response.put("registro", desconsiderado);
            response.put("dataHoraServidor", dataHoraServidor.toString());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro ao registrar desconsiderado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * GET /api/ponto/registros
     * Lista todos os registros aprovados
     */
    @GetMapping("/registros")
    public ResponseEntity<List<RegistroPonto>> listarRegistros() {
        List<RegistroPonto> registros = service.listarRegistros();
        return ResponseEntity.ok(registros);
    }

    /**
     * GET /api/ponto/desconsiderados
     * Lista todos os registros desconsiderados
     */
    @GetMapping("/desconsiderados")
    public ResponseEntity<List<RegistroDesconsiderado>> listarDesconsiderados() {
        List<RegistroDesconsiderado> desconsiderados = service.listarDesconsiderados();
        return ResponseEntity.ok(desconsiderados);
    }

    /**
     * GET /api/ponto/filtrar?dataInicial=DD/MM/YYYY&dataFinal=DD/MM/YYYY
     * Filtra registros aprovados por período
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<RegistroPonto>> filtrarRegistros(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<RegistroPonto> registros = service.filtrarRegistros(dataInicial, dataFinal);
        return ResponseEntity.ok(registros);
    }

    /**
     * GET /api/ponto/filtrar-desconsiderados?dataInicial=DD/MM/YYYY&dataFinal=DD/MM/YYYY
     * Filtra registros desconsiderados por período
     */
    @GetMapping("/filtrar-desconsiderados")
    public ResponseEntity<List<RegistroDesconsiderado>> filtrarDesconsiderados(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<RegistroDesconsiderado> desconsiderados = service.filtrarDesconsiderados(dataInicial, dataFinal);
        return ResponseEntity.ok(desconsiderados);
    }
}