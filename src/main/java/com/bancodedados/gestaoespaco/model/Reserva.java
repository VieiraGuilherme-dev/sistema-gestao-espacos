package com.bancodedados.gestaoespaco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(nullable = false)
    private String status; // Ex: PENDENTE, APROVADA, RECUSADA, CANCELADA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espaco_fisico_id", nullable = false)
    private EspacoFisico espacoFisico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public Reserva() {
        this.status = "PENDENTE";
    }

    public Reserva(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   EspacoFisico espacoFisico, Usuario usuario) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.espacoFisico = espacoFisico;
        this.usuario = usuario;
        this.status = "PENDENTE"; // Status inicial ao criar
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
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

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", status='" + status + '\'' +
                ", espacoFisicoId=" + (espacoFisico != null ? espacoFisico.getId() : "null") +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        // Compara por ID para entidades persistentes, assumindo que ID é único
        return id != null && Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}