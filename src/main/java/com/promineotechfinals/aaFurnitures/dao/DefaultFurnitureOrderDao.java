package com.promineotechfinals.aaFurnitures.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotechfinals.aaFurnitures.entity.Customer;


@Component
public class DefaultFurnitureOrderDao implements FurnitureOrderDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Customer fetchCustomer(String customerId) {
		// @formatter:off
	    String sql = "" 
	        + "SELECT * " 
	        + "FROM customers "
	        + "WHERE customer_id = :customer_id";
	    // @formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);

		return jdbcTemplate.query(sql, params, new CustomerResultSetExtractor());
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
	

}
