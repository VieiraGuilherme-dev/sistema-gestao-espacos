package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.EspacoFisico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EspacoFisicoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EspacoFisicoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<EspacoFisico> rowMapper = new RowMapper<>() {
        @Override
        public EspacoFisico mapRow(ResultSet rs, int rowNum) throws SQLException {
            EspacoFisico espaco = new EspacoFisico();
            espaco.setId(rs.getLong("id"));
            espaco.setNome(rs.getString("nome"));
            espaco.setLocalizacao(rs.getString("localizacao"));
            espaco.setCapacidade(rs.getInt("capacidade"));
            return espaco;
        }
    };

    public List<EspacoFisico> findAll() {
        String sql = "SELECT * FROM espaco_fisico";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public EspacoFisico findById(Long id) {
        String sql = "SELECT * FROM espaco_fisico WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(EspacoFisico espaco) {
        String sql = "INSERT INTO espaco_fisico (nome, localizacao, capacidade) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, espaco.getNome(), espaco.getLocalizacao(), espaco.getCapacidade());
    }

    public void update(EspacoFisico espaco) {
        String sql = "UPDATE espaco_fisico SET nome = ?, localizacao = ?, capacidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, espaco.getNome(), espaco.getLocalizacao(), espaco.getCapacidade(), espaco.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM espaco_fisico WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
