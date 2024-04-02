package com.edipo.first;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edipo.first.controller.UsuarioController;
import com.edipo.first.repository.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {
	
	@Autowired
	UsuarioController usuarioController;
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UsuarioRepository usuarioRepository;
	
	@Test
    public void whenUsuarioControllerInjected_thenNotNull() {
        assertThat(usuarioController).isNotNull();
    }
	
	@Test
    public void whenPostRequestToUsuarioAndValidUsuario_thenCorrectResponse() throws Exception {
        String user = "{\"nome\": \"First\", \"email\" : \"first@domain.com\", \"senha\" : \"123456\", \"confirmacaoSenha\" : \"123456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	@Test
    public void whenPostRequestToUsuarioAndInValidUsuario_thenCorrectReponse() throws Exception {
		String user = "{\"nome\": \"a\", \"email\" : \"first@domain.com\", \"senha\" : \"123456\", \"confirmacaoSenha\" : \"123456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Is.is("O nome deve ter entre 3 e 50 caracteres.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
	
	@Test
    public void whenPostRequestToUsuarioAndInValidUsuarioConfirmacaoSenha_thenCorrectReponse() throws Exception {
		String user = "{\"nome\": \"First\", \"email\" : \"first@domain.com\", \"senha\" : \"123456\", \"confirmacaoSenha\" : \"123546\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.confirmacaoSenha", Is.is("A confirmação da senha não corresponde à senha fornecida.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
