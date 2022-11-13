package com.promineotechfinals.aaFurnitures.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicAAFurnituresController implements AAFurnituresController {

	@Override
	public List<Furnitures> fetchFurnitures(String room, String material) {
		log.debug("room={}, material={}", room, material);
		
		return null;
	}

}
