package fr.capco.service.pricing.strategy;

import fr.capco.entity.Client;
import fr.capco.entity.ProductType;
import fr.capco.entity.ProfessionalClient;
import fr.capco.service.pricing.PricingStrategy;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class HighRevenueProfessionalPricingStrategy implements PricingStrategy {

	@Override
	public boolean supports(Client client) {
		return client instanceof ProfessionalClient && ((ProfessionalClient) client).getAnnualTurnover()> 10_000_000;
	}

	@Override
	public double getPrice(ProductType productType) {
		Assert.notNull(productType, "productType is required");
		if (productType.equals(ProductType.PREMIUM_PHONE))
			return 1000;
		else if (productType.equals(ProductType.MID_RANGE_PHONE))
			return 550;
		if (productType.equals(ProductType.LAPTOP))
			return 900;
		else
			throw new IllegalArgumentException("productType " + productType + " not supported yet");
	}
}