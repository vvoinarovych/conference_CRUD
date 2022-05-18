package com.sda.conference_room.mapper;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Organization;

public class ConferenceRoomMapper {

    public static ConferenceRoomDto map (final ConferenceRoom conferenceRoom) {
        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withId(conferenceRoom.getId())
                .withName(conferenceRoom.getName())
                .withLevel(conferenceRoom.getLevel())
                .withAvailable(conferenceRoom.isAvailable())
                .withSittingPlaces(conferenceRoom.getSittingPlaces())
                .withStandingPlaces(conferenceRoom.getStandingPlaces())
                .build();

        if(conferenceRoom.getOrganization() != null){
            OrganizationDto organizationDto = OrganizationMapper.map(conferenceRoom.getOrganization());
            conferenceRoomDto.setOrganizationDto(organizationDto);
        } else{
            conferenceRoomDto.setOrganizationDto(null);
        }
        return conferenceRoomDto;
    }

    public static ConferenceRoom map (final ConferenceRoomDto conferenceRoomDto) {
       ConferenceRoom conferenceRoom =  ConferenceRoom.builder()
                .withId(conferenceRoomDto.getId())
                .withName(conferenceRoomDto.getName())
                .withLevel(conferenceRoomDto.getLevel())
                .withAvailable(conferenceRoomDto.isAvailable())
                .withSittingPlaces(conferenceRoomDto.getSittingPlaces())
                .withStandingPlaces(conferenceRoomDto.getStandingPlaces())
                .withOrganization(null)
                .build();

        if(conferenceRoomDto.getOrganizationDto() != null){
            Organization organization = OrganizationMapper.map(conferenceRoomDto.getOrganizationDto());
            conferenceRoom.setOrganization(organization);
        } else{
            conferenceRoomDto.setOrganizationDto(null);
        }
        return conferenceRoom;
    }
}
