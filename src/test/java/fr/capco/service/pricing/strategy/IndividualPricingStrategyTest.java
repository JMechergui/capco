package fr.capco.service.pricing.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.capco.entity.Client;
import fr.capco.entity.IndividualClient;
import fr.capco.entity.ProductType;
import fr.capco.entity.ProfessionalClient;
import fr.capco.service.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndividualPricingStrategyTest {

	private PricingStrategy pricingStrategy;
	private IndividualClient particularClient;
	private Client nonParticularClient;

	@BeforeEach
	void setUp() {
		pricingStrategy = new IndividualPricingStrategy();
		particularClient = new IndividualClient(1L, "Jihed", "mechergui");
		nonParticularClient = new ProfessionalClient("rais1", "vat1", "siren1", 20_000_000);
	}

	@Test
	void testSupports_ParticularClient() {
		assertThat(pricingStrategy.supports(particularClient)).isTrue();
	}

	@Test
	void testSupports_NonParticularClient() {
		assertThat(pricingStrategy.supports(nonParticularClient)).isFalse();
	}

	@Test
	void testGetPrice_ValidProductTypes() {
		assertThat(pricingStrategy.getPrice(ProductType.PREMIUM_PHONE)).isEqualTo(1500);
		assertThat(pricingStrategy.getPrice(ProductType.MID_RANGE_PHONE)).isEqualTo(800);
		assertThat(pricingStrategy.getPrice(ProductType.LAPTOP)).isEqualTo(1200);
	}

	@Test
	void testGetPrice_InvalidProductType() {
		assertThrows(IllegalArgumentException.class, () -> pricingStrategy.getPrice(null));
	}
}
