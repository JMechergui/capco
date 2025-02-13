package fr.capco.service.pricing;


import fr.capco.entity.Client;
import fr.capco.entity.ProductType;

public interface PricingStrategy {

	/**
	 * check if the client is supported
	 * @param client
	 * @return
	 */
	boolean supports(Client client);
	/**
	 * get the price
	 * @param productType
	 * @return
	 */
    double getPrice(ProductType productType);
}