package com.bancodedados.gestaoespaco.model;

import java.util.ArrayList;
import java.util.List;

public class Equipamento {

    private Long id;
    private String nome;
    private String descricao;
    private List<EspacoEquipamento> espacos = new ArrayList<>();

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
            espacoEquipamento.setEquipamento(this);
        }
    }

    public void removeEspacoEquipamento(EspacoEquipamento espacoEquipamento) {
        if (espacoEquipamento != null) {
            espacos.remove(espacoEquipamento);
            espacoEquipamento.setEquipamento(null);
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
        if (!(o instanceof Equipamento)) return false;
        Equipamento that = (Equipamento) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
