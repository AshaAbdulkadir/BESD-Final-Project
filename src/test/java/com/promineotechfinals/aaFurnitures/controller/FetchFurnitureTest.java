package com.promineotechfinals.aaFurnitures.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotechfinals.aaFurnitures.Constants;
import com.promineotechfinals.aaFurnitures.controller.support.FetchFurnitureTestSupport;
import com.promineotechfinals.aaFurnitures.entity.Rooms;
import com.promineotechfinals.aaFurnitures.service.FurnitureStoreService;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
/**
 * 
 * @author Asha
 *
 */

class FetchFurnitureTest {

	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(scripts = { "classpath:aaFurniture/aaFurnitures.sql",
	"classpath:aaFurniture/aaFurnituresData.sql" }, config = @SqlConfig(encoding = "utf-8"))
	class TestsThatDoNotPolluteTheApplicationContext extends FetchFurnitureTestSupport {
		
		@Autowired
		private TestRestTemplate restTemplate;

		@LocalServerPort
		private int serverPort;
		
		/**
		 * 
		 * @param room
		 * @param material
		 * @param reason
		 */
		@ParameterizedTest
		@MethodSource("com.promineotechfinals.aaFurnitures.controller.FetchFurnitureTest#parametersForInvalidInput")
		void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSelected(String room, String material, String reason) {

			// Given: a valid room, material and URI

			String uri = String.format("http://localhost:%d/Furnitures?room=%s&material=%s", serverPort, room, material);

			// When: a connection is made to the URI
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<>() {
					});

			// Then: a not found (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

			// And: an error message is returned
			Map<String, Object> error = response.getBody();

			assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
		}

		/**
		 * 
		 */
		@Test
		void testThatAnErrorMessageIsReturnedWhenAnUnknownMaterialIsSelected() {

			// Given: a valid model, trim and URI
			Rooms room = Rooms.BED_ROOM;
			String material = "Unknown Value";
			String uri = String.format("http://localhost:%d/?room=%s&material=%s", serverPort, room, material);

			// When: a connection is made to the URI
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<>() {
					});

			// Then: a not found (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

			// And: an error message is returned
			Map<String, Object> error = response.getBody();

			assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
		}
		
		
		/**
		 * 
		 */
		@Test
		void testThatFurnituresAreReturnedWhenAValidRoomAndMaterialAreSelected() {
			
			
			// Given: a valid room, material and URI
			Rooms room = Rooms.BED_ROOM;
			String material = "Wood";
			String uri = String.format("%s?room=%s&material=%s", serverPort, room, material);

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
	
	
	/**
	 * Parameterized supplier method to be in the outer class
	 * @return
	 */
	
	static Stream<Arguments> parametersForInvalidInput() {
		// @formatter:off
		return Stream.of(
			arguments("BED_ROOM", "@#$%^&&%", "Material contains non-alpha-numeric characters"),
			arguments("BED_ROOM", "L".repeat(Constants.MATERIAL_MAX_LENGTH), "Material length too long"),
			arguments("INVALID", "Wood", "Room is not enum value")
		// @formatter:on
		);
	}
	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(scripts = { "classpath:aaFurniture/aaFurnitures.sql",
	"classpath:aaFurniture/aaFurnituresData.sql" }, config = @SqlConfig(encoding = "utf-8"))
	class TestsThatPolluteTheApplicationContext extends FetchFurnitureTestSupport {
		
		@LocalServerPort
		private int serverPort;

		@MockBean
		private FurnitureStoreService furnitureStoreService;

		/**
		 * 
		 */
		@Test
		void testThatAnUnplannedErrorResultsInA500Status() {

			// Given: a valid model, trim and URI
			Rooms room = Rooms.BED_ROOM;
			String material = "Invalid";
			String uri = String.format("http://localhost:%d/?room=%s&material=%s", serverPort, room, material);

			doThrow(new RuntimeException("Oh No!")).when(furnitureStoreService).fetchFurnitures(room, material);

			// When: a connection is made to the URI
			ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<>() {
					});

			// Then: an internal server error (500) status is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

			// And: an error message is returned
			Map<String, Object> error = response.getBody();

			assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}



}
