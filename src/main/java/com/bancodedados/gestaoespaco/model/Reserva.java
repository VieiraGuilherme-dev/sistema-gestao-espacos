package com.bancodedados.gestaoespaco.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva {

    private Long id;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusReserva status = StatusReserva.PENDENTE;
    private EspacoFisico espacoFisico;
    private Usuario usuario;

    public Reserva() {}

    public Reserva(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   EspacoFisico espacoFisico, Usuario usuario) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.espacoFisico = espacoFisico;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }

    public LocalDateTime getDataHoraFim() { return dataHoraFim; }
    public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }

    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }

    public EspacoFisico getEspacoFisico() { return espacoFisico; }
    public void setEspacoFisico(EspacoFisico espacoFisico) { this.espacoFisico = espacoFisico; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", status=" + status +
                ", espacoFisicoId=" + (espacoFisico != null ? espacoFisico.getId() : "null") +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return id != null && Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
