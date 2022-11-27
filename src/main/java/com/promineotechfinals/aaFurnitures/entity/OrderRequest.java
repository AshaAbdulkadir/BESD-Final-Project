package com.promineotechfinals.aaFurnitures.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;


@Data
public class OrderRequest {
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String customer;
	
	@NotNull
	private Rooms room;
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String material;
	
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String color;


	private List<@Length(max = 30) @Pattern(regexp = "[\\w\\s]*") String> options;

}
