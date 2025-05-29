package com.bancodedados.gestaoespaco.controller; // Correct package

import com.bancodedados.gestaoespaco.model.Solicitacao; // Correct model package
import com.bancodedados.gestaoespaco.service.SolicitacaoService; // Import the service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // For more specific HTTP responses
import org.springframework.http.ResponseEntity; // For more specific HTTP responses
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException; // For handling not found cases cleanly

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes") // Base path for all requests to this controller
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService; // Inject the service, not the repository

    /**
     * Creates a new Solicitacao.
     * This endpoint now accepts individual fields to construct the Solicitacao via the service.
     *
     * @param usuarioId The ID of the user making the request.
     * @param espacoId The ID of the physical space being requested.
     * @param dataReserva The desired date for the reservation (e.g., "YYYY-MM-DD").
     * @param horaReserva The desired time for the reservation (e.g., "HH:MM").
     * @return The created Solicitacao object.
     */
    @PostMapping
    public ResponseEntity<Solicitacao> criarSolicitacao(
            @RequestParam Long usuarioId,
            @RequestParam Long espacoId,
            @RequestParam LocalDate dataReserva,
            @RequestParam LocalTime horaReserva) {
        try {
            Solicitacao novaSolicitacao = solicitacaoService.criarSolicitacao(usuarioId, espacoId, dataReserva, horaReserva);
            return new ResponseEntity<>(novaSolicitacao, HttpStatus.CREATED); // Return 201 Created
        } catch (RuntimeException e) {
            // Catches exceptions from service layer (e.g., user/space not found)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Retrieves all Solicitacao entities.
     *
     * @return A list of all Solicitacao objects.
     */
    @GetMapping
    public List<Solicitacao> listarTodas() {
        return solicitacaoService.listarTodas();
    }

    /**
     * Retrieves Solicitacao entities by a specific status.
     *
     * @param status The status to filter by (e.g., "PENDENTE", "APROVADA", "REJEITADA").
     * @return A list of Solicitacao objects matching the given status.
     */
    @GetMapping("/status/{status}") // Changed path to be more explicit about filtering
    public List<Solicitacao> listarPorStatus(@PathVariable String status) {
        return solicitacaoService.listarPorStatus(status);
    }

    /**
     * Approves a specific Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to approve.
     * @return The updated Solicitacao object with "APROVADA" status.
     */
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Solicitacao> aprovarSolicitacao(@PathVariable Long id) {
        try {
            Solicitacao solicitacaoAprovada = solicitacaoService.aprovarSolicitacao(id);
            return ResponseEntity.ok(solicitacaoAprovada); // Return 200 OK
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Rejects a specific Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to reject.
     * @param justificativa An optional justification for the rejection.
     * @return The updated Solicitacao object with "REJEITADA" status.
     */
    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<Solicitacao> rejeitarSolicitacao(@PathVariable Long id,
                                                           @RequestParam(required = false) String justificativa) {
        try {
            // Note: Our current SolicitacaoService.rejeitarSolicitacao doesn't take justification yet.
            // You'd extend the service method to handle this (e.g., saving to Avaliacao or HistoricoSolicitacao).
            Solicitacao solicitacaoRejeitada = solicitacaoService.rejeitarSolicitacao(id);
            // Example of how you might use the justification:
            // if (justificativa != null && !justificativa.isEmpty()) {
            //     // Logic to save justification, e.g., to Avaliacao entity
            // }
            return ResponseEntity.ok(solicitacaoRejeitada); // Return 200 OK
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Retrieves a single Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to retrieve.
     * @return The Solicitacao object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarSolicitacaoPorId(@PathVariable Long id) {
        return solicitacaoService.buscarPorId(id)
                .map(ResponseEntity::ok) // If found, return 200 OK with the solicitacao
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com ID: " + id)); // If not found, return 404
    }

    /**
     * Deletes a Solicitacao by its ID.
     *
     * @param id The ID of the Solicitacao to delete.
     * @return A no-content response if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSolicitacao(@PathVariable Long id) {
        try {
            solicitacaoService.deletarSolicitacao(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
