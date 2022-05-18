package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.ReservationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<ReservationDto> getAllReservations();

    List<ReservationDto> getAllReservationsByConferenceRoomId(Long conferenceRoomId);

    List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(String organizationName, LocalDateTime start, LocalDateTime end);

    ReservationDto getReservationById(Long id);

    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto updateReservation(ReservationDto reservationDto);

    void deleteReservationById(Long id);

    List<ReservationDto> getAllReservationsByOrganizationId(Long id);
}
