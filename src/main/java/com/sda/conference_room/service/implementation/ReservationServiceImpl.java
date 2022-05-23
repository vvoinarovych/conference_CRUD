package com.sda.conference_room.service.implementation;

import com.sda.conference_room.exception.NotFoundException;
import com.sda.conference_room.exception.AlreadyExist;
import com.sda.conference_room.utils.mapper.ConferenceRoomMapper;
import com.sda.conference_room.utils.mapper.ReservationMapper;
import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.ReservationDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Reservation;
import com.sda.conference_room.repository.ReservationRepository;
import com.sda.conference_room.service.ConferenceRoomService;
import com.sda.conference_room.service.ReservationService;
import com.sda.conference_room.utils.validation.ReservationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConferenceRoomService conferenceRoomService;
    private final ReservationValidator reservationValidator;

    @Override
    public List<ReservationDto> getAllReservations() {
        log.info("Getting all the reservations");
        final List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(final Long id) {
        log.info("Getting a reservation by id: {}", id);
        return ReservationMapper.map(getReservationFromDatabaseById(id));
    }

    @Override
    public ReservationDto createReservation(final ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.map(reservationDto);
        if(reservationValidator.isRoomAvailableInSpecificPeriod(reservation.getConferenceRoom(),reservation.getStarting(), reservation.getEnding())){
            log.info("Creating reservation with id: {}", reservationDto.getId());
            Reservation createdReservation = reservationRepository.save(reservation);
            return ReservationMapper.map(createdReservation);
        }
        throw new AlreadyExist("Unable to book that conference room for that date");

    }

    @Override
    public List<ReservationDto> getAllReservationsByOrganizationId(Long id) {
        return getAllReservations().stream()
                .filter(reservation -> reservation.getConferenceRoomDto().getOrganizationDto().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto updateReservation(Long reservationId, ReservationDto reservationDto) {

        log.info("Updating reservation with id: {}", reservationDto.getId());
        getReservationFromDatabaseById(reservationDto.getId());
        final Reservation reservation = ReservationMapper.map(reservationDto);
        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.map(updatedReservation);
    }

    @Override
    public List<ReservationDto> getAllReservationsByConferenceRoomId(Long conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomById(conferenceRoomId);
        return reservationRepository.getAllByConferenceRoom(conferenceRoom)
                .stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReservationById(Long id) {
        log.info("Deleting reservation with id: {}", id);
        reservationRepository.delete(getReservationFromDatabaseById(id));
    }

    private Reservation getReservationFromDatabaseById(final Long id) {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(id);
        return reservationFromDatabase.orElseThrow(() -> new NotFoundException("Reservation with given id not found"));
    }

    @Override
    public List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(Long organizationId, ReservationDto reservationDto) {
        LocalDateTime start = reservationDto.getStarting();
        LocalDateTime end = reservationDto.getEnding();
        List<ConferenceRoom> conferenceRooms = conferenceRoomService.getAllConferenceRoomsForSpecificOrganization(organizationId).stream().map(ConferenceRoomMapper::map).collect(Collectors.toList());

        for (ConferenceRoom room : conferenceRooms) {
            room.setAvailable(reservationValidator.isRoomAvailableInSpecificPeriod(room, start, end));
        }
        return conferenceRooms.stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }
}
