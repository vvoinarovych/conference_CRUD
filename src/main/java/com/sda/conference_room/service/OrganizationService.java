package com.sda.conference_room.service;

import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.Organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    List<OrganizationDto> getALlOrganizations();

    OrganizationDto getOrganizationById(Long id);

    Organization updateOrganization(Long organizationId, OrganizationDto organizationDto);

    void deleteOrganizationById(Long id);
}
