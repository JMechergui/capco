package fr.capco.service.pricing.strategy;

import fr.capco.entity.Client;
import fr.capco.entity.IndividualClient;
import fr.capco.entity.ProductType;
import fr.capco.service.pricing.PricingStrategy;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class IndividualPricingStrategy implements PricingStrategy {

	@Override
	public boolean supports(Client client) {
		return client instanceof IndividualClient;
	}

	@Override
	public double getPrice(ProductType productType) {
		Assert.notNull(productType, "productType is required");
		if (productType.equals(ProductType.PREMIUM_PHONE))
			return 1500;
		else if (productType.equals(ProductType.MID_RANGE_PHONE))
			return 800;
		if (productType.equals(ProductType.LAPTOP))
			return 1200;
		else
			throw new IllegalArgumentException("productType " + productType + " not supported yet");
	}	 
}