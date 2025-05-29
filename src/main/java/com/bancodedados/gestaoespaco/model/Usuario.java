package com.bancodedados.gestaoespaco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reservas"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"usuario"})
    private List<Reserva> reservas;

    public Usuario() {}

    public Usuario(String nome, String email, TipoUsuario tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public TipoUsuario getTipo() { return tipo; }

    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

    public List<Reserva> getReservas() { return reservas; }

    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipo=" + tipo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id != null && Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}