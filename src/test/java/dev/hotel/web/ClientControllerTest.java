package dev.hotel.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientRepository cr;

	@Test
	void testListClients() throws Exception {

		Client c1 = new Client();
		c1.setNom("Fulano");
		c1.setPrenoms("P1");

		Client c2 = new Client();
		c2.setNom("Sutano");
		c2.setPrenoms("P2");

		when(cr.findAll(PageRequest.of(0, 3))).thenReturn(new PageImpl<>(Arrays.asList(c1, c2)));

		this.mockMvc.perform(get("/clients?start=0&size=3"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].nom").value("Fulano"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].prenoms").value("P1"))
				.andExpect(MockMvcResultMatchers.jsonPath("[1].nom").value("Sutano"))
				.andExpect(MockMvcResultMatchers.jsonPath("[1].prenoms").value("P2"));
		;

	}

	@Test
	void testClientsUUID() throws Exception {

		UUID uuid = UUID.randomUUID();
		Client c1 = new Client();
		c1.setUuid(uuid);
		c1.setNom("Fulano");
		c1.setPrenoms("P1");

		Optional<Client> op = Optional.of(c1);

		when(cr.findById(uuid)).thenReturn(op);

		this.mockMvc.perform(get("/client/" + uuid)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Fulano"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("P1"));
	}

	@Test
	void testNewClient() throws Exception {

	}
}
