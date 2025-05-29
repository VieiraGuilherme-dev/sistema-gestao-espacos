package com.bancodedados.gestaoespaco.model;

import jakarta.persistence.*;
import java.util.ArrayList; // Import for initializing the list
import java.util.List;

@Entity
@Table(name = "equipamento") // It's good practice to explicitly name your tables
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 'nome' is likely a required field
    private String nome;

    @Column(columnDefinition = "TEXT") // Use TEXT for potentially longer descriptions
    private String descricao;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EspacoEquipamento> espacos = new ArrayList<>(); // Initialize the list to prevent NullPointerExceptions

    public Equipamento() {
    }

    public Equipamento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<EspacoEquipamento> getEspacos() {
        return espacos;
    }

    public void setEspacos(List<EspacoEquipamento> espacos) {
        this.espacos = espacos;
    }

    public void addEspacoEquipamento(EspacoEquipamento espacoEquipamento) {
        if (espacoEquipamento != null) {
            espacos.add(espacoEquipamento);
            espacoEquipamento.setEquipamento(this); // Set the back-reference
        }
    }

    public void removeEspacoEquipamento(EspacoEquipamento espacoEquipamento) {
        if (espacoEquipamento != null) {
            espacos.remove(espacoEquipamento);
            espacoEquipamento.setEquipamento(null); // Remove the back-reference
        }
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;
        // For entities, equality is typically based on the ID if it's not null.
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // For entities, hashCode is typically based on the ID if it's not null.
        return id != null ? id.hashCode() : 0;
    }
}