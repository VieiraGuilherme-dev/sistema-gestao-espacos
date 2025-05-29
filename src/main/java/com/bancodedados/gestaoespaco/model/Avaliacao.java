package com.bancodedados.gestaoespaco.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id", nullable = false)
    private Solicitacao solicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gestor_id", nullable = false)
    private Usuario gestor;
    @Column(nullable = false)
    private String status; // APROVADA, REJEITADA

    @Column(columnDefinition = "TEXT")
    private String justificativa;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;

    public Avaliacao() {
        this.dataAvaliacao = LocalDateTime.now();
    }

    public Avaliacao(Solicitacao solicitacao, Usuario gestor, String status, String justificativa) {
        this.solicitacao = solicitacao;
        this.gestor = gestor;
        this.status = status;
        this.justificativa = justificativa;
        this.dataAvaliacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Usuario getGestor() {
        return gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", solicitacaoId=" + (solicitacao != null ? solicitacao.getId() : "null") +
                ", gestor=" + (gestor != null ? gestor.getNome() : "null") +
                ", status='" + status + '\'' +
                ", dataAvaliacao=" + dataAvaliacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return id != null && id.equals(avaliacao.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}