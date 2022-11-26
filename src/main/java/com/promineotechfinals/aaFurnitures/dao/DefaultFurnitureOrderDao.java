package com.promineotechfinals.aaFurnitures.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.promineotechfinals.aaFurnitures.entity.Color;
import com.promineotechfinals.aaFurnitures.entity.Customer;
import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Option;
import com.promineotechfinals.aaFurnitures.entity.OptionType;
import com.promineotechfinals.aaFurnitures.entity.Order;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

/**
 * 
 * @author Asha
 *
 */
@Component
public class DefaultFurnitureOrderDao implements FurnitureOrderDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<Customer> fetchCustomer(String customerId) {
		// @formatter:off
	    String sql = "" 
	        + "SELECT * " 
	        + "FROM customers "
	        + "WHERE customer_id = :customer_id";
	    // @formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);

		return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
	}

	class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
		@Override
		public Customer extractData(ResultSet rs) throws SQLException {
			rs.next(); //to get the next row

			// @formatter:off
	      return Customer.builder()
	          .customerId(rs.getString("customer_id"))
	          .customerPK(rs.getLong("customer_pk"))
	          .firstName(rs.getString("first_name"))
	          .lastName(rs.getString("last_name"))
	          .phone(rs.getString("phone"))
	          .build();
	      // @formatter:on

		}
	}

	@Override
	public List<Option> fetchOptions(List<String> optionIds) {
		if (optionIds.isEmpty()) {
			return new LinkedList<>();
		}

		Map<String, Object> params = new HashMap<>();

		// @formatter:off
	    String sql = ""
	        + "SELECT * "
	        + "FROM options "
	        + "WHERE option_id IN(";
	    // @formatter:on

		for (int index = 0; index < optionIds.size(); index++) {
			String key = "option_" + index;
			sql += ":" + key + ", ";
			params.put(key, optionIds.get(index));
		}

		sql = sql.substring(0, sql.length() - 2);
		sql += ")";

		return jdbcTemplate.query(sql, params, new RowMapper<Option>() {
			@Override
			public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			// @formatter:off
	        return Option.builder()
	            .category(OptionType.valueOf(rs.getString("category")))
	            .material(rs.getString("material"))
	            .name(rs.getString("name"))
	            .optionId(rs.getString("option_id"))
	            .optionPK(rs.getLong("option_pk"))
	            .price(rs.getBigDecimal("price"))
	            .build();
	        // @formatter:on
	        
			}
		});
	}
	
	/**
	 * 
	 */
	@Override
	public Optional<Furnitures> fetchRoom(@NotNull Rooms room, @NotNull String material) {
		// @formatter:off
	    String sql = "" 
	        + "SELECT * " 
	        + "FROM rooms "
	        + "WHERE room_id = :room_id "
	        + "AND material = :material ";
	    // @formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("room_id", room.toString());
		params.put("material", material);

		return Optional.ofNullable(jdbcTemplate.query(sql, params, new RoomResultSetExtractor()));
	}
	
	class RoomResultSetExtractor implements ResultSetExtractor<Furnitures> {
		@Override
		public Furnitures extractData(ResultSet rs) throws SQLException {
			rs.next();

			// @formatter:off
	      return Furnitures.builder()
	          .price(rs.getBigDecimal("price"))
	          .roomId(Rooms.valueOf(rs.getString("room_id")))
	          .roomPK(rs.getLong("model_pk"))
	          .material(rs.getString("material"))
	          .build();
	      // @formatter:on
		}
	}
	
	/**
	 * 
	 */
	@Override
	public Optional<Color> fetchColor(@NotNull String colorId) {
		// @formatter:off
	    String sql = "" 
	        + "SELECT * " 
	        + "FROM colors " 
	        + "WHERE color_id = :color_id";
	    // @formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("color_id", colorId);

		return Optional.ofNullable(jdbcTemplate.query(sql, params, new ColorResultSetExtractor()));
	}
	
	class ColorResultSetExtractor implements ResultSetExtractor<Color> {
		@Override
		public Color extractData(ResultSet rs) throws SQLException {
			rs.next();

			// @formatter:off
	      return Color.builder()
	          .color(rs.getString("color"))
	          .colorId(rs.getString("color_id"))
	          .colorPK(rs.getLong("color_pk"))
	          .price(rs.getBigDecimal("price"))
	          .build();
	      // @formatter:on
		}
	}
	
	/**
	 * 
	 */
	@Override
	public Order saveOrder(Customer customer, Furnitures furniture, Color color, BigDecimal price,
			List<Option> options) {
		SqlParams params = generateInsertSql(customer, furniture, color, price);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);

		Long orderPK = keyHolder.getKey().longValue();
		saveOptions(options, orderPK);

		//@formatter:off
     return Order.builder()
    		 .orderPK(orderPK)
    		 .customer(customer)
    		 .color(color)
    		 .options(options)
    		 .price(price)		 
    		 .build();
     //@formatter:on
	}
	
	private void saveOptions(List<Option> options, Long orderPK) {
		for (Option option : options) {
			SqlParams params = generateInsertSql(option, orderPK);
			jdbcTemplate.update(params.sql, params.source);

		}
		
	}
	
	/**
	 * 
	 * @param option
	 * @param orderPK
	 * @return
	 */
	private SqlParams generateInsertSql(Option option, Long orderPK) {
		
		SqlParams params = new SqlParams();

		// @formatter:off
	    params.sql = ""
	        + "INSERT INTO order_options ("
	        + "option_fk, order_fk"
	        + ") VALUES ("
	        + ":option_fk, :order_fk"
	        + ")";
	    // @formatter:on

		params.source.addValue("option_fk", option.getOptionPK());
		params.source.addValue("order_fk", orderPK);

		return params;
	}
	
	/**
	 * 
	 * @param customer
	 * @param furniture
	 * @param room 
	 * @param color
	 * @param price
	 * @return
	 */
	private SqlParams generateInsertSql(Customer customer, Furnitures furniture, Color color, BigDecimal price) {
		
		// Sequel statement 
		// @formatter:off
	    String sql = ""
	        + "INSERT INTO orders ("
	        + "customer_fk, color_fk, room_fk, price"
	        + ") VALUES ("
	        + ":customer_fk, :color_fk, :room_fk, :price"
	        + ")";
	    // @formatter:on

		SqlParams params = new SqlParams();

		params.sql = sql;
		params.source.addValue("customer_fk", customer.getCustomerPK());
		params.source.addValue("color_fk", color.getColorPK());
		params.source.addValue("room_fk", furniture.getRoomPK());
		params.source.addValue("price", price);

		return params;
	}

	// Sequel parameter
	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}
	

}
