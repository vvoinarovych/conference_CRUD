package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.ReservationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<ReservationDto> getAllReservations();

    List<ReservationDto> getAllReservationsByConferenceRoomId(Long conferenceRoomId);

    List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(Long organizationId, LocalDateTime start, LocalDateTime end);

    ReservationDto getReservationById(Long id);

    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto updateReservation(Long reservationId, ReservationDto reservationDto);

    void deleteReservationById(Long id);

    List<ReservationDto> getAllReservationsByOrganizationId(Long id);
}
