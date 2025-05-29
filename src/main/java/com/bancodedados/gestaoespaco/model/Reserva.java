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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status = StatusReserva.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espaco_fisico_id", nullable = false)
    private EspacoFisico espacoFisico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Reserva() {

    }

    // Construtor para facilitar a criação de novas reservas
    public Reserva(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   EspacoFisico espacoFisico, Usuario usuario) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.espacoFisico = espacoFisico;
        this.usuario = usuario;
        // O status padrão PENDENTE já é definido na declaração do campo, não precisamos setar aqui.
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

    public StatusReserva getStatus() { // Getter para o Enum StatusReserva
        return status;
    }

    public void setStatus(StatusReserva status) { // Setter para o Enum StatusReserva
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
                ", status=" + status + // Agora imprime o nome do Enum (ex: PENDENTE)
                ", espacoFisicoId=" + (espacoFisico != null ? espacoFisico.getId() : "null") +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        // Usa Objects.equals para lidar com IDs nulos e compara apenas o ID para igualdade
        return id != null && Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Gera o hashCode baseado no ID
    }
}