package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bancodedados.gestaoespaco.model.EspacoFisico;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface de repositório para a entidade Reserva.
 * Estende JpaRepository para fornecer operações CRUD padrão
 * (Criar, Ler, Atualizar, Excluir) para entidades de Reserva.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Método para encontrar reservas que se sobrepõem a um dado período para um espaço específico.

    List<Reserva> findByEspacoFisicoAndDataHoraInicioBeforeAndDataHoraFimAfter(
            EspacoFisico espacoFisico, LocalDateTime dataHoraFimDaNovaReserva, LocalDateTime dataHoraInicioDaNovaReserva);

    List<Reserva> findByUsuario(Usuario usuario);
    List<Reserva> findByStatus(String status);
}