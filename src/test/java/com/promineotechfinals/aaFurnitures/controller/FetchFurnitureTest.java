package com.promineotechfinals.aaFurnitures.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotechfinals.aaFurnitures.controller.support.FetchFurnitureTestSupport;
import com.promineotechfinals.aaFurnitures.entity.Rooms;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
/**
 * 
 * @author Asha
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:aaFurniture/aaFurnitures.sql",
"classpath:aaFurniture/aaFurnituresData.sql" }, config = @SqlConfig(encoding = "utf-8"))


class FetchFurnitureTest extends FetchFurnitureTestSupport {

	@Test
	void testThatFurnituresAreReturnedWhenAValidRoomAndMaterialAreSupplied() {
		
		
		// Given: a valid room, material and URI
		Rooms room = Rooms.BED_ROOM;
		String material = "Wood";
		String uri = String.format("%s?room=%s&material=%s", getBaseUri(), room, material);

		// When: a connection is made to the URI
		ResponseEntity<List<Furnitures>> response = 
				getRestTemplate().exchange(uri, HttpMethod.GET, null,
						new ParameterizedTypeReference<>() {
						});

		// Then: a success (OK - 200) status code is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// And: the actual list is the same as the expected list
		List<Furnitures> actual = response.getBody();
		List<Furnitures> expected = buildExpected();

		assertThat(actual).isEqualTo(expected);

	}

}
