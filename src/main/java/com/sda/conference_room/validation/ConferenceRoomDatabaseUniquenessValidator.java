package com.sda.conference_room.validation;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConferenceRoomDatabaseUniquenessValidator {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomDto conferenceRoomDto;

    public boolean isUnique() {
        ConferenceRoom conferenceRoomFromDb = conferenceRoomRepository.findConferenceRoomByName(conferenceRoomDto.getName());
        return conferenceRoomFromDb == null;
    }

}
