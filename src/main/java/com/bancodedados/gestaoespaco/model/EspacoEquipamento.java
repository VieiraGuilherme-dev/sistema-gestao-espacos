package com.bancodedados.gestaoespaco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "espaco_equipamento") // Explicitly naming the join table
public class EspacoEquipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Eagerly fetching is usually not recommended for ManyToOne
    @JoinColumn(name = "espaco_id", nullable = false) // Define the foreign key column and make it non-nullable
    private EspacoFisico espaco;

    @ManyToOne(fetch = FetchType.LAZY) // Eagerly fetching is usually not recommended for ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false) // Define the foreign key column and make it non-nullable
    private Equipamento equipamento;

    @Column(nullable = false) // 'quantidade' is likely a required field
    private int quantidade;

    // --- Constructors ---

    // Default constructor required by JPA
    public EspacoEquipamento() {
    }

    // Constructor with common fields for easier object creation
    public EspacoEquipamento(EspacoFisico espaco, Equipamento equipamento, int quantidade) {
        this.espaco = espaco;
        this.equipamento = equipamento;
        this.quantidade = quantidade;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EspacoFisico getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoFisico espaco) {
        this.espaco = espaco;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // --- Optional: toString(), equals(), and hashCode() for better object handling ---

    @Override
    public String toString() {
        return "EspacoEquipamento{" +
                "id=" + id +
                ", espaco=" + (espaco != null ? espaco.getNome() : "null") + // Avoid loading full object for toString
                ", equipamento=" + (equipamento != null ? equipamento.getNome() : "null") + // Avoid loading full object for toString
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspacoEquipamento that = (EspacoEquipamento) o;
        // For entities, equality is typically based on the ID if it's not null.
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // For entities, hashCode is typically based on the ID if it's not null.
        return id != null ? id.hashCode() : 0;
    }
}