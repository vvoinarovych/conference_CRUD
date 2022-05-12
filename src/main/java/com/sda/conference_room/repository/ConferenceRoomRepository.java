package com.sda.conference_room.repository;

import com.sda.conference_room.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
}
