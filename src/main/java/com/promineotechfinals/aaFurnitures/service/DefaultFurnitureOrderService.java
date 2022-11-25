package com.promineotechfinals.aaFurnitures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotechfinals.aaFurnitures.dao.FurnitureOrderDao;
import com.promineotechfinals.aaFurnitures.entity.Customer;
import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

@Service
public class DefaultFurnitureOrderService implements FurnitureOrderService {
	
	@Autowired
	private FurnitureOrderDao furnitureOrderDao;

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		Customer customer = furnitureOrderDao.fetchCustomer(orderRequest.getCustomer());
		
		return furnitureOrderDao.createOrder(orderRequest);
	}

}
