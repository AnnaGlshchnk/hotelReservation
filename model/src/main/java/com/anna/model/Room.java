package com.anna.model;

public class Room {

    private int roomId;
    private int roomNumber;
    private Hotel hotel;

    public Room() {
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(int roomId, int roomNumber, Hotel hotel) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.hotel = hotel;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}