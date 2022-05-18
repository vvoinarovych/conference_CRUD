package com.sda.conference_room.validation;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConferenceRoomValidator {

    private final ConferenceRoomRepository conferenceRoomRepository;

    public boolean isValid(ConferenceRoomDto conferenceRoomDto) {
        return isNameUnique(conferenceRoomDto);
    }

    private boolean isNameUnique(ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoomFromDb = conferenceRoomRepository.findConferenceRoomByName(conferenceRoomDto.getName());
        return conferenceRoomFromDb == null;
    }

}
