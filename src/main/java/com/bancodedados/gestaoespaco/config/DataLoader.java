package com.bancodedados.gestaoespaco.config;

import com.bancodedados.gestaoespaco.model.Usuario;
import com.bancodedados.gestaoespaco.model.EspacoFisico;
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
            // Verifica se já existem usuários para evitar duplicação em reinícios
            if (usuarioRepository.count() == 0) {
                Usuario usuario1 = new Usuario();
                usuario1.setNome("Admin do Sistema");
                usuario1.setEmail("admin@gestao.com");
                usuario1.setPapel("GESTOR"); // Supondo que você tenha um campo 'papel'
                usuarioRepository.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setNome("Aluno Exemplo");
                usuario2.setEmail("aluno@email.com");
                usuario2.setPapel("ALUNO");
                usuarioRepository.save(usuario2);

                System.out.println("Usuários de teste inseridos.");
            }

            // Verifica se já existem espaços físicos
            if (espacoFisicoRepository.count() == 0) {
                EspacoFisico espaco1 = new EspacoFisico();
                espaco1.setNome("Auditório Principal");
                espaco1.setTipo("AUDITORIO"); // Exemplo de tipo
                espaco1.setCapacidade(200);
                espaco1.setLocalizacao("Bloco C, Térreo");
                espacoFisicoRepository.save(espaco1);

                EspacoFisico espaco2 = new EspacoFisico();
                espaco2.setNome("Laboratório de Informática 1");
                espaco2.setTipo("LABORATORIO");
                espaco2.setCapacidade(30);
                espaco2.setLocalizacao("Bloco A, 2º Andar");
                espacoFisicoRepository.save(espaco2);

                System.out.println("Espaços físicos de teste inseridos.");
            }
        };
    }
}