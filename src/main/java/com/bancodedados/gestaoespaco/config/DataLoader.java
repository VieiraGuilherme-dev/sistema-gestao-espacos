package com.bancodedados.gestaoespaco.config;

import com.bancodedados.gestaoespaco.model.TipoEspaco;
import com.bancodedados.gestaoespaco.model.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Configuration
public class DataLoader {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {

                // Verifica se há usuários
                ResultSet rsUsuarios = conn.prepareStatement("SELECT COUNT(*) FROM usuarios").executeQuery();
                rsUsuarios.next();
                if (rsUsuarios.getInt(1) == 0) {

                    String sqlUsuario = "INSERT INTO usuarios (nome, email, tipo) VALUES (?, ?, ?)";
                    PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);

                    stmtUsuario.setString(1, "Admin do Sistema");
                    stmtUsuario.setString(2, "admin@gestao.com");
                    stmtUsuario.setString(3, TipoUsuario.ADMIN.name());
                    stmtUsuario.executeUpdate();

                    stmtUsuario.setString(1, "Aluno Exemplo");
                    stmtUsuario.setString(2, "aluno@email.com");
                    stmtUsuario.setString(3, TipoUsuario.ALUNO.name());
                    stmtUsuario.executeUpdate();

                    stmtUsuario.setString(1, "Prof. Ana Silva");
                    stmtUsuario.setString(2, "ana.silva@escola.com");
                    stmtUsuario.setString(3, TipoUsuario.PROFESSOR.name());
                    stmtUsuario.executeUpdate();

                    stmtUsuario.setString(1, "Func. João Mendes");
                    stmtUsuario.setString(2, "joao.mendes@escola.com");
                    stmtUsuario.setString(3, TipoUsuario.FUNCIONARIO.name());
                    stmtUsuario.executeUpdate();

                    System.out.println("Usuários de teste inseridos.");
                }

                // Verifica se há espaços físicos
                ResultSet rsEspacos = conn.prepareStatement("SELECT COUNT(*) FROM espacos").executeQuery();
                rsEspacos.next();
                if (rsEspacos.getInt(1) == 0) {

                    String sqlEspaco = "INSERT INTO espacos (nome, tipo, metragem) VALUES (?, ?, ?)";
                    PreparedStatement stmtEspaco = conn.prepareStatement(sqlEspaco);

                    stmtEspaco.setString(1, "Auditório Principal");
                    stmtEspaco.setString(2, TipoEspaco.AUDITORIO.name());
                    stmtEspaco.setDouble(3, 200.0);
                    stmtEspaco.executeUpdate();

                    stmtEspaco.setString(1, "Laboratório de Informática 1");
                    stmtEspaco.setString(2, TipoEspaco.LABORATORIO.name());
                    stmtEspaco.setDouble(3, 50.0);
                    stmtEspaco.executeUpdate();

                    stmtEspaco.setString(1, "Sala de Aula 101");
                    stmtEspaco.setString(2, TipoEspaco.SALA_DE_AULA.name());
                    stmtEspaco.setDouble(3, 40.0);
                    stmtEspaco.executeUpdate();

                    stmtEspaco.setString(1, "Sala de Reunião Bloco B");
                    stmtEspaco.setString(2, TipoEspaco.SALA_DE_REUNIAO.name());
                    stmtEspaco.setDouble(3, 25.0);
                    stmtEspaco.executeUpdate();

                    stmtEspaco.setString(1, "Quadra Poliesportiva");
                    stmtEspaco.setString(2, TipoEspaco.QUADRA.name());
                    stmtEspaco.setDouble(3, 600.0);
                    stmtEspaco.executeUpdate();

                    System.out.println("Espaços físicos de teste inseridos.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
