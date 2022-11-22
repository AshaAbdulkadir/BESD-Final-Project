package com.promineotechfinals.aaFurnitures.dao;

import org.springframework.stereotype.Component;

import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultFurnitureOrderDao implements FurnitureOrderDao {

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		log.debug("Order={}", orderRequest);
		return null;
	}

}
