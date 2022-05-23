package com.sda.conference_room.utils.validation;

import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Reservation;
import com.sda.conference_room.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationValidator {

    private final ReservationRepository reservationRepository;

    public boolean isRoomAvailableInSpecificPeriod(ConferenceRoom conferenceRoom, LocalDateTime start, LocalDateTime end) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> reservationsForThatRoom = reservations.stream()
                .filter(reservation -> reservation.getConferenceRoom().getName().equals(conferenceRoom.getName()))
                .filter(reservation -> (reservation.getStarting().isBefore(end) || reservation.getStarting().isEqual(end))
                        && (reservation.getEnding().isAfter(start) || reservation.getEnding().isEqual(start)))
                .collect(Collectors.toList());
        return reservationsForThatRoom.isEmpty();
    }
}
