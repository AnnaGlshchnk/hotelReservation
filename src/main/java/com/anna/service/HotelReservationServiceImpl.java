package com.anna.service;

import com.anna.dao.HotelReservationDao;
import com.anna.entity.Hotel;
import com.anna.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelReservationServiceImpl implements HotelReservationService {


    @Autowired
    private HotelReservationDao hotelReservationDao;

    public List<Hotel> showHotel() { return hotelReservationDao.showHotel(); }

    public List showRoom(int hotelId) { return hotelReservationDao.showRoom(hotelId); }

    public List showReservation(int roomId) { return hotelReservationDao.showReservation(roomId); }

    public void addReservation(Reservation reservation) {
        hotelReservationDao.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        hotelReservationDao.updateReservation(reservation);

    }

    public void deleteReservation(int reservId) {
        hotelReservationDao.deleteReservation(reservId);

    }


}