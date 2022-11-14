package com.promineotechfinals.aaFurnitures.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultFurnitureStoreService implements FurnitureStoreService {

	@Override
	public List<Furnitures> fetchFurnitures(String room, String material) {
		log.info("The fetchFurnitures method was called with room={} and material={}", room, material);
		return null;
	}

}
