package com.promineotechfinals.aaFurnitures.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class Furnitures implements Comparable<Furnitures> {
	
	private Long roomPK;
	private Rooms roomId;
	private String material;
	private BigDecimal price;
	
	@JsonIgnore
	public Long getRoomPK() {
		return roomPK;
	}

	@Override
	public int compareTo(Furnitures that) {
		
				// @formatter:off
				return Comparator
						.comparing(Furnitures::getRoomId)
						.thenComparing(Furnitures::getMaterial)
						.compare(this, that);
				
				// @formatter: on;
	}

}
