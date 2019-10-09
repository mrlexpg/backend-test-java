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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fcamara.asnpark.domain.Veiculo;
import br.com.fcamara.asnpark.repository.VeiculoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VeiculoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VeiculoRepository mockRepository;

	@Test
	@WithMockUser(username="fcadm",roles={"USER","ADMIN"})
	public void save_emptyVeiculo_400() throws Exception {

		String veiculoInJson = "{\"placa\":\"ABC-1234\"}";

		mockMvc.perform(post("/veiculo")
				.content(veiculoInJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.timestamp", is(notNullValue())))
		.andExpect(jsonPath("$.status", is(400)))
		.andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors", hasSize(4)))
		.andExpect(jsonPath("$.errors", hasItem("Por favor, insira a marca do veiculo.")))
		.andExpect(jsonPath("$.errors", hasItem("Por favor, insira a cor do veiculo.")))
		.andExpect(jsonPath("$.errors", hasItem("Por favor, insira o modelo do veiculo.")))
		.andExpect(jsonPath("$.errors", hasItem("Por favor, insira ao tipo do veiculo (Carro / Moto).")));

		verify(mockRepository, times(0)).save(any(Veiculo.class));
	}
}