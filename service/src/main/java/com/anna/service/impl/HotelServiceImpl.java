package com.anna.service.impl;

import com.anna.dao.api.HotelDao;
import com.anna.model.HotelData;
import com.anna.model.HotelDetails;
import com.anna.service.api.HotelService;
import com.anna.service.exception.OperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public List<HotelData> getHotels() throws OperationFailedException {

        return hotelDao.getHotels();
    }

    @Override
    public HotelDetails getHotelById(Integer hotelId) throws OperationFailedException {

        return hotelDao.getHotelById(hotelId);
    }
}
