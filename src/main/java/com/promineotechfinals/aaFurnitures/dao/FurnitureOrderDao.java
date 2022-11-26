package com.promineotechfinals.aaFurnitures.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.promineotechfinals.aaFurnitures.entity.Color;
import com.promineotechfinals.aaFurnitures.entity.Customer;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Option;
import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

public interface FurnitureOrderDao {
	
	List<Option> fetchOptions(List<String> optionIds);

	Optional<Customer> fetchCustomer(@NotNull String customer);

	Optional<Furnitures> fetchRoom(@NotNull Rooms room, @NotNull String material);

	Optional<Color> fetchColor(@NotNull String color);

	Order saveOrder(Customer customer, Furnitures furniture, Color color, BigDecimal price, List<Option> options);

}
