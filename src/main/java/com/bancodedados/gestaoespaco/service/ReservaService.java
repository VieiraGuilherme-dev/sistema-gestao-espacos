package com.bancodedados.gestaoespaco.service;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.model.Usuario;
import com.bancodedados.gestaoespaco.model.EspacoFisico;
import com.bancodedados.gestaoespaco.model.StatusReserva;
import com.bancodedados.gestaoespaco.repository.ReservaRepository;
import com.bancodedados.gestaoespaco.repository.UsuarioRepository;
import com.bancodedados.gestaoespaco.repository.EspacoFisicoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspacoFisicoRepository espacoFisicoRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          UsuarioRepository usuarioRepository,
                          EspacoFisicoRepository espacoFisicoRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.espacoFisicoRepository = espacoFisicoRepository;
    }

    @Transactional
    public Reserva criarReserva(Long usuarioId, Long espacoId,
                                LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {

        if (dataHoraInicio.isAfter(dataHoraFim) || dataHoraInicio.isEqual(dataHoraFim)) {
            throw new RuntimeException("A data/hora de início deve ser anterior à data/hora de fim.");
        }
        if (dataHoraInicio.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível fazer uma reserva para um horário que já passou.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));

        EspacoFisico espaco = espacoFisicoRepository.findById(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaço físico não encontrado com ID: " + espacoId));

        Reserva novaReserva = new Reserva(dataHoraInicio, dataHoraFim, espaco, usuario);
        novaReserva.setStatus(StatusReserva.PENDENTE); // Define o status inicial usando o Enum

        return salvarReserva(novaReserva); // Delega para o método de salvamento com validação de conflito
    }

    @Transactional
    public Reserva salvarReserva(Reserva reserva) {
        // Validação de conflito de horário
        List<Reserva> conflitos = reservaRepository.verificarConflitoHorario(
                reserva.getEspacoFisico().getId(),
                reserva.getDataHoraInicio(),
                reserva.getDataHoraFim()
        );

        if (!conflitos.isEmpty()) {
            if (reserva.getId() != null) {
                boolean isConflictWithSelf = conflitos.stream().anyMatch(r -> r.getId().equals(reserva.getId()));
                if (!isConflictWithSelf || conflitos.size() > 1) { // Se houver outro conflito ou o conflito não é com a própria reserva
                    throw new RuntimeException("Já existe uma reserva no mesmo horário para este espaço.");
                }
            } else { // Se não tem ID, é uma nova reserva, qualquer conflito é um problema
                throw new RuntimeException("Já existe uma reserva no mesmo horário para este espaço.");
            }
        }

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReserva(Long id) {
        return reservaRepository.findById(id);
    }

    @Transactional
    public void excluirReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada com ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    public List<Reserva> listarPorStatus(String status) {
        try {
            StatusReserva statusEnum = StatusReserva.valueOf(status.toUpperCase()); // Converte a string para Enum
            return reservaRepository.findByStatus(String.valueOf(statusEnum));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status de reserva inválido: " + status + ". Status permitidos: " + java.util.Arrays.toString(StatusReserva.values()));
        }
    }

    public List<Reserva> listarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> listarPorEspaco(Long espacoId) {
        return reservaRepository.findByEspacoFisicoId(espacoId);
    }

    @Transactional
    public Reserva aprovarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));
        reserva.setStatus(StatusReserva.APROVADA); // Usando o Enum
        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva recusarReserva(Long id) { // Renomeado para 'recusarReserva'
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));
        reserva.setStatus(StatusReserva.RECUSADA); // Usando o Enum (RECUSADA)
        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));
        reserva.setStatus(StatusReserva.CANCELADA); // Usando o Enum
        return reservaRepository.save(reserva);
    }
}