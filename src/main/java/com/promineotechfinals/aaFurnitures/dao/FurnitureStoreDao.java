package com.promineotechfinals.aaFurnitures.dao;

import java.util.List;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

public interface FurnitureStoreDao {
	/**
	 * 
	 * @param room
	 * @param material
	 * @return
	 */

	List<Furnitures> fetchFurnitures(Rooms room, String material);

}
