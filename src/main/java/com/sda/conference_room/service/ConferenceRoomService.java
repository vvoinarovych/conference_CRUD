package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ConferenceRoomDto;

import java.util.List;

public interface ConferenceRoomService {

    List<ConferenceRoomDto> getAllConferenceRooms();

    List<ConferenceRoomDto> getAllAvailableConferenceRooms();

    ConferenceRoomDto getConferenceRoomById(final Long id);

    ConferenceRoomDto addConferenceRoom(final ConferenceRoomDto conferenceRoomDto);

    ConferenceRoomDto updateConferenceRoom(final ConferenceRoomDto conferenceRoomDto);

    void deleteConferenceRoomById(final Long id);


}
