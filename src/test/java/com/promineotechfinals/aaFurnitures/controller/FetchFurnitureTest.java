package com.promineotechfinals.aaFurnitures.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.promineotechfinals.aaFurnitures.controller.support.FetchFurnitureTestSupport;
import com.promineotechfinals.aaFurnitures.entity.FurnitureRoom;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FetchFurnitureTest extends FetchFurnitureTestSupport {

	@Test
	void testThatFurnituresAreReturnedWhenAValidRoomAndMaterialAreSupplied() {
		System.out.println(getBaseUri());
		
		//Using the Given-When-Then of representing test (Part of BDD by Daniel Terhorst and Chris matts)
		
		// Given: a valid model, trim and URI
		FurnitureRoom room = FurnitureRoom.BED_ROOM;
		String material = "Unknown Value";
		String uri = String.format("http://localhost:%d/aaFurnitures?room=%s&material=%s", getBaseUri(), room, material);

		// When: a connection is made to the URI
		ResponseEntity<Furnitures> response = 
				getRestTemplate().getForEntity(uri, Furnitures.class);

		// Then: a not found (404) status code is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// And: an error message is returned
		//Map<String, Object> error = response.getBody();

		//assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
	}

}
