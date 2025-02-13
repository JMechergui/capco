package fr.capco.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import fr.capco.entity.Cart;
import fr.capco.entity.Client;
import fr.capco.entity.IndividualClient;
import fr.capco.entity.Product;
import fr.capco.entity.ProductType;
import fr.capco.service.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CartServiceTest {

	private CartService cartService;
	private IndividualClient client;
	private PricingStrategy pricingStrategy;

	@BeforeEach
	void setUp() {
		pricingStrategy = mock(PricingStrategy.class);
		when(pricingStrategy.supports(any(Client.class))).thenReturn(true);
		when(pricingStrategy.getPrice(ProductType.PREMIUM_PHONE)).thenReturn(1500.0);
		when(pricingStrategy.getPrice(ProductType.MID_RANGE_PHONE)).thenReturn(800.0);
		when(pricingStrategy.getPrice(ProductType.LAPTOP)).thenReturn(1200.0);

		cartService = new CartService(List.of(pricingStrategy));
		client = new IndividualClient(1L, "Jihed", "Mechergui");
	}

	@Test
	void testCalculateTotalCart_ValidCart() {
		Cart cart = new Cart(client,
				Arrays.asList(new Product("PREMIUM_PHONE", 2), new Product("LAPTOP", 1)));

		double total = cartService.calculateTotalCart(cart);

		assertThat(total).isEqualTo(1500 * 2 + 1200);
	}

	@Test
	void testCalculateTotalCart_NoPricingStrategy() {
		CartService cartServiceWithoutStrategy = new CartService(Collections.emptyList());
		Cart cart = new Cart(client, Arrays.asList(new Product("PREMIUM_PHONE", 1)));

		assertThrows(IllegalArgumentException.class, () -> cartServiceWithoutStrategy.calculateTotalCart(cart));
	}
}
