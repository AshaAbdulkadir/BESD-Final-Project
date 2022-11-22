package com.promineotechfinals.aaFurnitures.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Order {
	
	private Customer customer;
	private Rooms room;
	private Color color;
	private List<Option> options;
	private BigDecimal price;
	
	
	  @JsonIgnore
	  public Long getOrderPK() {
	  return orderPK;
	  }
	 

}
