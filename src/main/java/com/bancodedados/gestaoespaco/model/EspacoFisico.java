package com.bancodedados.gestaoespaco.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "espaco_fisico")
public class EspacoFisico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private double metragem;

    @OneToMany(mappedBy = "espaco", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EspacoEquipamento> equipamentos = new ArrayList<>();


    public EspacoFisico() {
    }

    public EspacoFisico(String nome, String tipo, double metragem) {
        this.nome = nome;
        this.tipo = tipo;
        this.metragem = metragem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMetragem() {
        return metragem;
    }

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public List<EspacoEquipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EspacoEquipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    // --- Helper methods for managing the 'equipamentos' list ---
    // These methods help maintain both sides of the bidirectional relationship

    public void addEquipamento(EspacoEquipamento equipamento) {
        if (equipamento != null) {
            equipamentos.add(equipamento);
            equipamento.setEspaco(this);
        }
    }

    public void removeEquipamento(EspacoEquipamento equipamento) {
        if (equipamento != null) {
            equipamentos.remove(equipamento);
            equipamento.setEspaco(null);
        }
    }

    @Override
    public String toString() {
        return "EspacoFisico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", metragem=" + metragem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspacoFisico that = (EspacoFisico) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}