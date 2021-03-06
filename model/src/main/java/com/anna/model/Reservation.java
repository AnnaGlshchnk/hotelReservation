package com.anna.model;

import java.util.Date;

public class Reservation {

    private long reservationId;
    private Date startReservation;
    private Date finishReservation;
    private Guest guest;

    public Reservation(long reservationId, Date startReservation, Date finishReservation, Guest guest) {
        this.reservationId = reservationId;
        this.startReservation = startReservation;
        this.finishReservation = finishReservation;
        this.guest = guest;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartReservation() {
        return startReservation;
    }

    public void setStartReservation(Date startReservation) {
        this.startReservation = startReservation;
    }

    public Date getFinishReservation() {
        return finishReservation;
    }

    public void setFinishReservation(Date finishReservation) {
        this.finishReservation = finishReservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
