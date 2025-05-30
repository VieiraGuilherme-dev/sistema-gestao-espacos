package com.bancodedados.gestaoespaco.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reserva implements Serializable {
    private Long id;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private EspacoFisico espacoFisico;
    private Usuario usuario;
    private StatusReserva status;

    public Reserva() {}

    // Construtor com campos necessários para criação
    public Reserva(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, EspacoFisico espacoFisico, Usuario usuario) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.espacoFisico = espacoFisico;
        this.usuario = usuario;
    }

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public EspacoFisico getEspacoFisico() {
        return espacoFisico;
    }

    public void setEspacoFisico(EspacoFisico espacoFisico) {
        this.espacoFisico = espacoFisico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", espacoFisico=" + (espacoFisico != null ? espacoFisico.getId() : "null") +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", status=" + status +
                '}';
    }
}