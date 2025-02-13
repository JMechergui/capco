package fr.capco.ctrl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.capco.entity.Cart;
import fr.capco.entity.Client;
import fr.capco.entity.IndividualClient;
import fr.capco.entity.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
class CartCtrlIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	Client client = null;

	@BeforeEach
	public void setUp() {
		client = new IndividualClient(1L, "Jihed", "Mechergui");

	}

	@Test
	void testCalculate_OK_RESPONSE() throws Exception {

		Cart cart = new Cart(client, Arrays.asList(Product.builder().type("PREMIUM_PHONE").quantity(1).build(),
				Product.builder().type("LAPTOP").quantity(1).build()));

		mockMvc.perform(
				post("/cart/calculate").contentType("application/json").content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isOk()).andExpect(content().string("2700.0"));
	}

	@Test
	void testCalculate_NOK_RESPONSE() throws Exception {

		Cart cart = new Cart(client, Arrays.asList(Product.builder().quantity(1).build(),
				Product.builder().type("LAPTOP").quantity(1).build()));
		mockMvc.perform(
				post("/cart/calculate").contentType("application/json").content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isBadRequest());
	}
}