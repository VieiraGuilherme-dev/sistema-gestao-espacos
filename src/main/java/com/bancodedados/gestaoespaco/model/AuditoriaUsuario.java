package com.bancodedados.gestaoespaco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_usuario")
public class AuditoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public AuditoriaUsuario() {
        this.dataHora = LocalDateTime.now();
    }

    public AuditoriaUsuario(Usuario usuario, String acao) {
        this.usuario = usuario;
        this.acao = acao;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "AuditoriaUsuario{" +
                "id=" + id +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                ", acao='" + acao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoriaUsuario that = (AuditoriaUsuario) o;
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}