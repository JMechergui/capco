package fr.capco.service.pricing.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.capco.entity.ProductType;
import fr.capco.entity.ProfessionalClient;
import fr.capco.service.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;

class HighRevenueProfessionalPricingStrategyTest {

	private PricingStrategy pricingStrategy;
	private ProfessionalClient highRevenueClient;
	private ProfessionalClient lowRevenueClient;

	@BeforeEach
	void setUp() {
		pricingStrategy = new HighRevenueProfessionalPricingStrategy();
		highRevenueClient = new ProfessionalClient("rais1", "vat1", "siren1", 20_000_000);
		lowRevenueClient = new ProfessionalClient("rais2", "vat2", "siren2", 5_000_000);
	}

	@Test
	void testSupports_HighRevenueProfessionalClient() {
		assertThat(pricingStrategy.supports(highRevenueClient)).isTrue();
	}

	@Test
	void testSupports_LowRevenueProfessionalClient() {
		assertThat(pricingStrategy.supports(lowRevenueClient)).isFalse();
	}

	@Test
	void testGetPrice_ValidProductTypes() {
		assertThat(pricingStrategy.getPrice(ProductType.PREMIUM_PHONE)).isEqualTo(1000);
		assertThat(pricingStrategy.getPrice(ProductType.MID_RANGE_PHONE)).isEqualTo(550);
		assertThat(pricingStrategy.getPrice(ProductType.LAPTOP)).isEqualTo(900);
	}

	@Test
	void testGetPrice_InvalidProductType() {
		assertThrows(IllegalArgumentException.class, () -> pricingStrategy.getPrice(null));
	}
}