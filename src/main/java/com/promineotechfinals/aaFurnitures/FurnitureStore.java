package com.promineotechfinals.aaFurnitures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = { com.promineotechfinals.ComponentScanMarker.class })
public class FurnitureStore {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureStore.class, args);

	}

}
