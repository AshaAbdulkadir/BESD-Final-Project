package com.promineotechfinals.aaFurnitures.entity;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Order {
	
	private Long orderPK;
	private Customer customer;
	private Furnitures room;
	private Color color;
	private List<Option> options;
	private BigDecimal price;

	
}
