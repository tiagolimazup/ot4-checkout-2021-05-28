package br.com.zup.orange.sample.turma;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
class TurmaController {

    final TurmaRepository turmaRepository;

    TurmaController(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @PostMapping("/turmas")
    ResponseEntity<CriacaoDeTurmaResponse> criaNovaTurma(@RequestBody CriacaoDeTurmaRequest request,
                                                         UriComponentsBuilder uriBuilder) {
        Turma turma = turmaRepository.save(request.toModel());

        return ResponseEntity.created(uriBuilder.path("/turmas/{id}").build(turma.getId()))
                .body(new CriacaoDeTurmaResponse(turma));
    }
}
