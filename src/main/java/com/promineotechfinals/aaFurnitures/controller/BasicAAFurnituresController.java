package com.promineotechfinals.aaFurnitures.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;
@RestController
public class BasicAAFurnituresController implements AAFurnituresController {

	@Override
	public List<Furnitures> fetchFurnitures(String room, String material) {
		return null;
	}

}
