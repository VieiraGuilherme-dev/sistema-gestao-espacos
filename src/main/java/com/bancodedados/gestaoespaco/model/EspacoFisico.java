package com.bancodedados.gestaoespaco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "espaco_fisico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reservas"})
public class EspacoFisico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEspaco tipo;

    @Column(nullable = false)
    private double metragem;

    @OneToMany(mappedBy = "espacoFisico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"espacoFisico"}) // Ignora o 'espacoFisico' dentro da lista de reservas para evitar ciclo
    private List<Reserva> reservas;

    @ElementCollection
    @CollectionTable(name = "espaco_equipamento", joinColumns = @JoinColumn(name = "espaco_fisico_id"))
    @Column(name = "equipamento")
    private List<String> equipamentos;

    public EspacoFisico() {}

    public EspacoFisico(String nome, TipoEspaco tipo, double metragem, List<String> equipamentos) {
        this.nome = nome;
        this.tipo = tipo;
        this.metragem = metragem;
        this.equipamentos = equipamentos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoEspaco getTipo() { return tipo; }
    public void setTipo(TipoEspaco tipo) { this.tipo = tipo; }
    public double getMetragem() { return metragem; }
    public void setMetragem(double metragem) { this.metragem = metragem; }
    public List<String> getEquipamentos() { return equipamentos; }
    public void setEquipamentos(List<String> equipamentos) { this.equipamentos = equipamentos; }
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }


    @Override
    public String toString() {
        return "EspacoFisico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", metragem=" + metragem +
                ", equipamentos=" + equipamentos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspacoFisico that = (EspacoFisico) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}