package br.com.zup.orange.sample.turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class TurmaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonMapper;

    @Autowired
    TurmaRepository turmaRepository;

    @Test
    void deveCriarUmaNovaTurma() throws Exception {
        CriacaoDeTurmaRequest request = new CriacaoDeTurmaRequest("OrangeTalents 4", LocalDate.now(),
                LocalDate.now().plusMonths(3));

        String json = jsonMapper.writeValueAsString(request);

        mockMvc.perform(post("/turmas")
                    .contentType(APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value(request.nome))
                .andExpect(jsonPath("$.dataInicio").value(request.dataInicio.format(ofPattern("yyyy-MM-dd"))))
                .andExpect(jsonPath("$.dataFim").value(request.dataFim.format(ofPattern("yyyy-MM-dd"))));

        Optional<Turma> turma = turmaRepository.findByNome(request.nome);

        assertTrue(turma.isPresent());

        assertEquals(request.dataInicio, turma.get().getDataInicio());
        assertEquals(request.dataFim, turma.get().getDataFim());
    }
}
