package br.com.zup.orange.sample.turma;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

class CriacaoDeTurmaRequest {

    @JsonProperty
    final String nome;

    @JsonProperty
    final LocalDate dataInicio;

    @JsonProperty
    final LocalDate dataFim;

    CriacaoDeTurmaRequest(String nome, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    Turma toModel() {
        return new Turma(nome, dataInicio, dataFim);
    }
}
