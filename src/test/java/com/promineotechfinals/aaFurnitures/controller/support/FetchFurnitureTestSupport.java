package com.promineotechfinals.aaFurnitures.controller.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
}
