package com.bancodedados.gestaoespaco.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Solicitacao {
    private Long id;
    private Usuario solicitante;
    private EspacoFisico espaco;
    private LocalDateTime dataHoraSolicitada;
    private LocalDateTime dataSolicitacao;
    private String status;

    public Solicitacao() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "PENDENTE";
    }

    public Solicitacao(Usuario solicitante, EspacoFisico espaco, LocalDateTime dataHoraSolicitada) {
        this.solicitante = solicitante;
        this.espaco = espaco;
        this.dataHoraSolicitada = dataHoraSolicitada;
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "PENDENTE";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getSolicitante() { return solicitante; }
    public void setSolicitante(Usuario solicitante) { this.solicitante = solicitante; }

    public EspacoFisico getEspaco() { return espaco; }
    public void setEspaco(EspacoFisico espaco) { this.espaco = espaco; }

    public LocalDateTime getDataHoraSolicitada() { return dataHoraSolicitada; }
    public void setDataHoraSolicitada(LocalDateTime dataHoraSolicitada) { this.dataHoraSolicitada = dataHoraSolicitada; }

    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

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
        if (!(o instanceof Solicitacao)) return false;
        Solicitacao that = (Solicitacao) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
