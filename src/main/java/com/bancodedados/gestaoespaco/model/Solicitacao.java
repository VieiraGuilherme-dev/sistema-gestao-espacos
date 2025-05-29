package com.bancodedados.gestaoespaco.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espaco_id", nullable = false)
    private EspacoFisico espaco;

    @Column(nullable = false)
    private LocalDateTime dataHoraSolicitada; // data e hora desejadas para o uso do espaço

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;    // data em que foi feita a solicitação

    @Column(nullable = false)
    private String status; // PENDENTE, APROVADA, REJEITADA

    public Solicitacao() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "PENDENTE";
    }

    public Solicitacao(Usuario solicitante, EspacoFisico espaco, LocalDateTime dataHoraSolicitada) {
        this.solicitante = solicitante;
        this.espaco = espaco;
        this.dataHoraSolicitada = dataHoraSolicitada;
        // Automatically set dataSolicitacao upon creation
        this.dataSolicitacao = LocalDateTime.now();
        // Set initial status to PENDENTE
        this.status = "PENDENTE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public EspacoFisico getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoFisico espaco) {
        this.espaco = espaco;
    }

    public LocalDateTime getDataHoraSolicitada() {
        return dataHoraSolicitada;
    }

    public void setDataHoraSolicitada(LocalDateTime dataHoraSolicitada) {
        this.dataHoraSolicitada = dataHoraSolicitada;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "id=" + id +
                ", solicitante=" + (solicitante != null ? solicitante.getNome() : "null") +
                ", espaco=" + (espaco != null ? espaco.getNome() : "null") +
                ", dataHoraSolicitada=" + dataHoraSolicitada +
                ", dataSolicitacao=" + dataSolicitacao +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacao that = (Solicitacao) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}