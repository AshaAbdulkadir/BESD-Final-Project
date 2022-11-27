package com.promineotechfinals.aaFurnitures.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.promineotechfinals.aaFurnitures.controller.support.CreateFurnitureOrderTestSupport;
import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

@EnableAutoConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:aaFurniture/aaFurnitures.sql",
"classpath:aaFurniture/aaFurnituresData.sql" }, config = @SqlConfig(encoding = "utf-8"))

class CreateFurnitureOrderTest  extends CreateFurnitureOrderTestSupport{
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void testCreateFurnitureOrderReturnsSuccess201() {
		
		//Given: an order as JSON
		String body = createOrderBody();
		String uri = String.format("http://localhost:%d/orders", serverPort);
		
		int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders");
		int numRowsOptions = JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <String> bodyEntity = new HttpEntity<>(body, headers);
		
		
		//When: an order is sent
		ResponseEntity <Order> response = restTemplate.exchange(uri,HttpMethod.POST, bodyEntity, Order.class);
		
		
		//Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
	
		//And: the returned order is correct
		assertThat(response.getBody()).isNotNull();
		
		Order order = response.getBody();
		assertThat(order.getCustomer().getCustomerId()).isEqualTo("JAMES_PAUL");
		assertThat(order.getRoom().getRoomId()).isEqualTo(Rooms.BED_ROOM);
		assertThat(order.getRoom().getMaterial()).isEqualTo("Wood");
		assertThat(order.getColor().getColorId()).isEqualTo("Dark_Finish");
		assertThat(order.getOptions()).hasSize(3);
	
		
		
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowsOrders + 1);
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options")).isEqualTo(numRowsOptions + 3);
	}

}
