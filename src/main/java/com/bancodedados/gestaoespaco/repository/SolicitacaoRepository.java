package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    /**
     * Finds all Solicitacao entities with a specific status.
     * Spring Data JPA automatically generates the query for this method.
     *
     * @param status The status to search for (e.g., "PENDENTE", "APROVADA", "REJEITADA").
     * @return A list of Solicitacao entities matching the provided status.
     */
    List<Solicitacao> findByStatus(String status);
}