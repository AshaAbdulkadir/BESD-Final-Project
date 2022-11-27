package com.promineotechfinals.aaFurnitures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promineotechfinals.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = { ComponentScanMarker.class })
public class FurnitureStore {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureStore.class, args);

	}
	
// REST service
// added database dependency/ spring auto configuration- to enable connection pool 
// jdbc template(query includes-connection/prepared statement/resultset) takes boiler plate away
// database configuration in application yaml file = started with no error = configured correctly
	
}
