package com.promineotechfinals.aaFurnitures.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotechfinals.aaFurnitures.dao.FurnitureOrderDao;
import com.promineotechfinals.aaFurnitures.entity.Color;
import com.promineotechfinals.aaFurnitures.entity.Customer;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultFurnitureOrderService implements FurnitureOrderService {
	
	@Autowired
	private FurnitureOrderDao furnitureOrderDao;
	
	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		
		log.info("Service: Order={}", orderRequest);
		
		
		// refactor- extracted method
		Customer customer = getCustomer(orderRequest);
		Furnitures furniture = getRoom(orderRequest);
		Color color = getColor(orderRequest);
		
		return null;
	}

	protected Color getColor(OrderRequest orderRequest) {
		return furnitureOrderDao.fetchColor(orderRequest.getColor())
				.orElseThrow(() -> new NoSuchElementException(
						"Color with ID=" + orderRequest.getColor() + "was not found"));
	}

	protected Furnitures getRoom(OrderRequest orderRequest) {
		return furnitureOrderDao
				.fetchRoom(orderRequest.getRoom(),orderRequest.getMaterial())
					.orElseThrow(() -> new NoSuchElementException("Room with ID=" 
							+ orderRequest.getRoom() + ", material="
							+ orderRequest.getMaterial() + " was not found"));
	}

	protected Customer getCustomer(OrderRequest orderRequest) {
		return furnitureOrderDao.fetchCustomer(orderRequest.getCustomer())
				.orElseThrow(() -> new NoSuchElementException("Customer with ID ="
						+ orderRequest.getCustomer() + " was not found"));
	}

}
