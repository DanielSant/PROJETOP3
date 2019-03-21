package com.example.projetop3rota;

public class Rotas {

    private String NomeOrigemViagem;
    private String NomeDestinoViagem;
    private float NumerodoVeiculo;
    private String HorarioPartida;

    public Rotas(String NomeOrigemViagem, String NomeDestinoViagem, float NumerodoVeiculo, String HorarioPartida) {
        this.NomeOrigemViagem = NomeOrigemViagem;
        this.NomeDestinoViagem = NomeDestinoViagem;
        this.NumerodoVeiculo = NumerodoVeiculo;
        this.HorarioPartida = HorarioPartida;
    }
    public String getNomeOrigemViagem() {
        return NomeOrigemViagem;
    }

    public void setNomeOrigemViagem(String nomeOrigemViagem) {
        NomeOrigemViagem = nomeOrigemViagem;
    }

    public String getNomeDestinoViagem() {
        return NomeDestinoViagem;
    }

    public void setNomeDestinoViagem(String nomeDestinoViagem) {
        NomeDestinoViagem = nomeDestinoViagem;
    }

    public float getNumerodoVeiculo() {
        return NumerodoVeiculo;
    }

    public void setNumerodoVeiculo(float numerodoVeiculo) {
        NumerodoVeiculo = numerodoVeiculo;
    }

    public String getHorarioPartida() {
        return HorarioPartida;
    }

    public void setHorarioPartida(String horarioPartida) {
        HorarioPartida = horarioPartida;
    }


}
