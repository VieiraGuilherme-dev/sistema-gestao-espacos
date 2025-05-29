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

                System.out.println("Espaços físicos de teste inseridos.");
            }
        };
    }
}