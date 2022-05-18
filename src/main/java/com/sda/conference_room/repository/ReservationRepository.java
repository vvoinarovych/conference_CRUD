package com.sda.conference_room.repository;

import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByConferenceRoom(ConferenceRoom conferenceRoom);
}
