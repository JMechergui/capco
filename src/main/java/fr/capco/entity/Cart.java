package fr.capco.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class Cart {
	
	@Valid
	@NotNull
    private Client client;
	
	@Valid
	@NotEmpty
    private List<Product> products;

	public Cart(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }
}