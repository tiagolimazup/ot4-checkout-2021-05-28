package br.com.zup.orange.sample.turma;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface TurmaRepository extends JpaRepository<Turma, Long> {

    Optional<Turma> findByNome(String nome);
}
