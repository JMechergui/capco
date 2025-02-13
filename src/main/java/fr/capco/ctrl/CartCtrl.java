package fr.capco.ctrl;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import fr.capco.entity.Cart;
import fr.capco.service.CartService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@RequestMapping("/cart")
public class CartCtrl {

	private CartService cartService;

	public CartCtrl(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/calculate")
	public double calculate(@Valid @RequestBody Cart cart) {

		Long start = System.currentTimeMillis();
	
		log.info("call calculate cart");
		double totalCart = cartService.calculateTotalCart(cart);
		log.info("calculate cart ends in {}", (System.currentTimeMillis() - start));

		return totalCart;
	}
}