package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByStatus(StatusReserva status);


    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findByEspacoFisicoId(Long espacoFisicoId);


    @Query("SELECT r FROM Reserva r WHERE r.espacoFisico.id = :espacoId AND " +
            "((:inicio BETWEEN r.dataHoraInicio AND r.dataHoraFim) OR " +
            "(:fim BETWEEN r.dataHoraInicio AND r.dataHoraFim) OR " +
            "(r.dataHoraInicio BETWEEN :inicio AND :fim))")
    List<Reserva> verificarConflitoHorario(@Param("espacoId") Long espacoId,
                                           @Param("inicio") LocalDateTime inicio,
                                           @Param("fim") LocalDateTime fim);

    // NOVOS MÉTODOS PARA ORDENAÇÃO:
    List<Reserva> findAllByOrderByDataHoraInicioAsc();
    List<Reserva> findAllByOrderByStatusAsc();
}