package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;

import java.util.List;

public interface ConferenceRoomService {

    List<ConferenceRoomDto> getAllConferenceRooms();

    List<ConferenceRoomDto> getAllAvailableConferenceRooms();//for deleting

    List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganization(String organizationName);

    ConferenceRoom getConferenceRoomById(final Long id);

    ConferenceRoomDto getConferenceRoomDtoById(final Long id);

    ConferenceRoomDto createConferenceRoom(final Long OrganizationId, final ConferenceRoomDto conferenceRoomDto);

    ConferenceRoomDto updateConferenceRoom(Long conferenceRoomId, ConferenceRoomDto conferenceRoomDto);

    void deleteConferenceRoomById(final Long id);


}
