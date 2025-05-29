package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository // Esta anotação indica que este é um componente de repositório do Spring.
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByStatus(String status);

    List<Reserva> findByUsuarioId(Long usuarioId);

    List<Reserva> findByEspacoFisicoId(Long espacoFisicoId);

    @Query("SELECT r FROM Reserva r WHERE r.espacoFisico.id = :espacoId AND " +
            "((:inicio BETWEEN r.dataHoraInicio AND r.dataHoraFim) OR " +
            "(:fim BETWEEN r.dataHoraInicio AND r.dataHoraFim) OR " +
            "(r.dataHoraInicio BETWEEN :inicio AND :fim))")
    List<Reserva> verificarConflitoHorario(@org.springframework.data.repository.query.Param("espacoId") Long espacoId,
                                           @org.springframework.data.repository.query.Param("inicio") LocalDateTime inicio,
                                           @org.springframework.data.repository.query.Param("fim") LocalDateTime fim);
}