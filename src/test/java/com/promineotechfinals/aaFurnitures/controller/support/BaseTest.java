package com.promineotechfinals.aaFurnitures.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import lombok.Getter;

public class BaseTest {

 private int serverPort;
 
 @Autowired
 @Getter
 private TestRestTemplate restTemplate;
 
 /**
  * 
  * @return
  */
 protected String getBaseUriForFurnitures() {
	 return String.format("http://localhost:%d/aafurnitures", serverPort);
 }
 
 /**
  * 
  * @return
  */
 protected String getBaseUriForOrders() {
	 return String.format("http://localhost:%d/aafurnitures", serverPort);
 }
 
 
}
