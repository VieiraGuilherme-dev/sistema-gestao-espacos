package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Reserva;
import com.bancodedados.gestaoespaco.model.StatusReserva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservaRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reserva> rowMapper = (rs, rowNum) -> {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("id"));
        reserva.setUsuarioId(rs.getLong("usuario_id")); // Adapte se estiver usando objeto Usuario
        reserva.setEspacoFisicoId(rs.getLong("espaco_fisico_id")); // Adapte se estiver usando objeto EspacoFisico
        reserva.setDataHoraInicio(rs.getTimestamp("data_hora_inicio").toLocalDateTime());
        reserva.setDataHoraFim(rs.getTimestamp("data_hora_fim").toLocalDateTime());
        reserva.setStatus(StatusReserva.valueOf(rs.getString("status")));
        return reserva;
    };

    public List<Reserva> findAll() {
        String sql = "SELECT * FROM reserva";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Reserva findById(Long id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Reserva reserva) {
        String sql = "INSERT INTO reserva (usuario_id, espaco_fisico_id, data_hora_inicio, data_hora_fim, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reserva.getUsuarioId(),
                reserva.getEspacoFisicoId(),
                Timestamp.valueOf(reserva.getDataHoraInicio()),
                Timestamp.valueOf(reserva.getDataHoraFim()),
                reserva.getStatus().name());
    }

    public void update(Reserva reserva) {
        String sql = "UPDATE reserva SET usuario_id = ?, espaco_fisico_id = ?, data_hora_inicio = ?, data_hora_fim = ?, status = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                reserva.getUsuarioId(),
                reserva.getEspacoFisicoId(),
                Timestamp.valueOf(reserva.getDataHoraInicio()),
                Timestamp.valueOf(reserva.getDataHoraFim()),
                reserva.getStatus().name(),
                reserva.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Reserva> findByStatus(StatusReserva status) {
        String sql = "SELECT * FROM reserva WHERE status = ?";
        return jdbcTemplate.query(sql, rowMapper, status.name());
    }

    public List<Reserva> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM reserva WHERE usuario_id = ?";
        return jdbcTemplate.query(sql, rowMapper, usuarioId);
    }

    public List<Reserva> findByEspacoFisicoId(Long espacoFisicoId) {
        String sql = "SELECT * FROM reserva WHERE espaco_fisico_id = ?";
        return jdbcTemplate.query(sql, rowMapper, espacoFisicoId);
    }

    public List<Reserva> verificarConflitoHorario(Long espacoId, LocalDateTime inicio, LocalDateTime fim) {
        String sql = "SELECT * FROM reserva WHERE espaco_fisico_id = ? AND " +
                "((? BETWEEN data_hora_inicio AND data_hora_fim) OR " +
                "(? BETWEEN data_hora_inicio AND data_hora_fim) OR " +
                "(data_hora_inicio BETWEEN ? AND ?))";

        return jdbcTemplate.query(sql, rowMapper,
                espacoId,
                Timestamp.valueOf(inicio),
                Timestamp.valueOf(fim),
                Timestamp.valueOf(inicio),
                Timestamp.valueOf(fim));
    }

    public List<Reserva> findAllByOrderByDataHoraInicioAsc() {
        String sql = "SELECT * FROM reserva ORDER BY data_hora_inicio ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Reserva> findAllByOrderByStatusAsc() {
        String sql = "SELECT * FROM reserva ORDER BY status ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
