package com.promineotechfinals.aaFurnitures.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotechfinals.aaFurnitures.entity.Furnitures;
import com.promineotechfinals.aaFurnitures.entity.Rooms;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DafaultFurnitureStoreDao implements FurnitureStoreDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Furnitures> fetchFurnitures(Rooms room, String material) {
		log.debug("DAO: room={}, materia={}", room, material);
		
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM rooms "
				+ "WHERE room_id = :room_id AND material= :material";
		
		// @formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("room_id", room.toString());
		params.put("material", material);
		
		return jdbcTemplate.query(sql, params, 
				new RowMapper<>() {

			@Override
			public Furnitures mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter: off
				return Furnitures.builder()
						.price(new BigDecimal(rs.getString("price")))
						.roomId(Rooms.valueOf(rs.getString("room_id")))
						.roomPK(rs.getLong("room_pk"))
						.material(rs.getString("material"))
						.build();
				
				// @formatter: on
			}});
	}

}
