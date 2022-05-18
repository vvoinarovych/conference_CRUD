package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ConferenceRoomDto;

import java.util.List;

public interface ConferenceRoomService {

    List<ConferenceRoomDto> getAllConferenceRooms();

    List<ConferenceRoomDto> getAllAvailableConferenceRooms();

    List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganization(String organizationName);

    ConferenceRoomDto getConferenceRoomById(final Long id);

    ConferenceRoomDto createConferenceRoom(final Long OrganizationId, final ConferenceRoomDto conferenceRoomDto);

    ConferenceRoomDto updateConferenceRoom(final ConferenceRoomDto conferenceRoomDto);

    void deleteConferenceRoomById(final Long id);


}
