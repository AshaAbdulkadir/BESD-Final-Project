package com.promineotechfinals.aaFurnitures.service;

import java.util.List;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

public interface FurnitureStoreService {
	/**
	 * 
	 * @param room
	 * @param material
	 * @return
	 */

	List<Furnitures> fetchFurnitures(Rooms room, String material);

}
