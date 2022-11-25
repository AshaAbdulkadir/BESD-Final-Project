package com.promineotechfinals.aaFurnitures.dao;

import javax.validation.constraints.NotNull;

import com.promineotechfinals.aaFurnitures.entity.Customer;

public interface FurnitureOrderDao {

	Customer fetchCustomer(@NotNull String customer);

}
