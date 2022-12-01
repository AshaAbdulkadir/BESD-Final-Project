package com.promineotechfinals.aaFurnitures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;
import com.promineotechfinals.aaFurnitures.service.FurnitureOrderService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BasicAAFurnituresUpdateOrderController implements AAFurnituresCreateOrderController {
	
	
	@Autowired
	private FurnitureOrderService furnitureOrderService;

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		log.debug("Order={}", orderRequest);
		return furnitureOrderService.createOrder(orderRequest);
	}

}
