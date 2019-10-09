package br.com.fcamara.asnpark.controller.test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fcamara.asnpark.domain.EntradaSaidaVeiculo;
import br.com.fcamara.asnpark.repository.EntradaSaidaVeiculoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EntradaSaidaVeiculoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	 @MockBean
	 private EntradaSaidaVeiculoRepository mockRepository;
	
	@Test
	public void save_emptyVeiculoOrEstabelecimento_400() throws Exception {

        String empresaInJson = "{\"dataHoraEntrada\": \"2019-10-08 20:12\"}";

        mockMvc.perform(post("/entradaSaidaVeiculo")
                .content(empresaInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors", hasItem("Por favor, insira o ID do estabelecimento ja cadastrado.")))
                .andExpect(jsonPath("$.errors", hasItem("Por favor, insira o ID do veiculo ja cadastrado.")))                ;

        verify(mockRepository, times(0)).save(any(EntradaSaidaVeiculo.class));
    }
	
	@Test
	public void save_emptyEstabelecimento_400() throws Exception {

        String empresaInJson = "{\"dataHoraEntrada\": \"2019-10-08 20:12\",\"dataHoraSaida\": \"\",\"veiculo\":{ \"id\" : 1}}";

        mockMvc.perform(post("/entradaSaidaVeiculo")
                .content(empresaInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItem("Por favor, insira o ID do estabelecimento ja cadastrado.")))               ;

        verify(mockRepository, times(0)).save(any(EntradaSaidaVeiculo.class));
    }
	
	@Test
	public void save_emptyVeiculo_400() throws Exception {

        String empresaInJson = "{\"dataHoraEntrada\": \"2019-10-08 20:12\",\"dataHoraSaida\": \"\",\"estabelecimento\": { \"id\" : 1}}";

        mockMvc.perform(post("/entradaSaidaVeiculo")
                .content(empresaInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItem("Por favor, insira o ID do veiculo ja cadastrado.")))               ;

        verify(mockRepository, times(0)).save(any(EntradaSaidaVeiculo.class));
    }
}