package com.promineotechfinals.aaFurnitures.controller;

import org.springframework.web.bind.annotation.RestController;

import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BasicAAFurnituresOrderController implements AAFurnituresOrderController {

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		log.debug("Order={}", orderRequest);
		return null;
	}

}
