package com.bancodedados.gestaoespaco.controller; // Pacote corrigido

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
        return ResponseEntity.ok(reservas); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id) {
        return reservaService.buscarReserva(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada com ID: " + id)); // 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        try {
            // Primeiro, busca a reserva existente para garantir que ela exista
            Reserva reservaExistente = reservaService.buscarReserva(id)
                    .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));

            // Atualiza os campos da reserva existente com os dados recebidos
            reservaExistente.setDataHoraInicio(reservaAtualizada.getDataHoraInicio());
            reservaExistente.setDataHoraFim(reservaAtualizada.getDataHoraFim());
            reservaExistente.setStatus(reservaAtualizada.getStatus());

            if (reservaAtualizada.getUsuario() != null) {
                // Se o usuário no payload atualizado tem um ID, você precisaria buscar e setar o usuário real
                // Ou o método salvarReserva do serviço precisaria lidar com essa lógica
                reservaExistente.setUsuario(reservaAtualizada.getUsuario());
            }
            if (reservaAtualizada.getEspacoFisico() != null) {
                // Mesma lógica para o espaço físico
                reservaExistente.setEspacoFisico(reservaAtualizada.getEspacoFisico());
            }

            // Chama o serviço para salvar a reserva atualizada (com a validação de conflito)
            return ResponseEntity.ok(reservaService.salvarReserva(reservaExistente));

        } catch (RuntimeException e) {
            // Erros como "Reserva não encontrada" ou "Conflito de horário"
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReserva(@PathVariable Long id) {
        try {
            reservaService.excluirReserva(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); // 404 Not Found
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> listarPorStatus(@PathVariable String status) {
        // Assume que o status pode ser passado em maiúsculas, ajuste se necessário
        List<Reserva> reservas = reservaService.listarPorStatus(status.toUpperCase());
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
    public ResponseEntity<Reserva> aprovarReserva(@PathVariable Long id) {
        try {
            Reserva reservaAprovada = reservaService.aprovarReserva(id);
            return ResponseEntity.ok(reservaAprovada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<Reserva> recusarReserva(@PathVariable Long id) {
        try {
            Reserva reservaRecusada = reservaService.recusarReserva(id);
            return ResponseEntity.ok(reservaRecusada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id) {
        try {
            Reserva reservaCancelada = reservaService.cancelarReserva(id);
            return ResponseEntity.ok(reservaCancelada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}