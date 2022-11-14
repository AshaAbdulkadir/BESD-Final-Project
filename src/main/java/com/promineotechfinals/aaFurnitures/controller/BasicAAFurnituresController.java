package com.promineotechfinals.aaFurnitures.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.service.FurnitureStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicAAFurnituresController implements AAFurnituresController {
	
	
	@Autowired 
	private FurnitureStoreService furnitureStoreService;

	@Override
	public List<Furnitures> fetchFurnitures(String room, String material) {
		log.debug("room={}, material={}", room, material);
		
		return furnitureStoreService.fetchFurnitures(room, material);
	}

}
