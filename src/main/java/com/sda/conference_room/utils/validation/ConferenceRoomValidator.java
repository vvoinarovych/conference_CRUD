package com.sda.conference_room.utils.validation;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConferenceRoomValidator {

    private final ConferenceRoomRepository conferenceRoomRepository;

    public boolean isValidForCreate(ConferenceRoomDto conferenceRoomDto) {
        return isNameUnique(conferenceRoomDto);
    }

    public boolean isValidForUpdate(Long conferenceRoomId, ConferenceRoomDto conferenceRoomDto) {
        return hasSameName(conferenceRoomId, conferenceRoomDto) || isNameUnique(conferenceRoomDto);
    }

    private boolean isNameUnique(ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoomFromDb = conferenceRoomRepository.findConferenceRoomByName(conferenceRoomDto.getName());
            return conferenceRoomFromDb == null;
    }

    private boolean hasSameName(Long conferenceRoomId, ConferenceRoomDto conferenceRoomDto ){
        String conferenceRoomFromDbName = conferenceRoomRepository.getById(conferenceRoomId).getName();
        return conferenceRoomDto.getName().equals(conferenceRoomFromDbName);
    }

}
