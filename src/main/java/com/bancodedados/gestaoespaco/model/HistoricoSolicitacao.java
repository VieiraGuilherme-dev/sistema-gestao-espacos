package com.bancodedados.gestaoespaco.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class HistoricoSolicitacao {

    private Long id;
    private Solicitacao solicitacao;
    private String statusAnterior;
    private String statusNovo;
    private LocalDateTime dataHoraAlteracao;

    public HistoricoSolicitacao() {
        this.dataHoraAlteracao = LocalDateTime.now();
    }

    public HistoricoSolicitacao(Solicitacao solicitacao, String statusAnterior, String statusNovo) {
        this.solicitacao = solicitacao;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataHoraAlteracao = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Solicitacao getSolicitacao() { return solicitacao; }
    public void setSolicitacao(Solicitacao solicitacao) { this.solicitacao = solicitacao; }

    public String getStatusAnterior() { return statusAnterior; }
    public void setStatusAnterior(String statusAnterior) { this.statusAnterior = statusAnterior; }

    public String getStatusNovo() { return statusNovo; }
    public void setStatusNovo(String statusNovo) { this.statusNovo = statusNovo; }

    public LocalDateTime getDataHoraAlteracao() { return dataHoraAlteracao; }
    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) { this.dataHoraAlteracao = dataHoraAlteracao; }

    @Override
    public String toString() {
        return "HistoricoSolicitacao{" +
                "id=" + id +
                ", solicitacaoId=" + (solicitacao != null ? solicitacao.getId() : "null") +
                ", statusAnterior='" + statusAnterior + '\'' +
                ", statusNovo='" + statusNovo + '\'' +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoricoSolicitacao)) return false;
        HistoricoSolicitacao that = (HistoricoSolicitacao) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
