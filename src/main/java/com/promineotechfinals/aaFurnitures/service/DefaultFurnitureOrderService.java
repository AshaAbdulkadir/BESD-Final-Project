package com.promineotechfinals.aaFurnitures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotechfinals.aaFurnitures.dao.FurnitureOrderDao;
import com.promineotechfinals.aaFurnitures.entity.Color;
import com.promineotechfinals.aaFurnitures.entity.Customer;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Option;
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
		
		
		// extracted method
		Customer customer = getCustomer(orderRequest);
		Furnitures furniture = getRoom(orderRequest);
		Color color = getColor(orderRequest);
		List<Option> options = getOption(orderRequest);
		
		
		//calculate price
		BigDecimal price =
		furniture.getPrice().add(color.getPrice());
		
		for(Option option : options) {
			price = price.add(option.getPrice());
		}
		
		return furnitureOrderDao.saveOrder(customer, furniture, color, price, options);
		
	}
	
	
	private List<Option> getOption(OrderRequest orderRequest) {
		
	 return furnitureOrderDao.fetchOptions(orderRequest.getOptions());
	}


	// helper methods 
	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	protected Color getColor(OrderRequest orderRequest) {
		return furnitureOrderDao.fetchColor(orderRequest.getColor())
				.orElseThrow(() -> new NoSuchElementException(
						"Color with ID=" + orderRequest.getColor() + "was not found"));
	}
	
	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	protected Furnitures getRoom(OrderRequest orderRequest) {
		return furnitureOrderDao
				.fetchRoom(orderRequest.getRoom(),orderRequest.getMaterial())
					.orElseThrow(() -> new NoSuchElementException("Room with ID=" 
							+ orderRequest.getRoom() + ", material="
							+ orderRequest.getMaterial() + " was not found"));
	}
	
	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	protected Customer getCustomer(OrderRequest orderRequest) {
		return furnitureOrderDao.fetchCustomer(orderRequest.getCustomer())
				.orElseThrow(() -> new NoSuchElementException("Customer with ID ="
						+ orderRequest.getCustomer() + " was not found"));
	}

}
