package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    List<ReservationDto> getAllReservations();

    ReservationDto getReservationById(Long id);

    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto updateReservation(ReservationDto reservationDto);

    void deleteReservationById(Long id);


}
