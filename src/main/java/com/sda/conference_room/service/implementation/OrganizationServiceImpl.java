package com.sda.conference_room.service.implementation;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.OrganizationRepository;
import com.sda.conference_room.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getALlOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.getById(id);
    }

    @Override
    public Organization updateOrganization(Long organizationId, Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationRepository.deleteOrganizationById(id);
    }
}
