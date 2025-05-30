package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class UsuarioRepository {

    private final DataSource dataSource;

    public UsuarioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Usuario save(Usuario usuario) {
        String sql;
        if (usuario.getId() == null) {
            // INSERT
            sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getSenha()); // Make sure to hash passwords in a real app!

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error saving new user: " + e.getMessage());
                throw new RuntimeException("Database error saving user", e);
            }
        } else {
            // UPDATE
            sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getSenha()); // Again, hash passwords
                pstmt.setLong(4, usuario.getId());

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    // This might happen if the user with the given ID doesn't exist
                    System.err.println("No user found with ID: " + usuario.getId() + " for update.");
                }
            } catch (SQLException e) {
                System.err.println("Error updating user with ID " + usuario.getId() + ": " + e.getMessage());
                throw new RuntimeException("Database error updating user", e);
            }
        }
        return usuario;
    }


    public Optional<Usuario> findById(Long id) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding user by ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Database error retrieving user", e);
        }
        return Optional.empty();
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, senha FROM usuario";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error finding all users: " + e.getMessage());
            throw new RuntimeException("Database error retrieving all users", e);
        }
        return usuarios;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting user by ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Database error deleting user", e);
        }
    }
}