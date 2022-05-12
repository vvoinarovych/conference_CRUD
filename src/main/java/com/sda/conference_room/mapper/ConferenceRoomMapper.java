package com.sda.conference_room.mapper;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;

public class ConferenceRoomMapper {

    public static ConferenceRoomDto map (final ConferenceRoom conferenceRoom) {
        return ConferenceRoomDto.builder()
                .withId(conferenceRoom.getId())
                .withName(conferenceRoom.getName())
                .withLevel(conferenceRoom.getLevel())
                .withAvailable(conferenceRoom.isAvailable())
                .withSittingPlaces(conferenceRoom.getSittingPlaces())
                .withStandingPlaces(conferenceRoom.getStandingPlaces())
//                .withOrganizationDto(OrganizationMapper.map(conferenceRoom.getOrganization()))
                .build();
    }

    public static ConferenceRoom map (final ConferenceRoomDto conferenceRoomDto) {
        return ConferenceRoom.builder()
                .withId(conferenceRoomDto.getId())
                .withName(conferenceRoomDto.getName())
                .withLevel(conferenceRoomDto.getLevel())
                .withAvailable(conferenceRoomDto.isAvailable())
                .withSittingPlaces(conferenceRoomDto.getSittingPlaces())
                .withStandingPlaces(conferenceRoomDto.getStandingPlaces())
//                .withOrganization(OrganizationMapper.map(conferenceRoomDto.getOrganizationDto()))
                .build();
    }
}
