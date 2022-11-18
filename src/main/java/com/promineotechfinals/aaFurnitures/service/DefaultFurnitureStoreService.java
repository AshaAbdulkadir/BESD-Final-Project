package com.promineotechfinals.aaFurnitures.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotechfinals.aaFurnitures.dao.FurnitureStoreDao;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultFurnitureStoreService implements FurnitureStoreService {
	
	@Autowired
	private FurnitureStoreDao furnitureStoreDao;

	@Override
	public List<Furnitures> fetchFurnitures(Rooms room, String material) {
		log.info("The fetchFurnitures method was called with room={} and material={}", room, material);
		
		List<Furnitures> furnitures = furnitureStoreDao.fetchFurnitures(room, material);
		
		if (furnitures.isEmpty()) {
			String msg = String.format("No furnitures found with room=%s and material=%s", room, material);

			throw new NoSuchElementException(msg);
		}

		Collections.sort(furnitures);
		return furnitures;
	}

}
