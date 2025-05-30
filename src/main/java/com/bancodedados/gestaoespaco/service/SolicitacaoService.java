package com.bancodedados.gestaoespaco.service;

import com.bancodedados.gestaoespaco.model.Solicitacao;
import com.bancodedados.gestaoespaco.model.EspacoFisico;
import com.bancodedados.gestaoespaco.repository.EspacoFisicoRepository;
import com.bancodedados.gestaoespaco.repository.SolicitacaoRepository;
import com.bancodedados.gestaoespaco.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspacoFisicoRepository espacoRepository;

    /**
     * Creates a new Solicitacao (request) for a physical space.
     *
     * @param usuarioId The ID of the user making the request.
     * @param espacoId The ID of the physical space being requested.
     * @param dataReserva The desired date for the reservation.
     * @param horaReserva The desired time for the reservation.
     * @return The newly created and saved Solicitacao object.
     * @throws RuntimeException if the Usuario or EspacoFisico is not found.
     */
    public Solicitacao criarSolicitacao(Long usuarioId, Long espacoId,
                                        LocalDate dataReserva,
                                        LocalTime horaReserva) {
        // Find the requesting user
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));

        // Find the requested physical space
        EspacoFisico espaco = espacoRepository.findById(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaço físico não encontrado com ID: " + espacoId));

        // Combine date and time into a single LocalDateTime object
        LocalDateTime dataHoraSolicitada = LocalDateTime.of(dataReserva, horaReserva);

        // Create a new Solicitacao instance using the appropriate constructor
        Solicitacao solicitacao = new Solicitacao(solicitante, espaco, dataHoraSolicitada);

        return solicitacaoRepository.save(solicitacao);
    }

    /**
     * Retrieves a list of all existing Solicitacao objects.
     *
     * @return A list containing all Solicitacao entities.
     */
    public List<Solicitacao> listarTodas() {
        return solicitacaoRepository.findAll();
    }

    /**
     * Retrieves a list of Solicitacao objects filtered by their status.
     *
     * @param status The status to filter by (e.g., "PENDENTE", "APROVADA", "REJEITADA").
     * @return A list of Solicitacao entities matching the given status.
     */
    public List<Solicitacao> listarPorStatus(String status) {
        return solicitacaoRepository.findByStatus(status);
    }

    /**
     * Approves a Solicitacao by setting its status to "APROVADA".
     *
     * @param id The ID of the Solicitacao to approve.
     * @return The updated Solicitacao object.
     * @throws RuntimeException if the Solicitacao is not found.
     */
    public Solicitacao aprovarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com ID: " + id));
        solicitacao.setStatus("APROVADA");
        return solicitacaoRepository.save(solicitacao);
    }

    /**
     * Rejects a Solicitacao by setting its status to "REJEITADA".
     *
     * @param id The ID of the Solicitacao to reject.
     * @return The updated Solicitacao object.
     * @throws RuntimeException if the Solicitacao is not found.
     */
    public Solicitacao rejeitarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com ID: " + id));
        solicitacao.setStatus("REJEITADA");
        return solicitacaoRepository.save(solicitacao);
    }

    /**
     * Retrieves a single Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to retrieve.
     * @return An Optional containing the Solicitacao if found, or empty otherwise.
     */
    public Optional<Solicitacao> buscarPorId(Long id) {
        return solicitacaoRepository.findById(id);
    }

    /**
     * Deletes a Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to delete.
     * @throws RuntimeException if the Solicitacao is not found.
     */
    public void deletarSolicitacao(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new RuntimeException("Solicitação não encontrada com ID: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }
}