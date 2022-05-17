package com.sda.conference_room.validation;

import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationDatabaseUniquenessValidator {

    private final OrganizationRepository organizationRepository;
    private final OrganizationDto organizationDto;

    public boolean isUnique() {
        Organization organizationFromDb = organizationRepository.findOrganizationByName(organizationDto.getName());
        return organizationFromDb == null;
    }

}
