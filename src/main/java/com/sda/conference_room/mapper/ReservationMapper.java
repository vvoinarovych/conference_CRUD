package com.sda.conference_room.mapper;

import com.sda.conference_room.model.dto.ReservationDto;
import com.sda.conference_room.model.entity.Reservation;

public class ReservationMapper {

    public static ReservationDto map(final Reservation reservation) {
        return ReservationDto.builder()
                .withId(reservation.getId())
                .withStarting(reservation.getStarting())
                .withEnding(reservation.getEnding())
                .withReservationIdentifier(reservation.getReservationIdentifier())
                .build();
    }

    public static Reservation map(final ReservationDto reservationDto) {
        return Reservation.builder()
                .withId(reservationDto.getId())
                .withStarting(reservationDto.getStarting())
                .withEnding(reservationDto.getEnding())
                .withReservationIdentifier(reservationDto.getReservationIdentifier())
                .build();

    }
}
