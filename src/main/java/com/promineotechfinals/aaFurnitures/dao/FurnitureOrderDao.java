package com.promineotechfinals.aaFurnitures.dao;

import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.OrderRequest;

public interface FurnitureOrderDao {

	Order createOrder(OrderRequest orderRequest);

}
