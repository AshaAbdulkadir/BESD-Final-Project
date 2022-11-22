package com.promineotechfinals.aaFurnitures.service;

import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

public interface FurnitureOrderService {

	Order createOrder(OrderRequest orderRequest);

}
