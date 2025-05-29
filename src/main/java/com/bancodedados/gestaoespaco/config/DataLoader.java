package com.bancodedados.gestaoespaco.config;

import com.bancodedados.gestaoespaco.model.Usuario;
import com.bancodedados.gestaoespaco.model.EspacoFisico;
import com.bancodedados.gestaoespaco.model.TipoUsuario;
import com.bancodedados.gestaoespaco.model.TipoEspaco;
import com.bancodedados.gestaoespaco.repository.UsuarioRepository;
import com.bancodedados.gestaoespaco.repository.EspacoFisicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UsuarioRepository usuarioRepository, EspacoFisicoRepository espacoFisicoRepository) {
        return args -> {

            if (usuarioRepository.count() == 0) {
                Usuario usuario1 = new Usuario();
                usuario1.setNome("Admin do Sistema");
                usuario1.setEmail("admin@gestao.com");
                usuario1.setTipo(TipoUsuario.ADMIN);
                usuarioRepository.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setNome("Aluno Exemplo");
                usuario2.setEmail("aluno@email.com");
                usuario2.setTipo(TipoUsuario.ALUNO);
                usuarioRepository.save(usuario2);

                // NOVO USUÁRIO: Professor
                Usuario usuario3 = new Usuario();
                usuario3.setNome("Prof. Ana Silva");
                usuario3.setEmail("ana.silva@escola.com");
                usuario3.setTipo(TipoUsuario.PROFESSOR); // Usando o Enum
                usuarioRepository.save(usuario3);

                // NOVO USUÁRIO: Funcionário
                Usuario usuario4 = new Usuario();
                usuario4.setNome("Func. João Mendes");
                usuario4.setEmail("joao.mendes@escola.com");
                usuario4.setTipo(TipoUsuario.FUNCIONARIO); // Usando o Enum
                usuarioRepository.save(usuario4);

                System.out.println("Usuários de teste inseridos.");
            }

            if (espacoFisicoRepository.count() == 0) {
                EspacoFisico espaco1 = new EspacoFisico();
                espaco1.setNome("Auditório Principal");
                espaco1.setTipo(TipoEspaco.AUDITORIO);
                espaco1.setMetragem(200.0);
                espacoFisicoRepository.save(espaco1);

                EspacoFisico espaco2 = new EspacoFisico();
                espaco2.setNome("Laboratório de Informática 1");
                espaco2.setTipo(TipoEspaco.LABORATORIO);
                espaco2.setMetragem(50.0);
                espacoFisicoRepository.save(espaco2);

                // NOVO ESPAÇO: Sala de Aula
                EspacoFisico espaco3 = new EspacoFisico();
                espaco3.setNome("Sala de Aula 101");
                espaco3.setTipo(TipoEspaco.SALA_DE_AULA);
                espaco3.setMetragem(40.0);
                espacoFisicoRepository.save(espaco3);

                // NOVO ESPAÇO: Sala de Reunião
                EspacoFisico espaco4 = new EspacoFisico();
                espaco4.setNome("Sala de Reunião Bloco B");
                espaco4.setTipo(TipoEspaco.SALA_DE_REUNIAO);
                espaco4.setMetragem(25.0);
                espacoFisicoRepository.save(espaco4);

                // NOVO ESPAÇO: Quadra Poliesportiva
                EspacoFisico espaco5 = new EspacoFisico();
                espaco5.setNome("Quadra Poliesportiva");
                espaco5.setTipo(TipoEspaco.QUADRA);
                espaco5.setMetragem(600.0);
                espacoFisicoRepository.save(espaco5);


                System.out.println("Espaços físicos de teste inseridos.");
            }
        };
    }
}