package com.promineotechfinals.aaFurnitures.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class Furnitures {
	
	private Long roomPK;
	private FurnitureRoom roomId;
	private String material;
	private BigDecimal price;

}
