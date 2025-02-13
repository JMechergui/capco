package fr.capco.service.pricing.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.capco.entity.ProductType;
import fr.capco.entity.ProfessionalClient;
import fr.capco.service.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LowRevenueProfessionalPricingStrategyTest {

	private PricingStrategy pricingStrategy;
	private ProfessionalClient lowRevenueClient;
	private ProfessionalClient highRevenueClient;

	@BeforeEach
	void setUp() {
		pricingStrategy = new LowRevenueProfessionalPricingStrategy();
		highRevenueClient = new ProfessionalClient("rais1", "vat1", "siren1", 20_000_000);
		lowRevenueClient = new ProfessionalClient("rais2", "vat2", "siren2", 5_000_000);
	}

	@Test
	void testSupports_LowRevenueProfessionalClient() {
		assertThat(pricingStrategy.supports(lowRevenueClient)).isTrue();
	}

	@Test
	void testSupports_HighRevenueProfessionalClient() {
		assertThat(pricingStrategy.supports(highRevenueClient)).isFalse();
	}

	@Test
	void testGetPrice_ValidProductTypes() {
		assertThat(pricingStrategy.getPrice(ProductType.PREMIUM_PHONE)).isEqualTo(1150);
		assertThat(pricingStrategy.getPrice(ProductType.MID_RANGE_PHONE)).isEqualTo(600);
		assertThat(pricingStrategy.getPrice(ProductType.LAPTOP)).isEqualTo(1000);
	}

	@Test
	void testGetPrice_InvalidProductType() {
		assertThrows(IllegalArgumentException.class, () -> pricingStrategy.getPrice(null));
	}
}
