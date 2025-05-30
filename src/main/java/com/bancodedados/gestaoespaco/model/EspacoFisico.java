package com.bancodedados.gestaoespaco.model;

import java.util.List;
import java.util.Objects;

public class EspacoFisico {

    private Long id;
    private String nome;
    private TipoEspaco tipo;
    private double metragem;
    private List<String> equipamentos;
    private List<Reserva> reservas;

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
        if (!(o instanceof EspacoFisico)) return false;
        EspacoFisico that = (EspacoFisico) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setLocalizacao(String localizacao) {
    }

    public void setCapacidade(int capacidade) {
    }
}
