package com.bancodedados.gestaoespaco.controller;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(
            @RequestParam Long usuarioId,
            @RequestParam Long espacoId,
            @RequestParam LocalDateTime dataHoraInicio,
            @RequestParam LocalDateTime dataHoraFim) {
        try {
            Reserva novaReserva = reservaService.criarReserva(usuarioId, espacoId, dataHoraInicio, dataHoraFim);
            return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodasReservas() {
        List<Reserva> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id) {
        return reservaService.buscarReserva(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada com ID: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        try {
            Reserva reservaExistente = reservaService.buscarReserva(id)
                    .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));

            reservaExistente.setDataHoraInicio(reservaAtualizada.getDataHoraInicio());
            reservaExistente.setDataHoraFim(reservaAtualizada.getDataHoraFim());
            reservaExistente.setStatus(reservaAtualizada.getStatus()); // O Jackson (Spring) deve mapear a string para o Enum

            if (reservaAtualizada.getUsuario() != null && reservaAtualizada.getUsuario().getId() != null) {
                reservaExistente.setUsuario(reservaAtualizada.getUsuario());
            }
            if (reservaAtualizada.getEspacoFisico() != null && reservaAtualizada.getEspacoFisico().getId() != null) {
                reservaExistente.setEspacoFisico(reservaAtualizada.getEspacoFisico());
            }

            return ResponseEntity.ok(reservaService.salvarReserva(reservaExistente));

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReserva(@PathVariable Long id) {
        try {
            reservaService.excluirReserva(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> listarPorStatus(@PathVariable String status) {
        List<Reserva> reservas = reservaService.listarPorStatus(status);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/espaco/{espacoId}")
    public ResponseEntity<List<Reserva>> listarPorEspaco(@PathVariable Long espacoId) {
        List<Reserva> reservas = reservaService.listarPorEspaco(espacoId);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Reserva> aprovar(@PathVariable Long id) {
        try {
            Reserva aprovada = reservaService.aprovarReserva(id);
            return ResponseEntity.ok(aprovada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}/recusar") // Mantido 'recusar' para consistência com o Enum RECUSADA
    public ResponseEntity<Reserva> recusar(@PathVariable Long id) {
        try {
            Reserva recusada = reservaService.recusarReserva(id); // Chamando o método 'recusarReserva'
            return ResponseEntity.ok(recusada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        try {
            Reserva cancelada = reservaService.cancelarReserva(id);
            return ResponseEntity.ok(cancelada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}