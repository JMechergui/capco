package fr.capco.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import fr.capco.validation.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
	
	@NotNull
	@ValidEnum(enumClass = ProductType.class, message = "Invalid status. Allowed values are: PREMIUM_PHONE, MID_RANGE_PHONE, LAPTOP")
	private String type;
	
	@Min(value = 1)
	private int quantity;
}