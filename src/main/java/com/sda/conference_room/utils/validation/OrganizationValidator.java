package com.sda.conference_room.utils.validation;

import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationValidator {

    private final OrganizationRepository organizationRepository;

    public boolean isValid(OrganizationDto organizationDto) {
        return isNameUnique(organizationDto);
    }

    private boolean isNameUnique(OrganizationDto organizationDto) {
        Organization organizationFromDb = organizationRepository.findOrganizationByName(organizationDto.getName());
        return organizationFromDb == null;
    }

}
