package com.sda.conference_room.model.dto;

import com.sda.conference_room.model.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceRoomDto {

    private Long id;

    private String name;

    private int level;

    private boolean available;

    private Long sittingPlaces;

    private Long standingPlaces;

    private OrganizationDto organizationDto;
}
