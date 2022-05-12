package com.sda.conference_room.service;

import com.sda.conference_room.model.entity.Organization;

import java.util.List;

public interface OrganizationService {

    Organization saveOrganization(Organization organization);

    List<Organization> getALlOrganizations();

    Organization getOrganizationById(Long id);

    Organization updateOrganization(Long organizationId, Organization organization);

    void deleteOrganizationById(Long id);
}
