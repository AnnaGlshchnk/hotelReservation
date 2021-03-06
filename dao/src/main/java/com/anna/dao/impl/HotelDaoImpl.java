package com.anna.dao.impl;

import com.anna.dao.api.HotelDao;
import com.anna.model.Hotel;
import com.anna.model.HotelData;
import com.anna.model.HotelDetails;
import com.anna.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {

    private static String HOTEL_ID = "hotelId";

    @Value("${hotelreservation.getHotels}")
    private String getHotelsSql;
    @Value("${hotelreservation.getHotelById}")
    private String getHotelByIdSql;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public HotelDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<HotelData> getHotels() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(getHotelsSql, mapSqlParameterSource, new HotelMapper());
    }

    public HotelDetails getHotelById(Integer hotelId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(HOTEL_ID, hotelId);
        return namedParameterJdbcTemplate.queryForObject(getHotelByIdSql, mapSqlParameterSource, new HotelDetailsMapper());
    }


    private class HotelMapper implements RowMapper<HotelData> {
        @Override
        public HotelData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HotelData(rs.getLong("hotel_id"),
                    new Hotel(rs.getString("hotel_name")),
                    rs.getInt("countOfRooms"));
        }
    }

    private class HotelDetailsMapper implements RowMapper<HotelDetails> {
        @Override
        public HotelDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            HotelDetails hotel = new HotelDetails(rs.getLong("hotel_id"),
                                    new Hotel(rs.getString("hotel_name")),
                                    new ArrayList<>());

            do {
                hotel.getRooms().add(new Room(rs.getInt("room_number")));
            } while (rs.next());

            return hotel;
        }
    }

}
