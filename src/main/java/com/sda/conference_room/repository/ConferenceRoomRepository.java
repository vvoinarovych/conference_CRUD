package com.sda.conference_room.repository;

import com.sda.conference_room.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {

    ConferenceRoom findConferenceRoomByName(String name);
}
