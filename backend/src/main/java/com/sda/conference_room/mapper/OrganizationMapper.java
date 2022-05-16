package com.sda.conference_room.mapper;

import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.Organization;

public class OrganizationMapper {

    public static Organization map(OrganizationDto dto) {
        return Organization.builder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withPassword((dto.getPassword()))
                .build();
    }

    public static OrganizationDto map(Organization organization){
        return OrganizationDto.builder()
                .withId(organization.getId())
                .withName(organization.getName())
                .withPassword(organization.getPassword())
                .build();
    }
}
