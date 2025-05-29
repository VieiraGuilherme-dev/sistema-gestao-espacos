package com.bancodedados.gestaoespaco.controller;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas") 
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public Reserva buscarReserva(@PathVariable Long id) {
        // Como o serviço retorna Optional<Reserva>, usamos .orElse(null) para replicar o comportamento original
        return reservaService.buscarReserva(id).orElse(null);
    }

    @PostMapping
    public Reserva criarReserva(@RequestBody Reserva reserva) {
        return reservaService.salvarReserva(reserva);
    }

    @PutMapping("/{id}")
    public Reserva atualizarReserva(@PathVariable Long id, @RequestBody Reserva novaReserva) {

        Optional<Reserva> reservaOptional = reservaService.buscarReserva(id);

        if (reservaOptional.isPresent()) {
            Reserva reservaExistente = reservaOptional.get();

            reservaExistente.setDataHoraInicio(novaReserva.getDataHoraInicio());
            reservaExistente.setDataHoraFim(novaReserva.getDataHoraFim());
            reservaExistente.setStatus(novaReserva.getStatus());
            reservaExistente.setEspacoFisico(novaReserva.getEspacoFisico());
            reservaExistente.setUsuario(novaReserva.getUsuario());

            return reservaService.salvarReserva(reservaExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirReserva(@PathVariable Long id) {
        reservaService.excluirReserva(id);
    }
}