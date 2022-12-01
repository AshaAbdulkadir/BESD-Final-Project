package com.promineotechfinals.aaFurnitures.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotechfinals.aaFurnitures.entity.Order;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title ="Furniture Order Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
public interface AAFurnituresDeleteOrderController {

	//@formatter:off 
	@Operation(
			summary = "Create furniture order",
			description = "Returns created furniture",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "Created furniture is returned.", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = Order.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No such furniture exists.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))
			},
			
			parameters = {
					@Parameter(
							name = "OrderRequest",  
							required = true, 
							description = "The order as JSON")
			}
		)

	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	Order createOrder(
			@Valid 
			@RequestBody com.promineotechfinals.aaFurnitures.entity.OrderRequest orderRequest);
	
	
	//@formatter:on

			
}
