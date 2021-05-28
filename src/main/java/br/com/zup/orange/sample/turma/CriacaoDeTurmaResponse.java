package br.com.zup.orange.sample.turma;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

class CriacaoDeTurmaResponse {

    @JsonProperty
    final Long id;

    @JsonProperty
    final String nome;

    @JsonProperty
    final LocalDate dataInicio;

    @JsonProperty
    final LocalDate dataFim;

    CriacaoDeTurmaResponse(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dataInicio = turma.getDataInicio();
        this.dataFim = turma.getDataFim();
    }
}
