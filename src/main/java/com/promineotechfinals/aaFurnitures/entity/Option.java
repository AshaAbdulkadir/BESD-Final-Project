package com.promineotechfinals.aaFurnitures.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Option {
	protected void Student() {
	  }
	private Long optionPK;
	private String optionId;
	private OptionType category;
	private String material;
	private String name;
	private BigDecimal price;
	

}
