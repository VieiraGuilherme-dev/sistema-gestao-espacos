package com.bancodedados.gestaoespaco.model;

public class EspacoEquipamento {

    private Long id;
    private EspacoFisico espaco;
    private Equipamento equipamento;
    private int quantidade;

    public EspacoEquipamento() {
    }

    public EspacoEquipamento(EspacoFisico espaco, Equipamento equipamento, int quantidade) {
        this.espaco = espaco;
        this.equipamento = equipamento;
        this.quantidade = quantidade;
    }

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

    @Override
    public String toString() {
        return "EspacoEquipamento{" +
                "id=" + id +
                ", espaco=" + (espaco != null ? espaco.getNome() : "null") +
                ", equipamento=" + (equipamento != null ? equipamento.getNome() : "null") +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EspacoEquipamento)) return false;
        EspacoEquipamento that = (EspacoEquipamento) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
