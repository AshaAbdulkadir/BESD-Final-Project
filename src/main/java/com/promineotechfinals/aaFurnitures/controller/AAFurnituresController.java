package com.promineotechfinals.aaFurnitures.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/furnitures")
@OpenAPIDefinition(info = @Info(title ="Furniture Store Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
public interface AAFurnituresController {
	//@formatter:off 
	@Operation(
			summary = "Returns a list of Furnitures",
			description = "Returns a list of Furniture given an option of room and /or material",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A list of furnitures is returned", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = Furnitures.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No furnitures were found with input supplied", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))
			},
			
			parameters = {
					@Parameter(
							name = "room", 
							allowEmptyValue = false, 
							required = false, 
							description = "The room name (i.e 'BED_ROOM')"),
					@Parameter(
							name = "material", 
							allowEmptyValue = false, 
							required = false, 
							description = "The type of material (i.e 'Wood')")
			}
		)
	//@formatter:on
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<Furnitures> fetchFurnitures(
			 @RequestParam(required = false)
			  	String room, 
			  @RequestParam(required = false)
			  	String material);

}