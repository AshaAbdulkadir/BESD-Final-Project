package com.promineotechfinals.aaFurnitures.controller.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.promineotechfinals.aaFurnitures.entity.Rooms;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;

public class FetchFurnitureTestSupport extends BaseTest {
	protected List<Furnitures> buildExpected() {
		List<Furnitures> list = new LinkedList<>();
		
		// @formatter: off
		
		list.add(Furnitures.builder()
				.roomId(Rooms.BED_ROOM)
				.material("Wood")
				.price(new BigDecimal("1485.00"))
				.build());
		
		list.add(Furnitures.builder()
				.roomId(Rooms.BED_ROOM)
				.material("Fabric")
				.price(new BigDecimal("1225.00"))
				.build());
		
		// @formatter: on
		
		Collections.sort(list);
		return list;
	}
	
	
	/**
	 * 
	 * @param error
	 * @param status
	 */
	protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
		// @formatter:off
		assertThat(error)
			.containsKey("message")
			.containsEntry("status code", HttpStatus.NOT_FOUND.value())
			.containsEntry("uri", "/Furnitures")
			.containsKey("timestamp")
			.containsEntry("reason", status.getReasonPhrase());
		// @formatter:on
	}
	
	
	
}
