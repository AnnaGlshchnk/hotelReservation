package com.anna.dao.impl;

import com.anna.dao.api.ReservationDao;
import com.anna.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    private static String RESERVATION_ID = "reservationId";
    private static String START_RESERVATION = "startReservation";
    private static String END_RESERVATION = "endReservation";
    private static String ROOM_ID = "roomId";
    private static String GUEST_ID = "guestId";

    @Value("${hotelreservation.getReservations}")
    private String getReservationSql;
    @Value("${hotelreservation.getReservationById}")
    private String getReservationByIdSql;
    @Value("${hotelreservation.addReservation}")
    private String addReservationSql;
    @Value("${hotelreservation.updateReservation}")
    private String updateReservationSql;
    @Value("${hotelreservation.deleteReservation}")
    private String deleteReservationSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ReservationDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Reservation> getReservations() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.query(getReservationSql, mapSqlParameterSource, new ReservationMapper());
    }

    public ReservationDetails getReservationById(Integer reservationId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(RESERVATION_ID, reservationId);

        return namedParameterJdbcTemplate.queryForObject(getReservationByIdSql, mapSqlParameterSource, new ReservationDetailsMapper());
    }

    public Integer addReservation(SaveReservation reservation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(START_RESERVATION, reservation.getStartReservation());
        mapSqlParameterSource.addValue(END_RESERVATION, reservation.getFinishReservation());
        mapSqlParameterSource.addValue(ROOM_ID, reservation.getRoom().getRoomId());
        mapSqlParameterSource.addValue(GUEST_ID, reservation.getGuest().getGuestId());

        namedParameterJdbcTemplate.update(addReservationSql, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Integer updateReservation(Integer reservationId, SaveReservation reservation) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(RESERVATION_ID, reservationId);
        mapSqlParameterSource.addValue(START_RESERVATION, reservation.getStartReservation());
        mapSqlParameterSource.addValue(END_RESERVATION, reservation.getFinishReservation());
        mapSqlParameterSource.addValue(ROOM_ID, reservation.getRoom().getRoomId());
        mapSqlParameterSource.addValue(GUEST_ID, reservation.getGuest().getGuestId());

        return namedParameterJdbcTemplate.update(updateReservationSql, mapSqlParameterSource);
    }

    public Integer deleteReservation(Integer reservationId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(RESERVATION_ID, reservationId);

        return namedParameterJdbcTemplate.update(deleteReservationSql, mapSqlParameterSource);
    }

    private class ReservationMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Reservation(rs.getLong("reservation_id"),
                    rs.getDate("start_reservation"),
                    rs.getDate("end_reservation"),
                    new Guest(rs.getLong("guest_id")));
        }
    }

    private class ReservationDetailsMapper implements RowMapper<ReservationDetails> {
        @Override
        public ReservationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReservationDetails(rs.getLong("reservation_id"),
                    rs.getDate("start_reservation"),
                    rs.getDate("end_reservation"),
                    new GuestData(rs.getString("first_name"),
                            rs.getString("surname")),
                    new Room(rs.getInt("room_number")),
                    new Hotel(rs.getString("hotel_name"))
            );
        }
    }
}
