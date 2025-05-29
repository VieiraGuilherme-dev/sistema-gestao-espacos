package com.bancodedados.gestaoespaco.controller;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            Reserva novaReserva = reservaService.criarReserva(
                    reservaRequest.getUsuarioId(),
                    reservaRequest.getEspacoId(),
                    reservaRequest.getDataHoraInicio(),
                    reservaRequest.getDataHoraFim()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Tratar de forma mais robusta em um projeto real
        }
    }

    @GetMapping
    public List<Reserva> listarTodasReservas() {
        return reservaService.listarReservas();
    }

    // Endpoint para buscar uma reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.buscarReserva(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para aprovar uma reserva
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Reserva> aprovarReserva(@PathVariable Long id) {
        try {
            Reserva reservaAprovada = reservaService.aprovarReserva(id);
            return ResponseEntity.ok(reservaAprovada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para recusar uma reserva
    @PutMapping("/{id}/recusar")
    public ResponseEntity<Reserva> recusarReserva(@PathVariable Long id) {
        try {
            Reserva reservaRecusada = reservaService.recusarReserva(id);
            return ResponseEntity.ok(reservaRecusada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id) {
        try {
            Reserva reservaCancelada = reservaService.cancelarReserva(id);
            return ResponseEntity.ok(reservaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/historico") // Novo endpoint
    public List<Reserva> listarHistorico(@RequestParam(required = false, defaultValue = "dataHoraInicio") String ordenarPor) {
        if (ordenarPor.equalsIgnoreCase("status")) {
            return reservaService.listarReservasOrdenadasPorStatus();
        } else {

            return reservaService.listarReservasOrdenadasPorDataHoraInicio();
        }
    }

    static class ReservaRequest {
        private Long usuarioId;
        private Long espacoId;
        private LocalDateTime dataHoraInicio;
        private LocalDateTime dataHoraFim;

        public Long getUsuarioId() { return usuarioId; }

        public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

        public Long getEspacoId() { return espacoId; }

        public void setEspacoId(Long espacoId) { this.espacoId = espacoId; }

        public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }

        public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }

        public LocalDateTime getDataHoraFim() { return dataHoraFim; }

        public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }
    }
}