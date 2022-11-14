package com.promineotechfinals.aaFurnitures.service;

import java.util.List;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;

public interface FurnitureStoreService {
	/**
	 * 
	 * @param room
	 * @param material
	 * @return
	 */

	List<Furnitures> fetchFurnitures(String room, String material);

}
