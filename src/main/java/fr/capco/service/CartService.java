package fr.capco.service;

import org.springframework.stereotype.Service;

import fr.capco.entity.Cart;
import fr.capco.entity.Client;
import fr.capco.entity.ProductType;
import fr.capco.service.pricing.PricingStrategy;

import java.util.List;

@Service
public class CartService {

	private final List<PricingStrategy> pricingStrategies;

	public CartService(List<PricingStrategy> pricingStrategies) {

		this.pricingStrategies = pricingStrategies;
	}

	/**
	 * 
	 * @param cart
	 * @return
	 */
	public double calculateTotalCart(Cart cart) {
		return cart.getProducts().stream()
				.map(product -> resolvePrice(ProductType.valueOf( product.getType()), cart.getClient()) * product.getQuantity())
				.reduce(0d, (a, b) -> a + b);
	}

	/**
	 * 
	 * @param productType
	 * @param client
	 * @return
	 */
	private double resolvePrice(ProductType productType, Client client) {
		return pricingStrategies.stream().filter(strategy -> strategy.supports(client)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("no strategy found to calculate the cart"))
				.getPrice(productType);
	}
}
